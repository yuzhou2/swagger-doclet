package com.tenxerconsulting.swagger.doclet.model;

import java.util.List;

public class ResourceListing {

	private String swaggerVersion;
	private String apiVersion;
	private String basePath;
	private List<ResourceListingAPI> apis;
	private ApiAuthorizations authorizations;
	private ApiInfo info;

	@SuppressWarnings("unused")
	private ResourceListing() {
	}

	public ResourceListing(String swaggerVersion, String apiVersion, String basePath, List<ResourceListingAPI> apis, ApiAuthorizations authorizations,
			ApiInfo info) {
		this.swaggerVersion = swaggerVersion;
		this.apiVersion = apiVersion;
		this.basePath = basePath;
		this.apis = apis;
		this.authorizations = authorizations;
		this.info = info;
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

	public List<ResourceListingAPI> getApis() {
		return this.apis;
	}

	/**
	 * This gets the authorizations
	 * @return the authorizations
	 */
	public ApiAuthorizations getAuthorizations() {
		return this.authorizations;
	}

	/**
	 * This gets the info
	 * @return the info
	 */
	public ApiInfo getInfo() {
		return this.info;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.apiVersion == null) ? 0 : this.apiVersion.hashCode());
		result = prime * result + ((this.apis == null) ? 0 : this.apis.hashCode());
		result = prime * result + ((this.authorizations == null) ? 0 : this.authorizations.hashCode());
		result = prime * result + ((this.basePath == null) ? 0 : this.basePath.hashCode());
		result = prime * result + ((this.info == null) ? 0 : this.info.hashCode());
		result = prime * result + ((this.swaggerVersion == null) ? 0 : this.swaggerVersion.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ResourceListing other = (ResourceListing) obj;
		if (this.apiVersion == null) {
			if (other.apiVersion != null) {
				return false;
			}
		} else if (!this.apiVersion.equals(other.apiVersion)) {
			return false;
		}
		if (this.apis == null) {
			if (other.apis != null) {
				return false;
			}
		} else if (!this.apis.equals(other.apis)) {
			return false;
		}
		if (this.authorizations == null) {
			if (other.authorizations != null) {
				return false;
			}
		} else if (!this.authorizations.equals(other.authorizations)) {
			return false;
		}
		if (this.basePath == null) {
			if (other.basePath != null) {
				return false;
			}
		} else if (!this.basePath.equals(other.basePath)) {
			return false;
		}
		if (this.info == null) {
			if (other.info != null) {
				return false;
			}
		} else if (!this.info.equals(other.info)) {
			return false;
		}
		if (this.swaggerVersion == null) {
			if (other.swaggerVersion != null) {
				return false;
			}
		} else if (!this.swaggerVersion.equals(other.swaggerVersion)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResourceListing [swaggerVersion=" + this.swaggerVersion + ", apiVersion=" + this.apiVersion + ", basePath=" + this.basePath + ", apis="
				+ this.apis + ", authorizations=" + this.authorizations + ", info=" + this.info + "]";
	}

}
