package com.hypnoticocelot.jaxrs.doclet.parser;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.transform;
import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.hypnoticocelot.jaxrs.doclet.DocletOptions;
import com.hypnoticocelot.jaxrs.doclet.model.Model;
import com.hypnoticocelot.jaxrs.doclet.model.Property;
import com.hypnoticocelot.jaxrs.doclet.translator.Translator;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Tag;
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
		boolean isPrimitive = /* type.isPrimitive()? || */AnnotationHelper.isPrimitive(type);
		boolean isJavaxType = type.qualifiedTypeName().startsWith("javax.");
		boolean isBaseObject = type.qualifiedTypeName().equals("java.lang.Object");
		boolean isTypeToTreatAsOpaque = this.options.getTypesToTreatAsOpaque().contains(type.qualifiedTypeName());
		ClassDoc classDoc = type.asClassDoc();
		if (isPrimitive || isJavaxType || isBaseObject || isTypeToTreatAsOpaque || classDoc == null || alreadyStoredType(type)) {
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

	private Map<String, TypeRef> findReferencedTypes(ClassDoc classDoc) {
		Map<String, TypeRef> elements = new HashMap<String, TypeRef>();

		// add fields
		FieldDoc[] fieldDocs = classDoc.fields();

		if (fieldDocs != null) {
			for (FieldDoc field : fieldDocs) {
				// TODO: remove fields that have excludeFieldTags on them

				// ignore static, transient fields
				if (!field.isStatic() && !field.isTransient()) {

					String description = getFieldDescription(field);
					String min = getFieldMin(field);
					String max = getFieldMax(field);

					String name = this.translator.fieldName(field).value();
					if (name != null && !elements.containsKey(name)) {
						elements.put(name, new TypeRef(field.type(), description, min, max));
					}
				}
			}
		}

		// add method return types
		MethodDoc[] methodDocs = classDoc.methods();
		if (methodDocs != null) {
			for (MethodDoc method : methodDocs) {
				String name = this.translator.methodName(method).value();
				if (name != null) {
					if (elements.containsKey(name)) {
						TypeRef typeRef = elements.get(name);
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
						// this is a getter where there wasn't a specific field
						String description = getFieldDescription(method);
						String min = getFieldMin(method);
						String max = getFieldMax(method);
						elements.put(name, new TypeRef(method.returnType(), description, min, max));
					}
				}
			}
		}
		return elements;
	}

	private String getFieldDescription(com.sun.javadoc.MemberDoc docItem) {
		// method
		String description = getDocItemTag(docItem, this.options.getPropertyCommentTags());
		if (description == null) {
			description = docItem.commentText();
		}
		if (description == null || description.trim().length() == 0) {
			return null;
		}
		return description;
	}

	private String getFieldMin(com.sun.javadoc.MemberDoc docItem) {
		return getDocItemTag(docItem, this.options.getPropertyMinTags());
	}

	private String getFieldMax(com.sun.javadoc.MemberDoc docItem) {
		return getDocItemTag(docItem, this.options.getPropertyMaxTags());
	}

	private String getDocItemTag(com.sun.javadoc.ProgramElementDoc item, Collection<String> matchTags) {
		String customValue = null;
		if (matchTags != null) {
			for (String matchTag : matchTags) {
				Tag[] tags = item.tags(matchTag);
				if (tags != null && tags.length > 0) {
					String val = tags[0].text().trim();
					if (val.trim().length() > 0) {
						customValue = val;
						break;
					}
				}
			}
		}
		return customValue;
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

			Type containerOf = parseParameterisedTypeOf(type);
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
			Type pt = parseParameterisedTypeOf(type.type);
			if (pt != null) {
				parseModel(pt);
			}
		}
	}

	private Type parseParameterisedTypeOf(Type type) {
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
