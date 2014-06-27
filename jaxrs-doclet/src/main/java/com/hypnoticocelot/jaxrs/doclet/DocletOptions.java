package com.hypnoticocelot.jaxrs.doclet;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hypnoticocelot.jaxrs.doclet.model.ApiAuthorizations;
import com.hypnoticocelot.jaxrs.doclet.model.ApiInfo;
import com.hypnoticocelot.jaxrs.doclet.translator.AnnotationAwareTranslator;
import com.hypnoticocelot.jaxrs.doclet.translator.FirstNotNullTranslator;
import com.hypnoticocelot.jaxrs.doclet.translator.NameBasedTranslator;
import com.hypnoticocelot.jaxrs.doclet.translator.Translator;

public class DocletOptions {

	public static final String DEFAULT_SWAGGER_UI_ZIP_PATH = "n/a";

	private static <T> T loadModelFromJson(String option, String path, Class<T> resourceClass) {
		File file = new File(path);
		checkArgument(file.isFile(), "Path after " + option + " (" + file.getAbsolutePath() + ") is expected to be an existing file!");
		// load it as json and build the object from it
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(file));
			return new ObjectMapper().readValue(is, resourceClass);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Failed to read model file: " + ex.getMessage(), ex);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ex) {
					// ignore
				}
			}
		}
	}

	public static DocletOptions parse(String[][] options) {
		DocletOptions parsedOptions = new DocletOptions();
		for (String[] option : options) {
			if (option[0].equals("-d")) {
				parsedOptions.outputDirectory = new File(option[1]);
				checkArgument(parsedOptions.outputDirectory.isDirectory(), "Path after -d is expected to be a directory!");
			} else if (option[0].equals("-apiAuthorizationsFile")) {
				parsedOptions.apiAuthorizations = loadModelFromJson("-apiAuthorizationsFile", option[1], ApiAuthorizations.class);
			} else if (option[0].equals("-apiInfoFile")) {
				parsedOptions.apiInfo = loadModelFromJson("-apiInfoFile", option[1], ApiInfo.class);
			} else if (option[0].equals("-docBasePath")) {
				parsedOptions.docBasePath = option[1];
			} else if (option[0].equals("-apiBasePath")) {
				parsedOptions.apiBasePath = option[1];
			} else if (option[0].equals("-apiVersion")) {
				parsedOptions.apiVersion = option[1];
			} else if (option[0].equals("-swaggerUiZipPath")) {
				parsedOptions.swaggerUiZipPath = option[1];
			} else if (option[0].equals("-excludeAnnotationClasses")) {
				parsedOptions.excludeAnnotationClasses.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-disableModels")) {
				parsedOptions.parseModels = false;
			} else if (option[0].equals("-disableCopySwaggerUi")) {
				parsedOptions.includeSwaggerUi = false;
			} else if (option[0].equals("-crossClassResources")) {
				parsedOptions.crossClassResources = true;
			} else if (option[0].equals("-sortApisByPath")) {
				parsedOptions.sortApisByPath = true;
			} else if (option[0].equals("-sortResourcesByPath")) {
				parsedOptions.sortResourcesByPath = true;
			} else if (option[0].equals("-sortResourcesByPriority")) {
				parsedOptions.sortResourcesByPriority = true;
			} else if (option[0].equals("-errorTags")) {
				parsedOptions.errorTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-successTags")) {
				parsedOptions.successTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-typesToTreatAsOpaque")) {
				parsedOptions.typesToTreatAsOpaque.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-excludeMethodTags")) {
				parsedOptions.excludeMethodTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-resourceTags")) {
				parsedOptions.resourceTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-responseTypeTags")) {
				parsedOptions.responseTypeTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-methodCommentTags")) {
				parsedOptions.methodCommentTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-methodSummaryTags")) {
				parsedOptions.methodSummaryTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-propertyCommentTags")) {
				parsedOptions.propertyCommentTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-propertyMinTags")) {
				parsedOptions.propertyMinTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-propertyMaxTags")) {
				parsedOptions.propertyMaxTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-unauthOperationTags")) {
				parsedOptions.unauthOperationTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-authOperationTags")) {
				parsedOptions.authOperationTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-unauthOperationTagValues")) {
				parsedOptions.unauthOperationTagValues.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-authOperationScopes")) {
				parsedOptions.authOperationScopes.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-operationScopeTags")) {
				parsedOptions.operationScopeTags.addAll(asList(copyOfRange(option, 1, option.length)));
			}
		}
		return parsedOptions;
	}

	private File outputDirectory;
	private String docBasePath = "http://localhost:8080";
	private String apiBasePath = "http://localhost:8080";
	private String swaggerUiZipPath = DEFAULT_SWAGGER_UI_ZIP_PATH;
	private String apiVersion = "0";

	private boolean includeSwaggerUi = true;

	private List<String> typesToTreatAsOpaque;
	private List<String> errorTags;
	private List<String> successTags;
	private List<String> excludeAnnotationClasses;
	private List<String> excludeMethodTags;
	private List<String> resourceTags;
	private List<String> responseTypeTags;
	private List<String> methodCommentTags;
	private List<String> methodSummaryTags;
	private List<String> propertyCommentTags;

	private List<String> propertyMinTags;
	private List<String> propertyMaxTags;

	private List<String> unauthOperationTags; // tags that say a method does NOT require authorization
	private List<String> authOperationTags; // tags that indicate whether an operation requires auth or not, coupled with a value from unauthOperationTagValues
	private List<String> unauthOperationTagValues; // for tags in authOperationTags this is the value to look for to indicate method does NOT require
													// authorization
	private List<String> authOperationScopes; // default scopes to add if authOperationTags is present but no scopes
	private List<String> operationScopeTags; // explicit scopes that are required for authorization for a method

	private List<String> resourcePriorityTags;
	private List<String> resourceDescriptionTags;

	private boolean parseModels = true;
	private boolean crossClassResources = false;
	private boolean sortResourcesByPath = false;
	private boolean sortResourcesByPriority = false;
	private boolean sortApisByPath = true;

	private ApiAuthorizations apiAuthorizations;

	private ApiInfo apiInfo;

	private Recorder recorder = new ObjectMapperRecorder();
	private Translator translator;

	/**
	 * This creates a DocletOptions
	 */
	public DocletOptions() {
		this.excludeAnnotationClasses = new ArrayList<String>();
		this.excludeAnnotationClasses.add("javax.ws.rs.HeaderParam");
		this.excludeAnnotationClasses.add("javax.ws.rs.core.Context");

		this.errorTags = new ArrayList<String>();
		this.errorTags.add("errorResponse"); // swagger 1.1
		this.errorTags.add("responseMessage"); // swagger 1.2
		this.errorTags.add("errorCode");

		this.successTags = new ArrayList<String>();
		this.successTags.add("successResponse");
		this.successTags.add("successCode");

		this.typesToTreatAsOpaque = new ArrayList<String>();
		this.typesToTreatAsOpaque.add("org.joda.time.DateTime");
		this.typesToTreatAsOpaque.add("java.util.UUID");
		// dont document MultipartFormDataInput as doclet can't handle it
		this.typesToTreatAsOpaque.add("org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput");

		this.excludeMethodTags = new ArrayList<String>();
		this.excludeMethodTags.add("hidden");
		this.excludeMethodTags.add("hide");
		this.excludeMethodTags.add("exclude");

		this.resourceTags = new ArrayList<String>();
		this.resourceTags.add("parentEndpointName");
		this.resourceTags.add("resourcePath");
		this.resourceTags.add("resource");

		this.responseTypeTags = new ArrayList<String>();
		this.responseTypeTags.add("responseType");
		this.responseTypeTags.add("outputType");

		this.methodCommentTags = new ArrayList<String>();
		this.methodCommentTags.add("description");
		this.methodCommentTags.add("comment");

		this.methodSummaryTags = new ArrayList<String>();
		this.methodSummaryTags.add("summary");
		this.methodSummaryTags.add("endpointName");

		this.propertyCommentTags = new ArrayList<String>();
		this.propertyCommentTags.add("description");
		this.propertyCommentTags.add("comment");
		this.propertyCommentTags.add("return");

		this.propertyMinTags = new ArrayList<String>();
		this.propertyMinTags.add("min");
		this.propertyMinTags.add("minimum");

		this.propertyMaxTags = new ArrayList<String>();
		this.propertyMaxTags.add("max");
		this.propertyMaxTags.add("maximum");

		this.unauthOperationTags = new ArrayList<String>();
		this.unauthOperationTags.add("noAuth");
		this.unauthOperationTags.add("unauthorized");

		this.authOperationTags = new ArrayList<String>();
		this.authOperationTags.add("authentication");
		this.authOperationTags.add("authorization");

		this.unauthOperationTagValues = new ArrayList<String>();
		this.unauthOperationTagValues.add("not required");
		this.unauthOperationTagValues.add("off");
		this.unauthOperationTagValues.add("false");
		this.unauthOperationTagValues.add("none");

		this.operationScopeTags = new ArrayList<String>();
		this.operationScopeTags.add("scope");
		this.operationScopeTags.add("oauth2Scope");

		this.authOperationScopes = new ArrayList<String>();

		this.resourcePriorityTags = new ArrayList<String>();
		this.resourcePriorityTags.add("resourcePriority");
		this.resourcePriorityTags.add("resourceOrder");

		this.resourceDescriptionTags = new ArrayList<String>();
		this.resourceDescriptionTags.add("resourceDescription");

		this.translator = new FirstNotNullTranslator()
				.addNext(
						new AnnotationAwareTranslator().ignore("javax.xml.bind.annotation.XmlTransient")
								.element("javax.xml.bind.annotation.XmlElement", "name").rootElement("javax.xml.bind.annotation.XmlRootElement", "name"))
				.addNext(
						new AnnotationAwareTranslator().ignore("com.fasterxml.jackson.annotation.JsonIgnore")
								.element("com.fasterxml.jackson.annotation.JsonProperty", "value")
								.rootElement("com.fasterxml.jackson.annotation.JsonRootName", "value"))

				.addNext(
						new AnnotationAwareTranslator().ignore("org.codehaus.jackson.map.annotate.JsonIgnore")
								.element("org.codehaus.jackson.map.annotate.JsonProperty", "value")
								.rootElement("org.codehaus.jackson.map.annotate.JsonRootName", "value"))

				.addNext(new NameBasedTranslator());
	}

	public File getOutputDirectory() {
		return this.outputDirectory;
	}

	public String getDocBasePath() {
		return this.docBasePath;
	}

	public String getApiBasePath() {
		return this.apiBasePath;
	}

	public String getApiVersion() {
		return this.apiVersion;
	}

	public String getSwaggerUiZipPath() {
		return this.swaggerUiZipPath;
	}

	public List<String> getExcludeAnnotationClasses() {
		return this.excludeAnnotationClasses;
	}

	public List<String> getErrorTags() {
		return this.errorTags;
	}

	/**
	 * This gets the successTags
	 * @return the successTags
	 */
	public List<String> getSuccessTags() {
		return this.successTags;
	}

	/**
	 * This gets the excludeMethodTags
	 * @return the excludeMethodTags
	 */
	public List<String> getExcludeMethodTags() {
		return this.excludeMethodTags;
	}

	/**
	 * This gets the resourceTags
	 * @return the resourceTags
	 */
	public List<String> getResourceTags() {
		return this.resourceTags;
	}

	public List<String> getTypesToTreatAsOpaque() {
		return this.typesToTreatAsOpaque;
	}

	public boolean isParseModels() {
		return this.parseModels;
	}

	/**
	 * This gets the crossClassResources
	 * @return the crossClassResources
	 */
	public boolean isCrossClassResources() {
		return this.crossClassResources;
	}

	/**
	 * This sets the crossClassResources
	 * @param crossClassResources the crossClassResources to set
	 * @return this
	 */
	public DocletOptions setCrossClassResources(boolean crossClassResources) {
		this.crossClassResources = crossClassResources;
		return this;
	}

	/**
	 * This gets the responseTypeTags
	 * @return the responseTypeTags
	 */
	public List<String> getResponseTypeTags() {
		return this.responseTypeTags;
	}

	/**
	 * This gets a list of javadoc tag names that can be used for the operation notes
	 * @return list of javadoc tag names that can be used for the operation notes
	 */
	public List<String> getMethodCommentTags() {
		return this.methodCommentTags;
	}

	/**
	 * This gets a list of javadoc tag names that can be used for the operation summary
	 * @return a list of javadoc tag names that can be used for the operation summary
	 */
	public List<String> getMethodSummaryTags() {
		return this.methodSummaryTags;
	}

	/**
	 * This gets list of javadoc tag names that can be used for the model property descriptions
	 * @return list of javadoc tag names that can be used for the model property descriptions
	 */
	public List<String> getPropertyCommentTags() {
		return this.propertyCommentTags;
	}

	/**
	 * This gets list of javadoc tag names that can be used for ordering resources in the resource listing
	 * @return the list of javadoc tag names that can be used for ordering resources in the resource listing
	 */
	public List<String> getResourcePriorityTags() {
		return this.resourcePriorityTags;
	}

	/**
	 * This gets list of javadoc tag names that can be used for the description of resources
	 * @return the list of javadoc tag names that can be used for the description of resources
	 */
	public List<String> getResourceDescriptionTags() {
		return this.resourceDescriptionTags;
	}

	/**
	 * This gets the propertyMinTags
	 * @return the propertyMinTags
	 */
	public List<String> getPropertyMinTags() {
		return this.propertyMinTags;
	}

	/**
	 * This gets the propertyMaxTags
	 * @return the propertyMaxTags
	 */
	public List<String> getPropertyMaxTags() {
		return this.propertyMaxTags;
	}

	/**
	 * This gets the unauthOperationTags
	 * @return the unauthOperationTags
	 */
	public List<String> getUnauthOperationTags() {
		return this.unauthOperationTags;
	}

	/**
	 * This gets the authOperationTags
	 * @return the authOperationTags
	 */
	public List<String> getAuthOperationTags() {
		return this.authOperationTags;
	}

	/**
	 * This gets the unauthOperationTagValues
	 * @return the unauthOperationTagValues
	 */
	public List<String> getUnauthOperationTagValues() {
		return this.unauthOperationTagValues;
	}

	/**
	 * This gets the operationScopeTags
	 * @return the operationScopeTags
	 */
	public List<String> getOperationScopeTags() {
		return this.operationScopeTags;
	}

	/**
	 * This gets the authOperationScopes
	 * @return the authOperationScopes
	 */
	public List<String> getAuthOperationScopes() {
		return this.authOperationScopes;
	}

	public Recorder getRecorder() {
		return this.recorder;
	}

	public DocletOptions setRecorder(Recorder recorder) {
		this.recorder = recorder;
		return this;
	}

	public Translator getTranslator() {
		return this.translator;
	}

	public DocletOptions setTranslator(Translator translator) {
		this.translator = translator;
		return this;
	}

	/**
	 * This gets the sortResourcesByPath
	 * @return the sortResourcesByPath
	 */
	public boolean isSortResourcesByPath() {
		return this.sortResourcesByPath;
	}

	/**
	 * This sets the sortResourcesByPath
	 * @param sortResourcesByPath the sortResourcesByPath to set
	 * @return this
	 */
	public DocletOptions setSortResourcesByPath(boolean sortResourcesByPath) {
		this.sortResourcesByPath = sortResourcesByPath;
		return this;
	}

	/**
	 * This gets the sortResourcesByPriority
	 * @return the sortResourcesByPriority
	 */
	public boolean isSortResourcesByPriority() {
		return this.sortResourcesByPriority;
	}

	/**
	 * This sets the sortResourcesByPriority
	 * @param sortResourcesByPriority the sortResourcesByPriority to set
	 * @return this
	 */
	public DocletOptions setSortResourcesByPriority(boolean sortResourcesByPriority) {
		this.sortResourcesByPriority = sortResourcesByPriority;
		return this;
	}

	/**
	 * This gets the sortOperationsByPath
	 * @return the sortOperationsByPath
	 */
	public boolean isSortApisByPath() {
		return this.sortApisByPath;
	}

	/**
	 * This sets the sortApisByPath
	 * @param sortApisByPath the sortApisByPath to set
	 * @return this
	 */
	public DocletOptions setSortApisByPath(boolean sortApisByPath) {
		this.sortApisByPath = sortApisByPath;
		return this;
	}

	/**
	 * This gets the includeSwaggerUi
	 * @return the includeSwaggerUi
	 */
	public boolean isIncludeSwaggerUi() {
		return this.includeSwaggerUi;
	}

	/**
	 * This sets the includeSwaggerUi
	 * @param includeSwaggerUi the includeSwaggerUi to set
	 * @return this
	 */
	public DocletOptions setIncludeSwaggerUi(boolean includeSwaggerUi) {
		this.includeSwaggerUi = includeSwaggerUi;
		return this;
	}

	/**
	 * This gets the apiAuthorizations
	 * @return the apiAuthorizations
	 */
	public ApiAuthorizations getApiAuthorizations() {
		return this.apiAuthorizations;
	}

	/**
	 * This sets the apiAuthorizations
	 * @param apiAuthorizations the apiAuthorizations to set
	 * @return this
	 */
	public DocletOptions setApiAuthorizations(ApiAuthorizations apiAuthorizations) {
		this.apiAuthorizations = apiAuthorizations;
		return this;
	}

	/**
	 * This gets the apiInfo
	 * @return the apiInfo
	 */
	public ApiInfo getApiInfo() {
		return this.apiInfo;
	}

	/**
	 * This sets the apiInfo
	 * @param apiInfo the apiInfo to set
	 * @return this
	 */
	public DocletOptions setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
		return this;
	}

}
