package com.hypnoticocelot.jaxrs.doclet.model;

import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;

@JsonPropertyOrder({ "apiVersion", "swaggerVersion", "basePath", "resourcePath", "apis", "models" })
public class ApiDeclaration {

	private String apiVersion;
	private String swaggerVersion;
	private String basePath;
	private String resourcePath;
	private Collection<Api> apis;
	private Map<String, Model> models;

	@SuppressWarnings("unused")
	private ApiDeclaration() {
	}

	public ApiDeclaration(String swaggerVersion, String apiVersion, String basePath, String resourcePath, Collection<Api> apis, Map<String, Model> models) {
		this.swaggerVersion = swaggerVersion;
		this.apiVersion = apiVersion;
		this.basePath = basePath;
		this.resourcePath = resourcePath;
		this.apis = apis == null || apis.isEmpty() ? null : apis;
		this.models = models == null || models.isEmpty() ? null : models;
	}

	public String getApiVersion() {
		return this.apiVersion;
	}

	public String getSwaggerVersion() {
		return this.swaggerVersion;
	}

	public String getBasePath() {
		return this.basePath;
	}

	public String getResourcePath() {
		return this.resourcePath;
	}

	/**
	 * This gets the apis
	 * @return the apis
	 */
	public Collection<Api> getApis() {
		return this.apis;
	}

	/**
	 * This sets the apis
	 * @param apis the apis to set
	 */
	public void setApis(Collection<Api> apis) {
		this.apis = apis;
	}

	/**
	 * This gets the models
	 * @return the models
	 */
	public Map<String, Model> getModels() {
		return this.models;
	}

	/**
	 * This sets the models
	 * @param models the models to set
	 */
	public void setModels(Map<String, Model> models) {
		this.models = models;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiDeclaration that = (ApiDeclaration) o;
		return Objects.equal(this.apiVersion, that.apiVersion) && Objects.equal(this.swaggerVersion, that.swaggerVersion)
				&& Objects.equal(this.basePath, that.basePath) && Objects.equal(this.resourcePath, that.resourcePath) && Objects.equal(this.apis, that.apis)
				&& Objects.equal(this.models, that.models);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.apiVersion, this.swaggerVersion, this.basePath, this.resourcePath, this.apis, this.models);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("apiVersion", this.apiVersion).add("swaggerVersion", this.swaggerVersion).add("basePath", this.basePath)
				.add("resourcePath", this.resourcePath).add("apis", this.apis).add("models", this.models).toString();
	}
}
