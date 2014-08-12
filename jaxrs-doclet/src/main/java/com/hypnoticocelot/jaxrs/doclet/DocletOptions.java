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

	/**
	 * This parses doclet options
	 * @param options The cmdline options
	 * @return The parse options
	 */
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
			} else if (option[0].equals("-swaggerUiZipPath") || option[0].equals("-swaggerUiPath")) {
				parsedOptions.swaggerUiPath = option[1];
			} else if (option[0].equals("-excludeParamAnnotations")) {
				parsedOptions.excludeParamAnnotations.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-disableModels")) {
				parsedOptions.parseModels = false;
			} else if (option[0].equals("-disableCopySwaggerUi") || option[0].equals("-skipUiFiles")) {
				parsedOptions.includeSwaggerUi = false;
			} else if (option[0].equals("-crossClassResources")) {
				parsedOptions.crossClassResources = true;
			} else if (option[0].equals("-disableSortApisByPath")) {
				parsedOptions.sortApisByPath = false;
			} else if (option[0].equals("-sortResourcesByPath")) {
				parsedOptions.sortResourcesByPath = true;
			} else if (option[0].equals("-sortResourcesByPriority")) {
				parsedOptions.sortResourcesByPriority = true;
			} else if (option[0].equals("-disableDeprecatedOperationExclusion")) {
				parsedOptions.excludeDeprecatedOperations = false;
			} else if (option[0].equals("-disableDeprecatedFieldExclusion")) {
				parsedOptions.excludeDeprecatedFields = false;
			} else if (option[0].equals("-disableDeprecatedParamExclusion")) {
				parsedOptions.excludeDeprecatedParams = false;
			} else if (option[0].equals("-disableDeprecatedResourceClassExclusion")) {
				parsedOptions.excludeDeprecatedResourceClasses = false;
			} else if (option[0].equals("-disableDeprecatedModelClassExclusion")) {
				parsedOptions.excludeDeprecatedModelClasses = false;
			} else if (option[0].equals("-responseMessageTags")) {
				parsedOptions.responseMessageTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-excludeModelPrefixes") || option[0].equals("-typesToTreatAsOpaque")) {
				parsedOptions.excludeModelPrefixes.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-excludeResourcePrefixes")) {
				parsedOptions.excludeResourcePrefixes.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-genericWrapperTypes")) {
				parsedOptions.genericWrapperTypes.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-excludeClassTags")) {
				parsedOptions.excludeClassTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-excludeOperationTags")) {
				parsedOptions.excludeOperationTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-excludeFieldTags")) {
				parsedOptions.excludeFieldTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-excludeParamsTags")) {
				parsedOptions.excludeParamsTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-csvParamsTags")) {
				parsedOptions.csvParamsTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-resourceTags")) {
				parsedOptions.resourceTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-responseTypeTags")) {
				parsedOptions.responseTypeTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-inputTypeTags")) {
				parsedOptions.inputTypeTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-defaultErrorTypeTags")) {
				parsedOptions.defaultErrorTypeTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-operationNotesTags")) {
				parsedOptions.operationNotesTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-operationSummaryTags")) {
				parsedOptions.operationSummaryTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-fieldDescriptionTags")) {
				parsedOptions.fieldDescriptionTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-fieldMinTags")) {
				parsedOptions.fieldMinTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-fieldMaxTags")) {
				parsedOptions.fieldMaxTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-requiredParamsTags")) {
				parsedOptions.requiredParamsTags.addAll(asList(copyOfRange(option, 1, option.length)));
			} else if (option[0].equals("-optionalParamsTags")) {
				parsedOptions.optionalParamsTags.addAll(asList(copyOfRange(option, 1, option.length)));
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
	private String swaggerUiPath = null;
	private String apiVersion = "0";

	private boolean includeSwaggerUi = true;

	private List<String> excludeResourcePrefixes;
	private List<String> excludeModelPrefixes;
	private List<String> genericWrapperTypes;
	private List<String> responseMessageTags;
	private List<String> responseTypeTags;
	private List<String> inputTypeTags;
	private List<String> defaultErrorTypeTags;

	private List<String> excludeParamAnnotations;
	private List<String> excludeClassTags;
	private List<String> excludeOperationTags;
	private List<String> excludeFieldTags;
	private List<String> excludeParamsTags;
	private List<String> csvParamsTags;
	private List<String> resourceTags;
	private List<String> operationNotesTags;
	private List<String> operationSummaryTags;
	private List<String> fieldDescriptionTags;

	private List<String> fieldMinTags;
	private List<String> fieldMaxTags;

	private List<String> requiredParamsTags;
	private List<String> optionalParamsTags;

	private List<String> unauthOperationTags; // tags that say a method does NOT require authorization
	private List<String> authOperationTags; // tags that indicate whether an operation requires auth or not, coupled with a value from unauthOperationTagValues
	private List<String> unauthOperationTagValues; // for tags in authOperationTags this is the value to look for to indicate method does NOT require
													// authorization
	private List<String> authOperationScopes; // default scopes to add if authOperationTags is present but no scopes
	private List<String> operationScopeTags; // explicit scopes that are required for authorization for a method

	private List<String> resourcePriorityTags;
	private List<String> resourceDescriptionTags;

	private boolean excludeDeprecatedResourceClasses = true;
	private boolean excludeDeprecatedModelClasses = true;
	private boolean excludeDeprecatedOperations = true;
	private boolean excludeDeprecatedFields = true;
	private boolean excludeDeprecatedParams = true;

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
		this.excludeParamAnnotations = new ArrayList<String>();
		this.excludeParamAnnotations.add("javax.ws.rs.core.Context");

		this.responseMessageTags = new ArrayList<String>();
		this.responseMessageTags.add("responseMessage");
		this.responseMessageTags.add("status");
		this.responseMessageTags.add("errorResponse");
		this.responseMessageTags.add("errorCode");
		this.responseMessageTags.add("successResponse");
		this.responseMessageTags.add("successCode");

		this.excludeModelPrefixes = new ArrayList<String>();
		this.excludeModelPrefixes.add("org.joda.time.DateTime");
		this.excludeModelPrefixes.add("java.util.UUID");
		// dont document MultipartFormDataInput as doclet can't handle it
		this.excludeModelPrefixes.add("org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput");

		// types which simply wrap an entity
		this.genericWrapperTypes = new ArrayList<String>();
		this.genericWrapperTypes.add("com.sun.jersey.api.JResponse");

		this.excludeResourcePrefixes = new ArrayList<String>();

		this.excludeClassTags = new ArrayList<String>();
		this.excludeClassTags.add("hidden");
		this.excludeClassTags.add("hide");
		this.excludeClassTags.add("exclude");

		this.excludeOperationTags = new ArrayList<String>();
		this.excludeOperationTags.add("hidden");
		this.excludeOperationTags.add("hide");
		this.excludeOperationTags.add("exclude");

		this.excludeFieldTags = new ArrayList<String>();
		this.excludeFieldTags.add("hidden");
		this.excludeFieldTags.add("hide");
		this.excludeFieldTags.add("exclude");

		this.excludeParamsTags = new ArrayList<String>();
		this.excludeParamsTags.add("excludeParams");
		this.excludeParamsTags.add("hiddenParams");
		this.excludeParamsTags.add("hideParams");

		this.csvParamsTags = new ArrayList<String>();
		this.csvParamsTags.add("csvParams");

		this.resourceTags = new ArrayList<String>();
		this.resourceTags.add("parentEndpointName");
		this.resourceTags.add("resourcePath");
		this.resourceTags.add("resource");

		this.responseTypeTags = new ArrayList<String>();
		this.responseTypeTags.add("responseType");
		this.responseTypeTags.add("outputType");

		this.inputTypeTags = new ArrayList<String>();
		this.inputTypeTags.add("inputType");
		this.inputTypeTags.add("bodyType");

		this.defaultErrorTypeTags = new ArrayList<String>();
		this.defaultErrorTypeTags.add("defaultErrorType");

		this.operationNotesTags = new ArrayList<String>();
		this.operationNotesTags.add("description");
		this.operationNotesTags.add("comment");
		this.operationNotesTags.add("notes");

		this.operationSummaryTags = new ArrayList<String>();
		this.operationSummaryTags.add("summary");
		this.operationSummaryTags.add("endpointName");

		this.fieldDescriptionTags = new ArrayList<String>();
		this.fieldDescriptionTags.add("description");
		this.fieldDescriptionTags.add("comment");
		this.fieldDescriptionTags.add("return");

		this.fieldMinTags = new ArrayList<String>();
		this.fieldMinTags.add("min");
		this.fieldMinTags.add("minimum");

		this.fieldMaxTags = new ArrayList<String>();
		this.fieldMaxTags.add("max");
		this.fieldMaxTags.add("maximum");

		this.requiredParamsTags = new ArrayList<String>();
		this.requiredParamsTags.add("requiredParams");

		this.optionalParamsTags = new ArrayList<String>();
		this.optionalParamsTags.add("optionalParams");

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

	/**
	 * This gets the swaggerUiPath
	 * @return the swaggerUiPath
	 */
	public String getSwaggerUiPath() {
		return this.swaggerUiPath;
	}

	/**
	 * This gets the responseMessageTags
	 * @return the responseMessageTags
	 */
	public List<String> getResponseMessageTags() {
		return this.responseMessageTags;
	}

	/**
	 * This gets the excludeOperationTags
	 * @return the excludeOperationTags
	 */
	public List<String> getExcludeOperationTags() {
		return this.excludeOperationTags;
	}

	/**
	 * This gets the excludeClassTags
	 * @return the excludeClassTags
	 */
	public List<String> getExcludeClassTags() {
		return this.excludeClassTags;
	}

	/**
	 * This gets the excludeFieldTags
	 * @return the excludeFieldTags
	 */
	public List<String> getExcludeFieldTags() {
		return this.excludeFieldTags;
	}

	/**
	 * This gets the excludeParamAnnotations
	 * @return the excludeParamAnnotations
	 */
	public List<String> getExcludeParamAnnotations() {
		return this.excludeParamAnnotations;
	}

	/**
	 * This gets the excludeParamsTags
	 * @return the excludeParamsTags
	 */
	public List<String> getExcludeParamsTags() {
		return this.excludeParamsTags;
	}

	/**
	 * This gets the csvParamsTags
	 * @return the csvParamsTags
	 */
	public List<String> getCsvParamsTags() {
		return this.csvParamsTags;
	}

	/**
	 * This gets the resourceTags
	 * @return the resourceTags
	 */
	public List<String> getResourceTags() {
		return this.resourceTags;
	}

	/**
	 * This gets prefixes of the FQN of resource classes to exclude
	 * @return the prefixes of the FQN of resource classes to exclude
	 */
	public List<String> getExcludeResourcePrefixes() {
		return this.excludeResourcePrefixes;
	}

	/**
	 * This sets the prefixes of the FQN of resource classes to exclude
	 * @param excludeResourcePrefixes the prefixes of the FQN of resource classes to exclude
	 * @return this
	 */
	public DocletOptions setExcludeResourcePrefixes(List<String> excludeResourcePrefixes) {
		this.excludeResourcePrefixes = excludeResourcePrefixes;
		return this;
	}

	/**
	 * This gets prefixes of the FQN of model classes to exclude
	 * @return prefixes of the FQN of model classes to exclude
	 */
	public List<String> getExcludeModelPrefixes() {
		return this.excludeModelPrefixes;
	}

	/**
	 * This sets the prefixes of the FQN of model classes to exclude
	 * @param excludeModelPrefixes prefixes of the FQN of model classes to exclude
	 * @return this
	 */
	public DocletOptions setExcludeModelPrefixes(List<String> excludeModelPrefixes) {
		this.excludeModelPrefixes = excludeModelPrefixes;
		return this;
	}

	/**
	 * This gets a list of FQN of types which simply wrap the
	 * real underlying data type
	 * @return The type
	 */
	public List<String> getGenericWrapperTypes() {
		return this.genericWrapperTypes;
	}

	/**
	 * This is whether model parsing is enabled
	 * @return Whether model parsing is enabled
	 */
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
	 * This gets tags that can customize the type for input body params
	 * @return The list of tags that can customize input body params
	 */
	public List<String> getInputTypeTags() {
		return this.inputTypeTags;
	}

	/**
	 * This gets the defaultErrorTypeTags
	 * @return the defaultErrorTypeTags
	 */
	public List<String> getDefaultErrorTypeTags() {
		return this.defaultErrorTypeTags;
	}

	/**
	 * This gets a list of javadoc tag names that can be used for the operation notes
	 * @return list of javadoc tag names that can be used for the operation notes
	 */
	public List<String> getOperationNotesTags() {
		return this.operationNotesTags;
	}

	/**
	 * This gets a list of javadoc tag names that can be used for the operation summary
	 * @return a list of javadoc tag names that can be used for the operation summary
	 */
	public List<String> getOperationSummaryTags() {
		return this.operationSummaryTags;
	}

	/**
	 * This gets list of javadoc tag names that can be used for the model field/method descriptions
	 * @return list of javadoc tag names that can be used for the model field/method descriptions
	 */
	public List<String> getFieldDescriptionTags() {
		return this.fieldDescriptionTags;
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
	 * This gets the fieldMinTags
	 * @return the fieldMinTags
	 */
	public List<String> getFieldMinTags() {
		return this.fieldMinTags;
	}

	/**
	 * This gets the fieldMaxTags
	 * @return the fieldMaxTags
	 */
	public List<String> getFieldMaxTags() {
		return this.fieldMaxTags;
	}

	/**
	 * This gets the requiredParamsTags
	 * @return the requiredParamsTags
	 */
	public List<String> getRequiredParamsTags() {
		return this.requiredParamsTags;
	}

	/**
	 * This gets the optionalParamsTags
	 * @return the optionalParamsTags
	 */
	public List<String> getOptionalParamsTags() {
		return this.optionalParamsTags;
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
	 * This gets the excludeDeprecatedResourceClasses
	 * @return the excludeDeprecatedResourceClasses
	 */
	public boolean isExcludeDeprecatedResourceClasses() {
		return this.excludeDeprecatedResourceClasses;
	}

	/**
	 * This gets the excludeDeprecatedModelClasses
	 * @return the excludeDeprecatedModelClasses
	 */
	public boolean isExcludeDeprecatedModelClasses() {
		return this.excludeDeprecatedModelClasses;
	}

	/**
	 * This gets the excludeDeprecatedOperations
	 * @return the excludeDeprecatedOperations
	 */
	public boolean isExcludeDeprecatedOperations() {
		return this.excludeDeprecatedOperations;
	}

	/**
	 * This sets the excludeDeprecatedOperations
	 * @param excludeDeprecatedOperations the excludeDeprecatedOperations to set
	 * @return this
	 */
	public DocletOptions setExcludeDeprecatedOperations(boolean excludeDeprecatedOperations) {
		this.excludeDeprecatedOperations = excludeDeprecatedOperations;
		return this;
	}

	/**
	 * This gets the excludeDeprecatedFields
	 * @return the excludeDeprecatedFields
	 */
	public boolean isExcludeDeprecatedFields() {
		return this.excludeDeprecatedFields;
	}

	/**
	 * This sets the excludeDeprecatedFields
	 * @param excludeDeprecatedFields the excludeDeprecatedFields to set
	 * @return this
	 */
	public DocletOptions setExcludeDeprecatedFields(boolean excludeDeprecatedFields) {
		this.excludeDeprecatedFields = excludeDeprecatedFields;
		return this;
	}

	/**
	 * This gets the excludeDeprecatedParams
	 * @return the excludeDeprecatedParams
	 */
	public boolean isExcludeDeprecatedParams() {
		return this.excludeDeprecatedParams;
	}

	/**
	 * This sets the excludeDeprecatedParams
	 * @param excludeDeprecatedParams the excludeDeprecatedParams to set
	 * @return this
	 */
	public DocletOptions setExcludeDeprecatedParams(boolean excludeDeprecatedParams) {
		this.excludeDeprecatedParams = excludeDeprecatedParams;
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

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DocletOptions [outputDirectory=" + this.outputDirectory + ", docBasePath=" + this.docBasePath + ", apiBasePath=" + this.apiBasePath
				+ ", swaggerUiPath=" + this.swaggerUiPath + ", apiVersion=" + this.apiVersion + ", includeSwaggerUi=" + this.includeSwaggerUi
				+ ", excludeResourcePrefixes=" + this.excludeResourcePrefixes + ", excludeModelPrefixes=" + this.excludeModelPrefixes
				+ ", genericWrapperTypes=" + this.genericWrapperTypes + ", responseMessageTags=" + this.responseMessageTags + ", responseTypeTags="
				+ this.responseTypeTags + ", inputTypeTags=" + this.inputTypeTags + ", defaultErrorTypeTags=" + this.defaultErrorTypeTags
				+ ", excludeParamAnnotations=" + this.excludeParamAnnotations + ", excludeClassTags=" + this.excludeClassTags + ", excludeOperationTags="
				+ this.excludeOperationTags + ", excludeFieldTags=" + this.excludeFieldTags + ", excludeParamsTags=" + this.excludeParamsTags
				+ ", csvParamsTags=" + this.csvParamsTags + ", resourceTags=" + this.resourceTags + ", operationNotesTags=" + this.operationNotesTags
				+ ", operationSummaryTags=" + this.operationSummaryTags + ", fieldDescriptionTags=" + this.fieldDescriptionTags + ", fieldMinTags="
				+ this.fieldMinTags + ", fieldMaxTags=" + this.fieldMaxTags + ", requiredParamsTags=" + this.requiredParamsTags + ", optionalParamsTags="
				+ this.optionalParamsTags + ", unauthOperationTags=" + this.unauthOperationTags + ", authOperationTags=" + this.authOperationTags
				+ ", unauthOperationTagValues=" + this.unauthOperationTagValues + ", authOperationScopes=" + this.authOperationScopes + ", operationScopeTags="
				+ this.operationScopeTags + ", resourcePriorityTags=" + this.resourcePriorityTags + ", resourceDescriptionTags=" + this.resourceDescriptionTags
				+ ", excludeDeprecatedResourceClasses=" + this.excludeDeprecatedResourceClasses + ", excludeDeprecatedModelClasses="
				+ this.excludeDeprecatedModelClasses + ", excludeDeprecatedOperations=" + this.excludeDeprecatedOperations + ", excludeDeprecatedFields="
				+ this.excludeDeprecatedFields + ", excludeDeprecatedParams=" + this.excludeDeprecatedParams + ", parseModels=" + this.parseModels
				+ ", crossClassResources=" + this.crossClassResources + ", sortResourcesByPath=" + this.sortResourcesByPath + ", sortResourcesByPriority="
				+ this.sortResourcesByPriority + ", sortApisByPath=" + this.sortApisByPath + "]";
	}

}
