package com.hypnoticocelot.jaxrs.doclet.parser;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.transform;
import static com.hypnoticocelot.jaxrs.doclet.parser.AnnotationHelper.parsePath;
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

import com.google.common.base.Function;
import com.hypnoticocelot.jaxrs.doclet.DocletOptions;
import com.hypnoticocelot.jaxrs.doclet.model.ApiParameter;
import com.hypnoticocelot.jaxrs.doclet.model.ApiResponseMessage;
import com.hypnoticocelot.jaxrs.doclet.model.HttpMethod;
import com.hypnoticocelot.jaxrs.doclet.model.Method;
import com.hypnoticocelot.jaxrs.doclet.model.Model;
import com.hypnoticocelot.jaxrs.doclet.model.Oauth2Scope;
import com.hypnoticocelot.jaxrs.doclet.model.OperationAuthorizations;
import com.hypnoticocelot.jaxrs.doclet.translator.Translator;
import com.hypnoticocelot.jaxrs.doclet.translator.Translator.OptionalName;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.SeeTag;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;

public class ApiMethodParser {

	private static final Pattern[] THREE_GROUP_RESPONSE_MESSAGE_PATTERNS = new Pattern[] { Pattern.compile("(\\d+)\\|(.*)\\|(.+)") };

	private static final Pattern[] TWO_GROUP_RESPONSE_MESSAGE_PATTERNS = new Pattern[] { Pattern.compile("(\\d+) (.+)"), Pattern.compile("(\\d+)\\|(.+)") };

	private final DocletOptions options;
	private final Translator translator;
	private final String parentPath;
	private final MethodDoc methodDoc;
	private final Set<Model> models;
	private final HttpMethod httpMethod;
	private final Method parentMethod;

	public ApiMethodParser(DocletOptions options, String parentPath, MethodDoc methodDoc) {
		this.options = options;
		this.translator = options.getTranslator();
		this.parentPath = parentPath;
		this.methodDoc = methodDoc;
		this.models = new LinkedHashSet<Model>();
		this.httpMethod = HttpMethod.fromMethod(methodDoc);
		this.parentMethod = null;
	}

	public ApiMethodParser(DocletOptions options, Method parentMethod, MethodDoc methodDoc) {
		this.options = options;
		this.translator = options.getTranslator();
		this.methodDoc = methodDoc;
		this.models = new LinkedHashSet<Model>();
		this.httpMethod = HttpMethod.fromMethod(methodDoc);
		this.parentPath = parentMethod.getPath();
		this.parentMethod = parentMethod;
	}

	public Method parse() {
		String methodPath = firstNonNull(parsePath(this.methodDoc.annotations()), "");
		if (this.httpMethod == null && methodPath.isEmpty()) {
			return null;
		}

		// check we shouldnt exclude from the api doc
		if (this.options.getExcludeMethodTags() != null) {
			for (String excludeTag : this.options.getExcludeMethodTags()) {
				Tag[] tags = this.methodDoc.tags(excludeTag);
				if (tags != null && tags.length > 0) {
					return null;
				}
			}
		}

		String path = this.parentPath + methodPath;

		// parameters
		List<ApiParameter> parameters = new LinkedList<ApiParameter>();
		for (Parameter parameter : this.methodDoc.parameters()) {
			if (!shouldIncludeParameter(this.httpMethod, parameter)) {
				continue;
			}
			if (this.options.isParseModels()) {
				this.models.addAll(new ApiModelParser(this.options, this.translator, parameter.type()).parse());
			}

			OptionalName paramTypeFormat = this.translator.typeName(parameter.type());
			String type = paramTypeFormat.value();

			List<String> allowableValues = null;
			ClassDoc typeClassDoc = parameter.type().asClassDoc();
			if (typeClassDoc != null && typeClassDoc.isEnum()) {
				type = "string";
				allowableValues = transform(asList(typeClassDoc.enumConstants()), new Function<FieldDoc, String>() {

					public String apply(FieldDoc input) {
						return input.name();
					}
				});
			}
			String paramType = AnnotationHelper.paramTypeOf(parameter);
			Boolean allowMultiple = null;
			if ("query".equals(paramType)) {
				// TODO: support config
				allowMultiple = Boolean.FALSE;
			}

			ApiParameter param = new ApiParameter(paramType, AnnotationHelper.paramNameOf(parameter), commentForParameter(this.methodDoc, parameter), type,
					paramTypeFormat.getFormat(), allowableValues, allowMultiple);
			parameters.add(param);
		}

		// parent method parameters are inherited
		if (this.parentMethod != null) {
			parameters.addAll(this.parentMethod.getParameters());
		}

		// response messages
		List<ApiResponseMessage> responseMessages = new LinkedList<ApiResponseMessage>();

		List<String> responseTags = new ArrayList<String>(this.options.getErrorTags());
		responseTags.addAll(this.options.getSuccessTags());

		for (String tagName : responseTags) {
			for (Tag tagValue : this.methodDoc.tags(tagName)) {
				// check 3 group patterns
				boolean matched = false;
				for (Pattern pattern : THREE_GROUP_RESPONSE_MESSAGE_PATTERNS) {
					Matcher matcher = pattern.matcher(tagValue.text());
					if (matcher.find()) {
						String errorCode = matcher.group(2);
						String desc = matcher.group(3);
						if (errorCode.trim().length() > 0 && !errorCode.trim().equals("-")) {
							desc = errorCode + "|" + desc;
						}
						responseMessages.add(new ApiResponseMessage(Integer.valueOf(matcher.group(1)), desc));
						matched = true;
						break;
					}
				}

				// check 2 group patterns
				if (!matched) {
					for (Pattern pattern : TWO_GROUP_RESPONSE_MESSAGE_PATTERNS) {
						Matcher matcher = pattern.matcher(tagValue.text());
						if (matcher.find()) {
							responseMessages.add(new ApiResponseMessage(Integer.valueOf(matcher.group(1)), matcher.group(2)));
							matched = true;
							break;
						}
					}
				}
			}
		}

		// return type
		// TODO: check this and support custom response model per response code
		Type type = this.methodDoc.returnType();
		String returnType = this.translator.typeName(type).value();

		// look for a custom return type, this is useful where we return a jaxrs Response
		// but typically return a different object
		String customReturnType = AnnotationHelper.getTagValue(this.methodDoc, this.options.getResponseTypeTags());

		if (customReturnType != null) {
			// find a corresponding @see tag which we can use to obtain a
			// Type reference
			SeeTag[] seeTags = this.methodDoc.seeTags();
			if (seeTags != null) {
				for (SeeTag seeTag : seeTags) {
					if (customReturnType.equals(seeTag.referencedClassName())) {
						type = seeTag.referencedClass();
						returnType = this.translator.typeName(type).value();
						break;
					}
				}
			}
		}

		if (this.options.isParseModels()) {
			this.models.addAll(new ApiModelParser(this.options, this.translator, type).parse());
		}

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
		String customNotes = AnnotationHelper.getTagValue(this.methodDoc, this.options.getMethodCommentTags());
		if (customNotes != null) {
			notes = customNotes;
		}
		String customSummary = AnnotationHelper.getTagValue(this.methodDoc, this.options.getMethodSummaryTags());
		if (customSummary != null) {
			summary = customSummary;
		}

		List<String> consumes = AnnotationHelper.getConsumes(this.methodDoc);
		List<String> produces = AnnotationHelper.getProduces(this.methodDoc);

		// ************************************
		// Auth support
		// ************************************
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

		return new Method(this.httpMethod, this.methodDoc.name(), path, parameters, responseMessages, summary, notes, returnType, consumes, produces,
				authorizations);
	}

	public Set<Model> models() {
		return this.models;
	}

	private boolean shouldIncludeParameter(HttpMethod httpMethod, Parameter parameter) {
		List<AnnotationDesc> allAnnotations = Arrays.asList(parameter.annotations());
		Collection<AnnotationDesc> excluded = filter(allAnnotations, new AnnotationHelper.ExcludedAnnotations(this.options));
		if (!excluded.isEmpty()) {
			return false;
		}

		Collection<AnnotationDesc> jaxRsAnnotations = filter(allAnnotations, new AnnotationHelper.JaxRsAnnotations());
		if (!jaxRsAnnotations.isEmpty()) {
			return true;
		}

		return (allAnnotations.isEmpty() || httpMethod == HttpMethod.POST);
	}

	private String commentForParameter(MethodDoc method, Parameter parameter) {
		for (ParamTag tag : method.paramTags()) {
			if (tag.parameterName().equals(parameter.name())) {
				return tag.parameterComment();
			}
		}
		return "";
	}

}
