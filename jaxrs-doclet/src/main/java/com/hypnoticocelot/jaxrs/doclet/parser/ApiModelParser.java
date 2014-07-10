package com.hypnoticocelot.jaxrs.doclet.parser;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.transform;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.hypnoticocelot.jaxrs.doclet.DocletOptions;
import com.hypnoticocelot.jaxrs.doclet.model.Model;
import com.hypnoticocelot.jaxrs.doclet.model.Property;
import com.hypnoticocelot.jaxrs.doclet.translator.NameBasedTranslator;
import com.hypnoticocelot.jaxrs.doclet.translator.Translator;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Type;

public class ApiModelParser {

	private final DocletOptions options;
	final Translator translator;
	private final Type rootType;
	private final Set<Model> models;

	public ApiModelParser(DocletOptions options, Translator translator, Type rootType) {
		this.options = options;
		this.translator = translator;
		this.rootType = rootType;
		this.models = new LinkedHashSet<Model>();
	}

	public Set<Model> parse() {
		parseModel(this.rootType);
		return this.models;
	}

	private void parseModel(Type type) {
		String qName = type.qualifiedTypeName();
		boolean isPrimitive = AnnotationHelper.isPrimitive(type);
		boolean isJavaxType = qName.startsWith("javax.");
		boolean isBaseObject = qName.equals("java.lang.Object");
		boolean isClass = qName.equals("java.lang.Class");
		boolean isWildcard = qName.equals("?");
		boolean isTypeToTreatAsOpaque = this.options.getTypesToTreatAsOpaque().contains(qName);
		ClassDoc classDoc = type.asClassDoc();
		if (isPrimitive || isJavaxType || isClass || isWildcard || isBaseObject || isTypeToTreatAsOpaque || classDoc == null || classDoc.isEnum()
				|| alreadyStoredType(type)) {
			return;
		}

		Map<String, TypeRef> types = findReferencedTypes(classDoc);
		Map<String, Property> elements = findReferencedElements(types);
		if (!elements.isEmpty()) {
			this.models.add(new Model(this.translator.typeName(type).value(), elements));
			parseNestedModels(types.values());
		}
	}

	static class TypeRef {

		Type type;
		String description;
		String min;
		String max;

		TypeRef(Type type) {
			super();
			this.type = type;
		}

		TypeRef(Type type, String description, String min, String max) {
			super();
			this.type = type;
			if (description != null && description.trim().length() > 0) {
				this.description = description.trim();
			}
			if (min != null && min.trim().length() > 0) {
				this.min = min.trim();
			}
			if (max != null && max.trim().length() > 0) {
				this.max = max.trim();
			}
		}
	}

	// get list of super classes with highest level first so we process
	// grandparents down, this allows us to override field names via the lower levels
	List<ClassDoc> getClassLineage(ClassDoc classDoc) {
		List<ClassDoc> classes = new ArrayList<ClassDoc>();
		while (classDoc != null) {

			// ignore parent object class
			String qName = classDoc.qualifiedName();
			boolean isBaseObject = qName.equals("java.lang.Object");
			if (isBaseObject) {
				break;
			}

			classes.add(classDoc);
			classDoc = classDoc.superclass();
		}
		Collections.reverse(classes);
		return classes;
	}

	private Map<String, TypeRef> findReferencedTypes(ClassDoc rootClassDoc) {

		Map<String, TypeRef> elements = new HashMap<String, TypeRef>();

		List<ClassDoc> classes = getClassLineage(rootClassDoc);

		// map of raw field names to translated names, translated names may be different
		// due to annotations like XMLElement
		Map<String, String> rawToTranslatedFields = new HashMap<String, String>();

		NameBasedTranslator nameTranslator = new NameBasedTranslator();

		for (ClassDoc classDoc : classes) {

			boolean superClass = !classDoc.equals(rootClassDoc);

			// add fields
			FieldDoc[] fieldDocs = classDoc.fields();

			Set<String> excludeFields = new HashSet<String>();

			if (fieldDocs != null) {
				for (FieldDoc field : fieldDocs) {

					// ignore static or transient fields
					if (field.isStatic() || field.isTransient()) {
						continue;
					}

					// if super class ignore private fields
					if (superClass && field.isPrivate()) {
						continue;
					}

					String name = this.translator.fieldName(field).value();
					rawToTranslatedFields.put(field.name(), name);

					// ignore deprecated fields
					if (this.options.isExcludeDeprecatedFields() && AnnotationHelper.isDeprecated(field)) {
						excludeFields.add(field.name());
						continue;
					}

					String description = getFieldDescription(field);
					String min = getFieldMin(field);
					String max = getFieldMax(field);

					if (name != null && !elements.containsKey(name)) {
						elements.put(field.name(), new TypeRef(field.type(), description, min, max));
					}
				}
			}

			// add methods
			MethodDoc[] methodDocs = classDoc.methods();
			if (methodDocs != null) {
				for (MethodDoc method : methodDocs) {

					// ignore static methods and private methods
					if (method.isStatic() || method.isPrivate()) {
						continue;
					}

					// we tie getters and their corresponding methods together via this rawFieldName
					String rawFieldName = nameTranslator.methodName(method).value();

					// this is either an overridden name of the field on a getter or a non getter method
					// with a supported annotation
					String translatedNameViaMethod = this.translator.methodName(method).value();

					if (translatedNameViaMethod != null) {

						boolean isFieldGetter = rawFieldName != null && (elements.containsKey(rawFieldName) || excludeFields.contains(rawFieldName));
						boolean excludeMethod = this.options.isExcludeDeprecatedFields() && AnnotationHelper.isDeprecated(method);

						if (isFieldGetter) {

							// skip if the field has already been excluded
							if (excludeFields.contains(rawFieldName)) {
								continue;
							}

							// skip if this method is to be excluded but also remove the field from the elements
							// so it doesnt appear in the model
							if (excludeMethod) {
								elements.remove(rawFieldName);
								continue;
							}

							// see if the field name should be overwritten via annotations on the getter
							String nameViaField = rawToTranslatedFields.get(rawFieldName);
							if (!translatedNameViaMethod.equals(nameViaField)) {
								rawToTranslatedFields.put(rawFieldName, translatedNameViaMethod);
							}

							TypeRef typeRef = elements.get(rawFieldName);
							// the field was already found e.g. class had a field and this is the getter
							// check if there are tags on the getter we can use to fill in description, min and max
							if (typeRef.description == null) {
								typeRef.description = getFieldDescription(method);
							}
							if (typeRef.min == null) {
								typeRef.min = getFieldMin(method);
							}
							if (typeRef.max == null) {
								typeRef.max = getFieldMax(method);
							}

						} else {

							// skip if this method is to be excluded
							if (excludeMethod) {
								continue;
							}

							// this is a getter or other method where there wasn't a specific field
							String description = getFieldDescription(method);
							String min = getFieldMin(method);
							String max = getFieldMax(method);
							elements.put(translatedNameViaMethod, new TypeRef(method.returnType(), description, min, max));
						}

					}
				}
			}

		}

		// finally switch the element keys to use the translated field names
		Map<String, TypeRef> res = new HashMap<String, TypeRef>();
		for (Map.Entry<String, TypeRef> entry : elements.entrySet()) {
			String translatedName = rawToTranslatedFields.get(entry.getKey());
			res.put(translatedName == null ? entry.getKey() : translatedName, entry.getValue());
		}

		return res;
	}

	private String getFieldDescription(com.sun.javadoc.MemberDoc docItem) {
		// method
		String description = AnnotationHelper.getTagValue(docItem, this.options.getPropertyCommentTags());
		if (description == null) {
			description = docItem.commentText();
		}
		if (description == null || description.trim().length() == 0) {
			return null;
		}
		return description;
	}

	private String getFieldMin(com.sun.javadoc.MemberDoc docItem) {
		return AnnotationHelper.getTagValue(docItem, this.options.getPropertyMinTags());
	}

	private String getFieldMax(com.sun.javadoc.MemberDoc docItem) {
		return AnnotationHelper.getTagValue(docItem, this.options.getPropertyMaxTags());
	}

	private Map<String, Property> findReferencedElements(Map<String, TypeRef> types) {
		Map<String, Property> elements = new HashMap<String, Property>();
		for (Map.Entry<String, TypeRef> entry : types.entrySet()) {
			String typeName = entry.getKey();
			Type type = entry.getValue().type;
			ClassDoc typeClassDoc = type.asClassDoc();

			String propertyType = this.translator.typeName(type).value();
			List<String> allowableValues = null;
			if (typeClassDoc != null && typeClassDoc.isEnum()) {
				propertyType = "string";
				allowableValues = transform(asList(typeClassDoc.enumConstants()), new Function<FieldDoc, String>() {

					public String apply(FieldDoc input) {
						return input.name();
					}
				});
			}

			Type containerOf = getTypeArgument(type);
			String itemsRef = null;
			String itemsType = null;
			String containerTypeOf = containerOf == null ? null : this.translator.typeName(containerOf).value();
			if (containerOf != null) {
				if (AnnotationHelper.isPrimitive(containerOf)) {
					itemsType = containerTypeOf;
				} else {
					itemsRef = containerTypeOf;
				}
			}
			Boolean uniqueItems = null;
			if (propertyType.equals("array")) {
				if (AnnotationHelper.isSet(type.qualifiedTypeName())) {
					uniqueItems = Boolean.TRUE;
				}
			}

			Property property = new Property(propertyType, entry.getValue().description, itemsRef, itemsType, uniqueItems, allowableValues,
					entry.getValue().min, entry.getValue().max);
			elements.put(typeName, property);
		}
		return elements;
	}

	private void parseNestedModels(Collection<TypeRef> types) {
		for (TypeRef type : types) {
			parseModel(type.type);
			Type pt = getTypeArgument(type.type);
			if (pt != null) {
				parseModel(pt);
			}
		}
	}

	/**
	 * This gets the type of the given type, if its parameterized it returns the first type argument.
	 * @param type The type (may be parameterized)
	 * @return The raw type if not parameterized otherwise the type of the first parameterized argument
	 */
	public static Type getTypeArgument(Type type) {
		Type result = null;
		ParameterizedType pt = type.asParameterizedType();
		if (pt != null) {
			Type[] typeArgs = pt.typeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				result = typeArgs[0];
			}
		}
		return result;
	}

	private boolean alreadyStoredType(final Type type) {
		return filter(this.models, new Predicate<Model>() {

			public boolean apply(Model model) {
				return model.getId().equals(ApiModelParser.this.translator.typeName(type).value());
			}
		}).size() > 0;
	}

}
