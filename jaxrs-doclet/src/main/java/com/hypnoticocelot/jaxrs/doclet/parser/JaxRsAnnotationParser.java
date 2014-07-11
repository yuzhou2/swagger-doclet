package com.hypnoticocelot.jaxrs.doclet.parser;

import static com.google.common.collect.Maps.uniqueIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.hypnoticocelot.jaxrs.doclet.DocletOptions;
import com.hypnoticocelot.jaxrs.doclet.Recorder;
import com.hypnoticocelot.jaxrs.doclet.ServiceDoclet;
import com.hypnoticocelot.jaxrs.doclet.model.Api;
import com.hypnoticocelot.jaxrs.doclet.model.ApiDeclaration;
import com.hypnoticocelot.jaxrs.doclet.model.Model;
import com.hypnoticocelot.jaxrs.doclet.model.ResourceListing;
import com.hypnoticocelot.jaxrs.doclet.model.ResourceListingAPI;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

public class JaxRsAnnotationParser {

	private static final String SWAGGER_VERSION = "1.2";

	private final DocletOptions options;
	private final RootDoc rootDoc;

	public JaxRsAnnotationParser(DocletOptions options, RootDoc rootDoc) {
		this.options = options;
		this.rootDoc = rootDoc;
	}

	public boolean run() {
		try {

			Collection<ClassDoc> docletClasses = Arrays.asList(this.rootDoc.classes());

			List<ApiDeclaration> declarations = null;

			if (this.options.isCrossClassResources()) {
				// parse with the v2 parser that supports endpoints of the same resource being spread across resource files
				Map<String, ApiDeclaration> resourceToDeclaration = new HashMap<String, ApiDeclaration>();
				for (ClassDoc classDoc : this.rootDoc.classes()) {
					CrossClassApiParser classParser = new CrossClassApiParser(this.options, classDoc, docletClasses, SWAGGER_VERSION,
							this.options.getApiVersion(), this.options.getApiBasePath());
					classParser.parse(resourceToDeclaration);
				}
				Collection<ApiDeclaration> declarationColl = resourceToDeclaration.values();

				declarations = new ArrayList<ApiDeclaration>(declarationColl);

				// clear any empty models
				for (ApiDeclaration api : declarations) {
					if (api.getModels() != null && api.getModels().isEmpty()) {
						api.setModels(null);
					}
				}

			} else {
				// use the original parse mode which treats each resource as a separate api
				declarations = new ArrayList<ApiDeclaration>();
				for (ClassDoc classDoc : this.rootDoc.classes()) {

					// look for a class level priority tag for the resource listing
					int priorityVal = Integer.MAX_VALUE;
					String priority = AnnotationHelper.getTagValue(classDoc, this.options.getResourcePriorityTags());
					if (priority != null) {
						try {
							priorityVal = Integer.parseInt(priority);
						} catch (NumberFormatException ex) {
							System.err.println("Warning invalid priority tag value: " + priority + " on class doc: " + classDoc);
						}
					}
					// look for a class level description tag for the resource listing
					String description = AnnotationHelper.getTagValue(classDoc, this.options.getResourceDescriptionTags());

					ApiClassParser classParser = new ApiClassParser(this.options, classDoc, docletClasses);
					List<Api> apis = classParser.parse();
					if (apis.isEmpty()) {
						continue;
					}

					Map<String, Model> models = uniqueIndex(classParser.models(), new Function<Model, String>() {

						public String apply(Model model) {
							return model.getId();
						}
					});

					// The idea (and need) for the declaration is that "/foo" and "/foo/annotated" are stored in separate
					// Api classes but are part of the same resource.
					declarations.add(new ApiDeclaration(SWAGGER_VERSION, this.options.getApiVersion(), this.options.getApiBasePath(),
							classParser.getRootPath(), apis, models, priorityVal, description));
				}
			}

			// sort the api declarations if needed
			if (this.options.isSortResourcesByPriority()) {

				Collections.sort(declarations, new Comparator<ApiDeclaration>() {

					public int compare(ApiDeclaration dec1, ApiDeclaration dec2) {
						return Integer.valueOf(dec1.getPriority()).compareTo(dec2.getPriority());
					}

				});

			} else if (this.options.isSortResourcesByPath()) {
				Collections.sort(declarations, new Comparator<ApiDeclaration>() {

					public int compare(ApiDeclaration dec1, ApiDeclaration dec2) {
						if (dec1 == null || dec1.getResourcePath() == null) {
							return 1;
						}
						if (dec2 == null || dec2.getResourcePath() == null) {
							return -1;
						}
						return dec1.getResourcePath().compareTo(dec2.getResourcePath());
					}

				});
			}

			// sort apis of each declaration
			if (this.options.isSortApisByPath()) {
				for (ApiDeclaration dec : declarations) {
					if (dec.getApis() != null) {
						Collections.sort(dec.getApis(), new Comparator<Api>() {

							public int compare(Api o1, Api o2) {
								if (o1 == null || o1.getPath() == null) {
									return -1;
								}
								return o1.getPath().compareTo(o2.getPath());
							}
						});
					}
				}
			}

			writeApis(declarations);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private void writeApis(Collection<ApiDeclaration> apis) throws IOException {
		List<ResourceListingAPI> resources = new LinkedList<ResourceListingAPI>();
		File outputDirectory = this.options.getOutputDirectory();
		Recorder recorder = this.options.getRecorder();
		for (ApiDeclaration api : apis) {
			String resourcePath = api.getResourcePath();
			if (!Strings.isNullOrEmpty(resourcePath)) {
				String resourceName = resourcePath.replaceFirst("/", "").replaceAll("/", "_").replaceAll("[\\{\\}]", "");
				resources.add(new ResourceListingAPI("/" + resourceName + ".{format}", api.getDescription()));
				File apiFile = new File(outputDirectory, resourceName + ".json");
				recorder.record(apiFile, api);
			}
		}

		// write out json for api
		ResourceListing listing = new ResourceListing(SWAGGER_VERSION, this.options.getApiVersion(), this.options.getDocBasePath(), resources,
				this.options.getApiAuthorizations(), this.options.getApiInfo());
		File docFile = new File(outputDirectory, "service.json");
		recorder.record(docFile, listing);

		// Copy swagger-ui into the output directory.
		if (this.options.isIncludeSwaggerUi()) {

			// TODO: support swagger ui dir instead of zip...

			String swaggerUiZipPath = this.options.getSwaggerUiZipPath();
			ZipInputStream swaggerZip;
			if (DocletOptions.DEFAULT_SWAGGER_UI_ZIP_PATH.equals(swaggerUiZipPath)) {
				swaggerZip = new ZipInputStream(ServiceDoclet.class.getResourceAsStream("/swagger-ui.zip"));
				System.out.println("Using default swagger-ui.zip file from SwaggerDoclet jar file");
			} else {
				if (new File(swaggerUiZipPath).exists()) {
					swaggerZip = new ZipInputStream(new FileInputStream(swaggerUiZipPath));
					System.out.println("Using swagger-ui.zip file from: " + swaggerUiZipPath);
				} else {
					File f = new File(".");
					System.out.println("SwaggerDoclet working directory: " + f.getAbsolutePath());
					System.out.println("-swaggerUiZipPath not set correct: " + swaggerUiZipPath);

					throw new RuntimeException("-swaggerUiZipPath not set correct, file not found: " + swaggerUiZipPath);
				}
			}

			ZipEntry entry = swaggerZip.getNextEntry();
			while (entry != null) {
				final File swaggerFile = new File(outputDirectory, entry.getName());
				if (entry.isDirectory()) {
					if (!swaggerFile.isDirectory() && !swaggerFile.mkdirs()) {
						throw new RuntimeException("Unable to create directory: " + swaggerFile);
					}
				} else {
					recorder.record(swaggerFile, swaggerZip);
				}

				entry = swaggerZip.getNextEntry();
			}
			swaggerZip.close();

		}
	}

}
