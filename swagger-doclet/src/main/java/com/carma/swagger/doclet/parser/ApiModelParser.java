package com.carma.swagger.doclet.parser;

import static com.google.common.collect.Collections2.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.carma.swagger.doclet.DocletOptions;
import com.carma.swagger.doclet.model.Model;
import com.carma.swagger.doclet.model.Property;
import com.carma.swagger.doclet.translator.NameBasedTranslator;
import com.carma.swagger.doclet.translator.Translator;
import com.carma.swagger.doclet.translator.Translator.OptionalName;
import com.google.common.base.Predicate;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Type;
import com.sun.javadoc.TypeVariable;

/**
 * The ApiModelParser represents a parser for api model classes which are used for parameters, resource method return types and
 * model fields.
 * @version $Id$
 */
public class ApiModelParser {

	private final DocletOptions options;
	final Translator translator;
	private final Type rootType;
	private final Set<Model> models;
	private final ClassDoc[] viewClasses;

	private Map<String, Type> varsToTypes = new HashMap<String, Type>();

	// composite param model processing specifics
	private boolean composite = false;
	private boolean consumesMultipart = false;

	/**
	 * This creates a ApiModelParser
	 * @param options
	 * @param translator
	 * @param rootType
	 */
	public ApiModelParser(DocletOptions options, Translator translator, Type rootType) {
		this(options, translator, rootType, null);
	}

	/**
	 * This creates a ApiModelParser
	 * @param options
	 * @param translator
	 * @param rootType
	 * @param viewClasses
	 */
	public ApiModelParser(DocletOptions options, Translator translator, Type rootType, ClassDoc[] viewClasses) {
		this.options = options;
		this.translator = translator;
		this.rootType = rootType;
		if (viewClasses == null) {
			this.viewClasses = null;
		} else {
			this.viewClasses = new ClassDoc[viewClasses.length];
			int i = 0;
			for (ClassDoc view : viewClasses) {
				this.viewClasses[i++] = view;
			}
		}
		this.models = new LinkedHashSet<Model>();
	}

	/**
	 * This creates a ApiModelParser for use when using composite parameter model parsing
	 * @param options
	 * @param translator
	 * @param rootType
	 * @param consumesMultipart
	 */
	public ApiModelParser(DocletOptions options, Translator translator, Type rootType, boolean consumesMultipart) {
		this(options, translator, rootType, null);
		this.consumesMultipart = consumesMultipart;
		this.composite = true;
	}

	/**
	 * This parsers a model class built from parsing this class
	 * @return The set of model classes
	 */
	public Set<Model> parse() {
		parseModel(this.rootType, false);
		return this.models;
	}

	private void parseModel(Type type, boolean nested) {

		String qName = type.qualifiedTypeName();
		boolean isPrimitive = ParserHelper.isPrimitive(type, this.options);
		boolean isJavaxType = qName.startsWith("javax.");
		boolean isBaseObject = qName.equals("java.lang.Object");
		boolean isClass = qName.equals("java.lang.Class");
		boolean isWildcard = qName.equals("?");

		ClassDoc classDoc = type.asClassDoc();

		if (isPrimitive || isJavaxType || isClass || isWildcard || isBaseObject || classDoc == null || classDoc.isEnum() || alreadyStoredType(type)) {
			return;
		}

		// check if its got an exclude tag
		// see if deprecated
		if (this.options.isExcludeDeprecatedModelClasses() && ParserHelper.isDeprecated(classDoc)) {
			return;
		}

		// see if excluded via a tag
		if (ParserHelper.hasTag(classDoc, this.options.getExcludeClassTags())) {
			return;
		}

		// see if excluded via its FQN
		if (this.options.getExcludeModelPrefixes() != null && !this.options.getExcludeModelPrefixes().isEmpty()) {
			for (String prefix : this.options.getExcludeModelPrefixes()) {
				String className = classDoc.qualifiedName();
				if (className.startsWith(prefix)) {
					return;
				}
			}
		}

		// if parameterized then build map of the param vars
		ParameterizedType pt = type.asParameterizedType();
		if (pt != null) {
			Type[] typeArgs = pt.typeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				TypeVariable[] vars = classDoc.typeParameters();
				int i = 0;
				for (TypeVariable var : vars) {
					this.varsToTypes.put(var.qualifiedTypeName(), typeArgs[i]);
					i++;
				}
			}
		}

		Map<String, TypeRef> types = findReferencedTypes(classDoc, nested);
		Map<String, Property> elements = findReferencedElements(classDoc, types, nested);
		if (!elements.isEmpty()) {

			String modelId = nested ? this.translator.typeName(type).value() : this.translator.typeName(type, this.viewClasses).value();

			List<String> requiredFields = null;
			// build list of required fields
			for (Map.Entry<String, TypeRef> fieldEntry : types.entrySet()) {
				String fieldName = fieldEntry.getKey();
				TypeRef fieldDesc = fieldEntry.getValue();
				Boolean required = fieldDesc.required;
				if ((required != null && required.booleanValue()) || (required == null && this.options.isModelFieldsRequiredByDefault())) {
					if (requiredFields == null) {
						requiredFields = new ArrayList<String>();
					}
					requiredFields.add(fieldName);
				}
			}

			this.models.add(new Model(modelId, elements, requiredFields));
			parseNestedModels(types.values());
		}
	}

	/**
	 * This gets the id of the root model
	 * @return The id of the root model
	 */
	public String getRootModelId() {
		return this.translator.typeName(this.rootType, this.viewClasses).value();
	}

	static class TypeRef {

		String rawName;
		String paramCategory;
		String sourceDesc;
		Type type;
		String description;
		String min;
		String max;
		String defaultValue;
		Boolean required;

		TypeRef(String rawName, String paramCategory, String sourceDesc, Type type, String description, String min, String max, String defaultValue,
				Boolean required) {
			super();
			this.rawName = rawName;
			this.paramCategory = paramCategory;
			this.sourceDesc = sourceDesc;
			this.type = type;
			this.description = description;
			this.min = min;
			this.max = max;
			this.defaultValue = defaultValue;
			this.required = required;
		}
	}

	// get list of super classes with highest level first so we process
	// grandparents down, this allows us to override field names via the lower levels
	List<ClassDoc> getClassLineage(ClassDoc classDoc) {
		List<ClassDoc> classes = new ArrayList<ClassDoc>();
		while (classDoc != null) {

			// ignore parent object class
			if (!ParserHelper.hasAncestor(classDoc)) {
				break;
			}

			classes.add(classDoc);
			classDoc = classDoc.superclass();
		}
		Collections.reverse(classes);
		return classes;
	}

	private Map<String, TypeRef> findReferencedTypes(ClassDoc rootClassDoc, boolean nested) {

		Map<String, TypeRef> elements = new HashMap<String, TypeRef>();

		List<ClassDoc> classes = getClassLineage(rootClassDoc);

		// map of raw field names to translated names, translated names may be different
		// due to annotations like XMLElement
		Map<String, String> rawToTranslatedFields = new HashMap<String, String>();

		NameBasedTranslator nameTranslator = new NameBasedTranslator(this.options);

		for (ClassDoc classDoc : classes) {

			AnnotationParser p = new AnnotationParser(classDoc);
			String xmlAccessorType = p.getAnnotationValue("javax.xml.bind.annotation.XmlAccessorType", "value");

			Set<String> customizedFieldNames = new HashSet<String>();

			// add fields
			Set<String> excludeFields = new HashSet<String>();
			if (!"javax.xml.bind.annotation.XmlAccessType.PROPERTY".equals(xmlAccessorType)) {
				FieldDoc[] fieldDocs = classDoc.fields();

				if (fieldDocs != null) {
					for (FieldDoc field : fieldDocs) {

						// ignore static or transient fields or _ prefixed ones
						if (field.isStatic() || field.isTransient() || field.name().charAt(0) == '_') {
							continue;
						}

						String name = this.translator.fieldName(field).value();
						rawToTranslatedFields.put(field.name(), name);
						if (!field.name().equals(name)) {
							customizedFieldNames.add(field.name());
						}

						// ignore deprecated fields
						if (this.options.isExcludeDeprecatedFields() && ParserHelper.isDeprecated(field)) {
							excludeFields.add(field.name());
							continue;
						}

						// ignore fields we are to explicitly exclude
						if (ParserHelper.hasTag(field, this.options.getExcludeFieldTags())) {
							excludeFields.add(field.name());
							continue;
						}

						// ignore fields that are for a different json view
						ClassDoc[] jsonViews = ParserHelper.getJsonViews(field);
						if (!ParserHelper.isItemPartOfView(this.viewClasses, jsonViews)) {
							excludeFields.add(field.name());
							continue;
						}

						if (name != null && !elements.containsKey(name)) {

							Type fieldType = getModelType(field.type(), nested);

							String description = getFieldDescription(field, true);
							String min = getFieldMin(field);
							String max = getFieldMax(field);
							Boolean required = getFieldRequired(field);
							String defaultValue = getFieldDefaultValue(field);

							String paramCategory = this.composite ? ParserHelper.paramTypeOf(false, this.consumesMultipart, field, fieldType, this.options)
									: null;

							elements.put(field.name(), new TypeRef(field.name(), paramCategory, " field: " + field.name(), fieldType, description, min, max,
									defaultValue, required));
						}
					}
				}
			}

			// add methods
			if (!"javax.xml.bind.annotation.XmlAccessType.FIELD".equals(xmlAccessorType)) {
				MethodDoc[] methodDocs = classDoc.methods();
				if (methodDocs != null) {
					for (MethodDoc method : methodDocs) {

						// ignore static methods and private methods
						if (method.isStatic() || method.isPrivate() || method.name().charAt(0) == '_') {
							continue;
						}

						// we tie getters and their corresponding methods together via this rawFieldName
						String rawFieldName = nameTranslator.methodName(method).value();

						String translatedNameViaMethod = this.translator.methodName(method).value();

						if (translatedNameViaMethod != null) {

							boolean isFieldMethod = rawFieldName != null && (elements.containsKey(rawFieldName) || excludeFields.contains(rawFieldName));

							boolean isFieldGetter = isFieldMethod && method.name().startsWith("get");
							boolean isFieldSetter = isFieldMethod && method.name().startsWith("set");

							boolean excludeMethod = false;

							// ignore deprecated methods
							excludeMethod = this.options.isExcludeDeprecatedFields() && ParserHelper.isDeprecated(method);

							// ignore methods we are to explicitly exclude
							if (ParserHelper.hasTag(method, this.options.getExcludeFieldTags())) {
								excludeMethod = true;
							}

							// ignore methods that are for a different json view
							ClassDoc[] jsonViews = ParserHelper.getJsonViews(method);
							if (!ParserHelper.isItemPartOfView(this.viewClasses, jsonViews)) {
								excludeMethod = true;
							}

							if (isFieldGetter || isFieldSetter) {

								// skip if the field has already been excluded
								if (excludeFields.contains(rawFieldName)) {
									continue;
								}

								// skip if this method is to be excluded but also remove the field from the elements
								// so it doesnt appear in the model
								if (excludeMethod) {
									elements.remove(rawFieldName);
									excludeFields.add(rawFieldName);
									continue;
								}

								// see if the field name should be overwritten via annotations on the getter/setter
								// note if the field has its own customizing annotation then that takes precedence
								String nameViaField = rawToTranslatedFields.get(rawFieldName);
								if (!customizedFieldNames.contains(rawFieldName) && !translatedNameViaMethod.equals(nameViaField)) {
									rawToTranslatedFields.put(rawFieldName, translatedNameViaMethod);
									customizedFieldNames.add(rawFieldName);
								}

								TypeRef typeRef = elements.get(rawFieldName);

								// the field was already found e.g. class had a field and this is the getter
								// check if there are tags on the getter we can use to fill in description, min and max
								if (typeRef.description == null) {
									typeRef.description = getFieldDescription(method, isFieldGetter);
								}
								if (typeRef.min == null) {
									typeRef.min = getFieldMin(method);
								}
								if (typeRef.max == null) {
									typeRef.max = getFieldMax(method);
								}
								if (typeRef.defaultValue == null) {
									typeRef.defaultValue = getFieldDefaultValue(method);
								}
								if (typeRef.required == null) {
									typeRef.required = getFieldRequired(method);
								}
								if (this.composite && typeRef.paramCategory == null) {
									typeRef.paramCategory = ParserHelper.paramTypeOf(false, this.consumesMultipart, method, typeRef.type, this.options);
								}

								typeRef.sourceDesc = " method: " + method.name();

							} else {

								// skip if this method is to be excluded
								if (excludeMethod) {
									continue;
								}

								// this is a getter or other method where there wasn't a specific field
								String description = getFieldDescription(method, true);
								String min = getFieldMin(method);
								String max = getFieldMax(method);
								String defaultValue = getFieldDefaultValue(method);
								Boolean required = getFieldRequired(method);

								Type returnType = getModelType(method.returnType(), nested);

								String paramCategory = ParserHelper.paramTypeOf(false, this.consumesMultipart, method, returnType, this.options);

								elements.put(translatedNameViaMethod, new TypeRef(rawFieldName, paramCategory, " method: " + method.name(), returnType,
										description, min, max, defaultValue, required));
							}

						}
					}
				}
			}

		}

		// finally switch the element keys to use the translated field names
		Map<String, TypeRef> res = new HashMap<String, TypeRef>();
		for (Map.Entry<String, TypeRef> entry : elements.entrySet()) {
			String translatedName = rawToTranslatedFields.get(entry.getKey());
			TypeRef typeRef = entry.getValue();
			if (this.composite && typeRef.paramCategory == null) {
				typeRef.paramCategory = "body";
			}
			res.put(translatedName == null ? entry.getKey() : translatedName, typeRef);
		}

		return res;
	}

	private String getFieldDescription(com.sun.javadoc.MemberDoc docItem, boolean useCommentText) {
		// method
		String description = ParserHelper.getTagValue(docItem, this.options.getFieldDescriptionTags());
		if (description == null && useCommentText) {
			description = docItem.commentText();
		}
		if (description == null || description.trim().length() == 0) {
			return null;
		}
		return description.trim();
	}

	private String getFieldMin(com.sun.javadoc.MemberDoc docItem) {
		String val = ParserHelper.getTagValue(docItem, this.options.getFieldMinTags());
		if (val != null && val.trim().length() > 0) {
			return val.trim();
		}
		return null;
	}

	private String getFieldMax(com.sun.javadoc.MemberDoc docItem) {
		String val = ParserHelper.getTagValue(docItem, this.options.getFieldMaxTags());
		if (val != null && val.trim().length() > 0) {
			return val.trim();
		}
		return null;
	}

	private String getFieldDefaultValue(com.sun.javadoc.MemberDoc docItem) {
		String val = ParserHelper.getTagValue(docItem, this.options.getFieldDefaultTags());
		if (val != null && val.trim().length() > 0) {
			return val.trim();
		}
		return null;
	}

	private Boolean getFieldRequired(com.sun.javadoc.MemberDoc docItem) {
		if (ParserHelper.hasTag(docItem, this.options.getRequiredFieldTags())) {
			return Boolean.TRUE;
		}
		if (ParserHelper.hasTag(docItem, this.options.getOptionalFieldTags())) {
			return Boolean.FALSE;
		}
		Boolean notSpecified = null;
		return notSpecified;
	}

	private Map<String, Property> findReferencedElements(ClassDoc classDoc, Map<String, TypeRef> types, boolean nested) {
		Map<String, Property> elements = new HashMap<String, Property>();
		for (Map.Entry<String, TypeRef> entry : types.entrySet()) {
			String typeName = entry.getKey();
			TypeRef typeRef = entry.getValue();
			Type type = typeRef.type;
			ClassDoc typeClassDoc = type.asClassDoc();

			OptionalName propertyTypeFormat = this.translator.typeName(type);
			String propertyType = propertyTypeFormat.value();

			// set enum values
			List<String> allowableValues = ParserHelper.getAllowableValues(typeClassDoc);
			if (allowableValues != null) {
				propertyType = "string";
			}

			Type containerOf = ParserHelper.getContainerType(type, this.varsToTypes);
			String itemsRef = null;
			String itemsType = null;
			String containerTypeOf = containerOf == null ? null : this.translator.typeName(containerOf).value();
			if (containerOf != null) {
				if (ParserHelper.isPrimitive(containerOf, this.options)) {
					itemsType = containerTypeOf;
				} else {
					itemsRef = containerTypeOf;
				}
			}
			Boolean uniqueItems = null;
			if (propertyType.equals("array")) {
				if (ParserHelper.isSet(type.qualifiedTypeName())) {
					uniqueItems = Boolean.TRUE;
				}
			}

			String validationContext = " for the " + typeRef.sourceDesc + " of the class: " + classDoc.name();
			// validate min/max
			ParserHelper.verifyNumericValue(validationContext + " min value.", propertyTypeFormat.value(), propertyTypeFormat.getFormat(), typeRef.min);
			ParserHelper.verifyNumericValue(validationContext + " max value.", propertyTypeFormat.value(), propertyTypeFormat.getFormat(), typeRef.max);

			// if enum and default value check it matches the enum values
			if (allowableValues != null && typeRef.defaultValue != null && !allowableValues.contains(typeRef.defaultValue)) {
				throw new IllegalStateException(" Invalid value for the default value of the " + typeRef.sourceDesc + " it should be one of: "
						+ allowableValues);
			}
			// verify default vs min, max and by itself
			if (typeRef.defaultValue != null) {
				if (typeRef.min == null && typeRef.max == null) {
					// just validate the default
					ParserHelper.verifyValue(validationContext + " default value.", propertyTypeFormat.value(), propertyTypeFormat.getFormat(),
							typeRef.defaultValue);
				}
				// if min/max then default is validated as part of comparison
				if (typeRef.min != null) {
					int comparison = ParserHelper.compareNumericValues(validationContext + " min value.", propertyTypeFormat.value(),
							propertyTypeFormat.getFormat(), typeRef.defaultValue, typeRef.min);
					if (comparison < 0) {
						throw new IllegalStateException("Invalid value for the default value of the " + typeRef.sourceDesc + " it should be >= the minimum: "
								+ typeRef.min);
					}
				}
				if (typeRef.max != null) {
					int comparison = ParserHelper.compareNumericValues(validationContext + " max value.", propertyTypeFormat.value(),
							propertyTypeFormat.getFormat(), typeRef.defaultValue, typeRef.max);
					if (comparison > 0) {
						throw new IllegalStateException("Invalid value for the default value of the " + typeRef.sourceDesc + " it should be <= the maximum: "
								+ typeRef.max);
					}
				}
			}

			Property property = new Property(typeRef.rawName, typeRef.paramCategory, propertyType, propertyTypeFormat.getFormat(), typeRef.description,
					itemsRef, itemsType, uniqueItems, allowableValues, typeRef.min, typeRef.max, typeRef.defaultValue);
			elements.put(typeName, property);
		}
		return elements;
	}

	private void parseNestedModels(Collection<TypeRef> types) {
		for (TypeRef type : types) {
			parseModel(type.type, true);

			// parse paramaterized types
			ParameterizedType pt = type.type.asParameterizedType();
			if (pt != null) {
				Type[] typeArgs = pt.typeArguments();
				if (typeArgs != null) {
					for (Type paramType : typeArgs) {
						parseModel(paramType, true);
					}
				}
			}
		}
	}

	private Type getModelType(Type type, boolean nested) {
		if (type != null) {

			ParameterizedType pt = type.asParameterizedType();
			if (pt != null) {
				Type[] typeArgs = pt.typeArguments();
				if (typeArgs != null && typeArgs.length > 0) {
					// if its a generic wrapper type then return the wrapped type
					if (this.options.getGenericWrapperTypes().contains(type.qualifiedTypeName())) {
						return typeArgs[0];
					}
					// TODO what about maps?
				}
			}
			// if its a ref to a param type replace with the type impl
			Type paramType = ParserHelper.getVarType(type.asTypeVariable(), this.varsToTypes);
			if (paramType != null) {
				return paramType;
			}
		}
		return type;
	}

	/**
	 * This gets the return type for a resource method, it supports wrapper types
	 * @param options
	 * @param type
	 * @return The type to use for the resource method
	 */
	public static Type getReturnType(DocletOptions options, Type type) {
		if (type != null) {
			ParameterizedType pt = type.asParameterizedType();
			if (pt != null) {
				Type[] typeArgs = pt.typeArguments();
				if (typeArgs != null && typeArgs.length > 0) {
					// if its a generic wrapper type then return the wrapped type
					if (options.getGenericWrapperTypes().contains(type.qualifiedTypeName())) {
						return typeArgs[0];
					}
				}
			}
		}
		return type;
	}

	private boolean alreadyStoredType(final Type type) {
		return filter(this.models, new Predicate<Model>() {

			public boolean apply(Model model) {
				return model.getId().equals(ApiModelParser.this.translator.typeName(type).value());
			}
		}).size() > 0;
	}

}
