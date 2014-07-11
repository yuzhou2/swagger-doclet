package com.hypnoticocelot.jaxrs.doclet.parser;

import static com.google.common.base.Objects.firstNonNull;
import static com.google.common.collect.Collections2.transform;
import static com.hypnoticocelot.jaxrs.doclet.parser.AnnotationHelper.parsePath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.hypnoticocelot.jaxrs.doclet.DocletOptions;
import com.hypnoticocelot.jaxrs.doclet.model.Api;
import com.hypnoticocelot.jaxrs.doclet.model.Method;
import com.hypnoticocelot.jaxrs.doclet.model.Model;
import com.hypnoticocelot.jaxrs.doclet.model.Operation;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Type;

public class ApiClassParser {

	private final DocletOptions options;
	private final ClassDoc classDoc;
	private final String rootPath;
	private final Set<Model> models;
	private final Collection<ClassDoc> classes;
	private final Method parentMethod;

	public ApiClassParser(DocletOptions options, ClassDoc classDoc, Collection<ClassDoc> classes) {
		this.options = options;
		this.classDoc = classDoc;
		this.rootPath = firstNonNull(parsePath(classDoc.annotations()), "");
		this.models = new LinkedHashSet<Model>();
		this.classes = classes;
		this.parentMethod = null;
	}

	/**
	 * Creates sub-resource class parser.
	 * @param parentMethod method that creates the sub-resource.
	 */
	public ApiClassParser(DocletOptions options, ClassDoc classDoc, Collection<ClassDoc> classes, Method parentMethod) {
		this.options = options;
		this.classDoc = classDoc;
		this.rootPath = firstNonNull(parsePath(classDoc.annotations()), "");
		this.models = new LinkedHashSet<Model>();
		this.classes = classes;
		this.parentMethod = parentMethod;
	}

	public String getRootPath() {
		return this.rootPath;
	}

	public List<Api> parse() {
		List<Api> apis = new ArrayList<Api>();
		Map<String, Collection<Method>> apiMethods = new HashMap<String, Collection<Method>>();

		ClassDoc currentClassDoc = this.classDoc;
		while (currentClassDoc != null) {

			// read default error type for class
			String defaultErrorTypeClass = AnnotationHelper.getTagValue(currentClassDoc, this.options.getDefaultErrorTypeTags());
			Type defaultErrorType = AnnotationHelper.findModel(this.classes, defaultErrorTypeClass);

			if (this.options.isParseModels() && defaultErrorType != null) {
				this.models.addAll(new ApiModelParser(this.options, this.options.getTranslator(), defaultErrorType).parse());
			}

			for (MethodDoc method : currentClassDoc.methods()) {
				ApiMethodParser methodParser = this.parentMethod == null ? new ApiMethodParser(this.options, this.rootPath, method, this.classes,
						defaultErrorTypeClass) : new ApiMethodParser(this.options, this.parentMethod, method, this.classes, defaultErrorTypeClass);
				Method parsedMethod = methodParser.parse();
				if (parsedMethod == null) {
					continue;
				}
				if (parsedMethod.isSubResource()) {
					ClassDoc subResourceClassDoc = lookUpClassDoc(method.returnType());
					if (subResourceClassDoc != null) {
						// delete class from the dictionary to handle recursive sub-resources
						Collection<ClassDoc> shrunkClasses = new ArrayList<ClassDoc>(this.classes);
						shrunkClasses.remove(currentClassDoc);
						// recursively parse the sub-resource class
						ApiClassParser subResourceParser = new ApiClassParser(this.options, subResourceClassDoc, shrunkClasses, parsedMethod);
						apis.addAll(subResourceParser.parse());
						this.models.addAll(subResourceParser.models());
					}
					continue;
				}
				this.models.addAll(methodParser.models());

				String realPath = parsedMethod.getPath();
				Collection<Method> matchingMethods = apiMethods.get(realPath);
				if (matchingMethods == null) {
					matchingMethods = new ArrayList<Method>();
					apiMethods.put(realPath, matchingMethods);
				}
				matchingMethods.add(parsedMethod);
			}
			currentClassDoc = currentClassDoc.superclass();
		}

		for (Map.Entry<String, Collection<Method>> apiEntries : apiMethods.entrySet()) {
			Collection<Operation> operations = new ArrayList<Operation>(transform(apiEntries.getValue(), new Function<Method, Operation>() {

				public Operation apply(Method method) {
					return new Operation(method);
				}
			}));
			apis.add(new Api(apiEntries.getKey(), "", operations));
		}
		return apis;
	}

	private ClassDoc lookUpClassDoc(Type type) {
		for (ClassDoc subResourceClassDoc : this.classes) {
			if (subResourceClassDoc.qualifiedTypeName().equals(type.qualifiedTypeName())) {
				return subResourceClassDoc;
			}
		}
		return null;
	}

	public Collection<Model> models() {
		return this.models;
	}

}
