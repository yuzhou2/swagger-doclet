package com.carma.swagger.doclet.parser;

import static com.carma.swagger.doclet.parser.AnnotationHelper.parsePath;
import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.transform;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.carma.swagger.doclet.DocletOptions;
import com.carma.swagger.doclet.model.ApiParameter;
import com.carma.swagger.doclet.model.ApiResponseMessage;
import com.carma.swagger.doclet.model.HttpMethod;
import com.carma.swagger.doclet.model.Method;
import com.carma.swagger.doclet.model.Model;
import com.carma.swagger.doclet.model.Oauth2Scope;
import com.carma.swagger.doclet.model.OperationAuthorizations;
import com.carma.swagger.doclet.translator.Translator;
import com.carma.swagger.doclet.translator.Translator.OptionalName;
import com.google.common.base.Function;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;

public class ApiMethodParser {

	// pattern that can match a code, a description and an optional response model type
	private static final Pattern[] RESPONSE_MESSAGE_PATTERNS = new Pattern[] { Pattern.compile("(\\d+)([^`]+)(`.*)?") };

	private static String trimLeadingChars(String str, char... trimChars) {
		if (str == null || str.trim().isEmpty()) {
			return str;
		}
		StringBuilder newStr = new StringBuilder();
		boolean foundNonTrimChar = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (foundNonTrimChar) {
				newStr.append(c);
			} else {
				if (Character.isWhitespace(c)) {
					// trim
				} else {
					// see if a non trim char, if so add it and set flag
					boolean isTrimChar = false;
					for (char trimC : trimChars) {
						if (c == trimC) {
							isTrimChar = true;
							break;
						}
					}
					if (!isTrimChar) {
						foundNonTrimChar = true;
						newStr.append(c);
					}
				}
			}
		}
		return newStr.length() == 0 ? null : newStr.toString().trim();
	}

	private static String commentForParameter(MethodDoc method, Parameter parameter) {
		for (ParamTag tag : method.paramTags()) {
			if (tag.parameterName().equals(parameter.name())) {
				return tag.parameterComment();
			}
		}
		return "";
	}

	private final DocletOptions options;
	private final Translator translator;
	private final String parentPath;
	private final MethodDoc methodDoc;
	private final Set<Model> models;
	private final HttpMethod httpMethod;
	private final Method parentMethod;
	private final Collection<ClassDoc> classes;
	private final String classDefaultErrorType;
	private final String methodDefaultErrorType;

	public ApiMethodParser(DocletOptions options, String parentPath, MethodDoc methodDoc, Collection<ClassDoc> classes, String classDefaultErrorType) {
		this.options = options;
		this.translator = options.getTranslator();
		this.parentPath = parentPath;
		this.methodDoc = methodDoc;
		this.models = new LinkedHashSet<Model>();
		this.httpMethod = HttpMethod.fromMethod(methodDoc);
		this.parentMethod = null;
		this.classes = classes;
		this.classDefaultErrorType = classDefaultErrorType;
		this.methodDefaultErrorType = AnnotationHelper.getTagValue(methodDoc, options.getDefaultErrorTypeTags());
	}

	public ApiMethodParser(DocletOptions options, Method parentMethod, MethodDoc methodDoc, Collection<ClassDoc> classes, String classDefaultErrorType) {
		this.options = options;
		this.translator = options.getTranslator();
		this.methodDoc = methodDoc;
		this.models = new LinkedHashSet<Model>();
		this.httpMethod = HttpMethod.fromMethod(methodDoc);
		this.parentPath = parentMethod.getPath();
		this.parentMethod = parentMethod;
		this.classes = classes;
		this.classDefaultErrorType = classDefaultErrorType;
		this.methodDefaultErrorType = AnnotationHelper.getTagValue(methodDoc, options.getDefaultErrorTypeTags());
	}

	public Method parse() {
		String methodPath = firstNonNull(parsePath(this.methodDoc.annotations()), "");
		if (this.httpMethod == null && methodPath.isEmpty()) {
			return null;
		}

		// check if deprecated and exclude if set to do so
		boolean deprecated = false;
		if (AnnotationHelper.isDeprecated(this.methodDoc)) {
			if (this.options.isExcludeDeprecatedOperations()) {
				return null;
			}
			deprecated = true;
		}

		// exclude if it has exclusion tags
		if (AnnotationHelper.hasTag(this.methodDoc, this.options.getExcludeOperationTags())) {
			return null;
		}

		String path = this.parentPath + methodPath;

		// build params
		List<ApiParameter> parameters = this.generateParameters();

		// build response messages
		List<ApiResponseMessage> responseMessages = generateResponseMessages();

		// ************************************
		// Return type
		// ************************************
		Type returnType = this.methodDoc.returnType();
		// first check if its a wrapper type and if so replace with the wrapped type
		returnType = firstNonNull(ApiModelParser.getReturnType(this.options, returnType), returnType);

		String returnTypeName = this.translator.typeName(returnType).value();
		Type modelType = returnType;

		ClassDoc[] viewClasses = AnnotationHelper.getJsonViews(this.methodDoc);

		// now see if it is a collection if so the return type will be array and the
		// containerOf will be added to the model

		String returnTypeItemsRef = null;
		String returnTypeItemsType = null;
		Type containerOf = AnnotationHelper.getContainerType(returnType, null);

		if (containerOf != null) {
			// its a collection, add the containter of type to the model
			modelType = containerOf;
			// set the items type or ref
			if (AnnotationHelper.isPrimitive(containerOf)) {
				returnTypeItemsType = this.translator.typeName(containerOf).value();
			} else {
				returnTypeItemsRef = this.translator.typeName(containerOf, viewClasses).value();
			}

		} else {
			// if its not a container then adjust the return type name for any views
			returnTypeName = this.translator.typeName(returnType, viewClasses).value();

			// look for a custom return type, this is useful where we return a jaxrs Response in the method signature
			// but typically return a different object in its entity (such as for a 201 created response)
			String customReturnTypeName = AnnotationHelper.getTagValue(this.methodDoc, this.options.getResponseTypeTags());
			NameToType nameToType = readCustomReturnType(customReturnTypeName);
			if (nameToType != null) {
				returnTypeName = nameToType.translatedName;
				returnType = nameToType.type;
			}

		}

		if (this.options.isParseModels()) {
			this.models.addAll(new ApiModelParser(this.options, this.translator, modelType, viewClasses).parse());
		}

		// ************************************
		// Summary and notes
		// ************************************
		// First Sentence of Javadoc method description
		Tag[] fst = this.methodDoc.firstSentenceTags();
		StringBuilder sentences = new StringBuilder();
		for (Tag tag : fst) {
			sentences.append(tag.text());
		}
		String firstSentences = sentences.toString();

		// default plugin behaviour
		String summary = firstSentences;
		String notes = this.methodDoc.commentText();
		notes = notes.replace(summary, "");

		// look for custom notes/summary tags to use instead
		String customNotes = AnnotationHelper.getTagValue(this.methodDoc, this.options.getOperationNotesTags());
		if (customNotes != null) {
			notes = customNotes;
		}
		String customSummary = AnnotationHelper.getTagValue(this.methodDoc, this.options.getOperationSummaryTags());
		if (customSummary != null) {
			summary = customSummary;
		}

		// Auth support
		OperationAuthorizations authorizations = generateAuthorizations();

		// ************************************
		// Produces & consumes
		// ************************************
		List<String> consumes = AnnotationHelper.getConsumes(this.methodDoc);
		List<String> produces = AnnotationHelper.getProduces(this.methodDoc);

		// final result!
		return new Method(this.httpMethod, this.methodDoc.name(), path, parameters, responseMessages, summary, notes, returnTypeName, returnTypeItemsRef,
				returnTypeItemsType, consumes, produces, authorizations, deprecated);
	}

	private OperationAuthorizations generateAuthorizations() {
		OperationAuthorizations authorizations = null;

		// build map of scopes from the api auth
		Map<String, Oauth2Scope> apiScopes = new HashMap<String, Oauth2Scope>();
		if (this.options.getApiAuthorizations() != null && this.options.getApiAuthorizations().getOauth2() != null
				&& this.options.getApiAuthorizations().getOauth2().getScopes() != null) {
			List<Oauth2Scope> scopes = this.options.getApiAuthorizations().getOauth2().getScopes();
			if (scopes != null) {
				for (Oauth2Scope scope : scopes) {
					apiScopes.put(scope.getScope(), scope);
				}
			}
		}
		// see if method has a tag that implies there is no authentication
		// in this case set the authentication object to {} to indicate we override
		// at the operation level
		// a) if method has an explicit unauth tag
		if (AnnotationHelper.hasTag(this.methodDoc, this.options.getUnauthOperationTags())) {
			authorizations = new OperationAuthorizations();
		} else {

			// otherwise if method has scope tags then add those to indicate method requires scope
			List<String> scopeValues = AnnotationHelper.getTagValues(this.methodDoc, this.options.getOperationScopeTags());
			if (scopeValues != null) {
				List<Oauth2Scope> oauth2Scopes = new ArrayList<Oauth2Scope>();
				for (String scopeVal : scopeValues) {
					Oauth2Scope apiScope = apiScopes.get(scopeVal);
					if (apiScope == null) {
						throw new IllegalStateException("The scope: " + scopeVal + " was referenced in the method: " + this.methodDoc
								+ " but this scope was not part of the API service.json level authorization object.");
					}
					oauth2Scopes.add(apiScope);
				}
				authorizations = new OperationAuthorizations(oauth2Scopes);
			}

			// if not scopes see if its auth and whether we need to add default scope to it
			if (scopeValues == null || scopeValues.isEmpty()) {
				// b) if method has an auth tag that starts with one of the known values that indicates whether auth required.
				String authSpec = AnnotationHelper.getTagValue(this.methodDoc, this.options.getAuthOperationTags());
				if (authSpec != null) {

					boolean unauthFound = false;
					for (String unauthValue : this.options.getUnauthOperationTagValues()) {
						if (authSpec.toLowerCase().startsWith(unauthValue.toLowerCase())) {
							authorizations = new OperationAuthorizations();
							unauthFound = true;
							break;
						}
					}
					if (!unauthFound) {
						// its deemed to require authentication, however there is no explicit scope so we need to use
						// the default scopes
						List<String> defaultScopes = this.options.getAuthOperationScopes();
						if (defaultScopes != null && !defaultScopes.isEmpty()) {
							List<Oauth2Scope> oauth2Scopes = new ArrayList<Oauth2Scope>();
							for (String scopeVal : defaultScopes) {
								Oauth2Scope apiScope = apiScopes.get(scopeVal);
								if (apiScope == null) {
									throw new IllegalStateException("The default scope: " + scopeVal + " needed for the authorized method: " + this.methodDoc
											+ " was not part of the API service.json level authorization object.");
								}
								oauth2Scopes.add(apiScope);
							}
							authorizations = new OperationAuthorizations(oauth2Scopes);
						}
					}
				}
			}

		}
		return authorizations;
	}

	private List<ApiResponseMessage> generateResponseMessages() {
		List<ApiResponseMessage> responseMessages = new LinkedList<ApiResponseMessage>();

		List<String> responseMessageTags = new ArrayList<String>(this.options.getResponseMessageTags());

		for (String tagName : responseMessageTags) {
			for (Tag tagValue : this.methodDoc.tags(tagName)) {
				boolean matched = false;

				if (!matched) {
					for (Pattern pattern : RESPONSE_MESSAGE_PATTERNS) {
						Matcher matcher = pattern.matcher(tagValue.text());
						if (matcher.find()) {

							int statusCode = Integer.valueOf(matcher.group(1).trim());
							// trim special chars the desc may start with
							String desc = trimLeadingChars(matcher.group(2), '|', '-');

							// see if it has a custom response model
							String responseModelClass = null;
							if (matcher.groupCount() > 2) {
								responseModelClass = trimLeadingChars(matcher.group(3), '`');
							}
							// for errors, if no custom one use the method level one if there is one
							if (statusCode >= 400) {
								if (responseModelClass == null) {
									responseModelClass = this.methodDefaultErrorType;
								}
								// for errors, if no custom one use the class level one if there is one
								if (responseModelClass == null) {
									responseModelClass = this.classDefaultErrorType;
								}
							}

							String responseModel = null;
							if (responseModelClass != null) {
								Type responseType = AnnotationHelper.findModel(this.classes, responseModelClass);
								if (responseType != null) {
									responseModel = this.translator.typeName(responseType).value();
									if (this.options.isParseModels()) {
										this.models.addAll(new ApiModelParser(this.options, this.translator, responseType).parse());
									}
								}
							}

							responseMessages.add(new ApiResponseMessage(statusCode, desc, responseModel));
							matched = true;
							break;
						}
					}

				}

			}
		}
		return responseMessages;
	}

	private List<ApiParameter> generateParameters() {
		// parameters
		List<ApiParameter> parameters = new LinkedList<ApiParameter>();

		// read required and optional params
		List<String> optionalParams = AnnotationHelper.getTagCsvValues(this.methodDoc, this.options.getOptionalParamsTags());
		List<String> requiredParams = AnnotationHelper.getTagCsvValues(this.methodDoc, this.options.getRequiredParamsTags());

		// read exclude params
		List<String> excludeParams = AnnotationHelper.getTagCsvValues(this.methodDoc, this.options.getExcludeParamsTags());

		// read csv params
		List<String> csvParams = AnnotationHelper.getTagCsvValues(this.methodDoc, this.options.getCsvParamsTags());

		for (Parameter parameter : this.methodDoc.parameters()) {
			if (!shouldIncludeParameter(this.httpMethod, excludeParams, parameter)) {
				continue;
			}

			Type paramType = parameter.type();
			String paramCategory = AnnotationHelper.paramTypeOf(parameter);

			// look for a custom input type for body params
			if ("body".equals(paramCategory)) {
				String customParamType = AnnotationHelper.getTagValue(this.methodDoc, this.options.getInputTypeTags());
				paramType = readCustomParamType(customParamType, paramType);
			}

			if (this.options.isParseModels()) {
				this.models.addAll(new ApiModelParser(this.options, this.translator, paramType).parse());
			}

			OptionalName paramTypeFormat = this.translator.typeName(paramType);
			String typeName = paramTypeFormat.value();

			List<String> allowableValues = null;
			ClassDoc typeClassDoc = parameter.type().asClassDoc();
			if (typeClassDoc != null && typeClassDoc.isEnum()) {
				typeName = "string";
				allowableValues = transform(asList(typeClassDoc.enumConstants()), new Function<FieldDoc, String>() {

					public String apply(FieldDoc input) {
						return input.name();
					}
				});
			}

			// set whether its a csv param
			Boolean allowMultiple = null;
			if (csvParams != null) {
				if ("query".equals(paramCategory) || "path".equals(paramCategory) || "header".equals(paramCategory)) {
					if (csvParams.contains(parameter.name())) {
						allowMultiple = Boolean.TRUE;
					}
				}
			}

			// set whether the parameter is required or not
			Boolean required = null;
			// if its a path param then its required as per swagger spec
			if ("path".equals(paramCategory)) {
				required = Boolean.TRUE;
			}
			// if its in the required list then its required
			else if (requiredParams != null && requiredParams.contains(parameter.name())) {
				required = Boolean.TRUE;
			}
			// else if its in the optional list its optional
			else if (optionalParams != null && optionalParams.contains(parameter.name())) {
				// leave as null as this is equivalent to false but doesnt add to the json
			}
			// else if its a body param its required
			else if ("body".equals(paramCategory)) {
				required = Boolean.TRUE;
			}
			// otherwise its optional
			else {
				// leave as null as this is equivalent to false but doesnt add to the json
			}

			// FIXME support min and max param values
			String minimum = null;
			String maximum = null;

			// set collection related fields
			// TODO: consider supporting parameterized collections as api parameters...
			Type containerOf = AnnotationHelper.getContainerType(paramType, null);
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
			if (typeName.equals("array")) {
				if (AnnotationHelper.isSet(paramType.qualifiedTypeName())) {
					uniqueItems = Boolean.TRUE;
				}
			}

			ApiParameter param = new ApiParameter(paramCategory, AnnotationHelper.paramNameOf(parameter), required, allowMultiple, typeName,
					paramTypeFormat.getFormat(), commentForParameter(this.methodDoc, parameter), itemsRef, itemsType, uniqueItems, allowableValues, minimum,
					maximum);

			parameters.add(param);
		}

		// parent method parameters are inherited
		if (this.parentMethod != null) {
			parameters.addAll(this.parentMethod.getParameters());
		}

		return parameters;
	}

	/**
	 * This gets the parsed models found for this method
	 * @return the set of parsed models found for this method
	 */
	public Set<Model> models() {
		return this.models;
	}

	private Type readCustomParamType(String customTypeName, Type defaultType) {
		if (customTypeName != null) {
			// lookup the type from the doclet classes
			Type customType = AnnotationHelper.findModel(this.classes, customTypeName);
			if (customType != null) {
				// also add this custom return type to the models
				if (this.options.isParseModels()) {
					this.models.addAll(new ApiModelParser(this.options, this.translator, customType).parse());
				}
				return customType;
			}
		}
		return defaultType;
	}

	static class NameToType {

		String translatedName;
		Type type;
	}

	NameToType readCustomReturnType(String customTypeName) {
		if (customTypeName != null) {
			// lookup the type from the doclet classes
			Type customType = AnnotationHelper.findModel(this.classes, customTypeName);
			if (customType != null) {
				customType = firstNonNull(ApiModelParser.getReturnType(this.options, customType), customType);
				String translated = this.translator.typeName(customType).value();
				if (translated != null) {
					NameToType res = new NameToType();
					res.translatedName = translated;
					res.type = customType;
					return res;
				}
			}
		}
		return null;
	}

	private boolean shouldIncludeParameter(HttpMethod httpMethod, List<String> excludeParams, Parameter parameter) {
		List<AnnotationDesc> allAnnotations = Arrays.asList(parameter.annotations());

		// remove any params annotated with exclude param annotations e.g. jaxrs Context
		Collection<AnnotationDesc> excluded = filter(allAnnotations, new AnnotationHelper.ExcludedAnnotations(this.options.getExcludeParamAnnotations()));
		if (!excluded.isEmpty()) {
			return false;
		}

		// remove any params with exclude param tags
		if (excludeParams != null && excludeParams.contains(parameter.name())) {
			return false;
		}

		// remove any deprecated params
		if (this.options.isExcludeDeprecatedParams() && AnnotationHelper.hasDeprecated(parameter.annotations())) {
			return false;
		}

		Collection<AnnotationDesc> jaxRsAnnotations = filter(allAnnotations, new AnnotationHelper.JaxRsAnnotations());
		if (!jaxRsAnnotations.isEmpty()) {
			return true;
		}

		return (allAnnotations.isEmpty() || httpMethod == HttpMethod.POST || httpMethod == HttpMethod.PUT);
	}

}
