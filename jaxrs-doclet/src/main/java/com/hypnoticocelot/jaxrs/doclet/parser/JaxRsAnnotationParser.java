package com.hypnoticocelot.jaxrs.doclet.parser;

import static com.google.common.collect.Maps.uniqueIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

			Collection<ApiDeclaration> declarations = null;

			if (this.options.isCrossClassResources()) {
				// parse with the v2 parser that supports enpoints of the same resource being spread across resource files
				Map<String, ApiDeclaration> resourceToDeclaration = new HashMap<String, ApiDeclaration>();
				for (ClassDoc classDoc : this.rootDoc.classes()) {
					CrossClassApiParser classParser = new CrossClassApiParser(this.options, classDoc, SWAGGER_VERSION, this.options.getApiVersion(),
							this.options.getApiBasePath());
					classParser.parse(resourceToDeclaration);
				}
				declarations = resourceToDeclaration.values();
			} else {
				// use the original parse mode which treats each resource as a separate api
				declarations = new ArrayList<ApiDeclaration>();
				for (ClassDoc classDoc : this.rootDoc.classes()) {
					ApiClassParser classParser = new ApiClassParser(this.options, classDoc, Arrays.asList(this.rootDoc.classes()));
					Collection<Api> apis = classParser.parse();
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
							classParser.getRootPath(), apis, models));
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
				resources.add(new ResourceListingAPI("/" + resourceName + ".{format}", ""));
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
