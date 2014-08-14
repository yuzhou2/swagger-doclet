package com.carma.swagger.doclet.model;

/**
 * The ApiAuthorizations represents the authorizations element for the API
 * @version $Id$
 * @author conor.roche
 */
public class ApiAuthorizations {

	private ApiOauth2Authorization oauth2;
	private ApiBasicAuthorization basicAuth;
	private ApiKeyAuthorization apiKey;

	/**
	 * This creates an empty Authorizations
	 */
	public ApiAuthorizations() {
	}

	/**
	 * This creates an Authorizations for oauth2
	 * @param oauth2 The oauth2 authorization
	 */
	public ApiAuthorizations(ApiOauth2Authorization oauth2) {
		super();
		this.oauth2 = oauth2;
	}

	/**
	 * This creates a ApiAuthorizations for basic auth
	 * @param basicAuth The basic authorization
	 */
	public ApiAuthorizations(ApiBasicAuthorization basicAuth) {
		super();
		this.basicAuth = basicAuth;
	}

	/**
	 * This creates a ApiAuthorizations for api key type auth
	 * @param apiKey The API key authorization details
	 */
	public ApiAuthorizations(ApiKeyAuthorization apiKey) {
		super();
		this.apiKey = apiKey;
	}

	/**
	 * This gets the oauth2
	 * @return the oauth2
	 */
	public ApiOauth2Authorization getOauth2() {
		return this.oauth2;
	}

	/**
	 * This gets the basicAuth
	 * @return the basicAuth
	 */
	public ApiBasicAuthorization getBasicAuth() {
		return this.basicAuth;
	}

	/**
	 * This gets the apiKey
	 * @return the apiKey
	 */
	public ApiKeyAuthorization getApiKey() {
		return this.apiKey;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.apiKey == null) ? 0 : this.apiKey.hashCode());
		result = prime * result + ((this.basicAuth == null) ? 0 : this.basicAuth.hashCode());
		result = prime * result + ((this.oauth2 == null) ? 0 : this.oauth2.hashCode());
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
		ApiAuthorizations other = (ApiAuthorizations) obj;
		if (this.apiKey == null) {
			if (other.apiKey != null) {
				return false;
			}
		} else if (!this.apiKey.equals(other.apiKey)) {
			return false;
		}
		if (this.basicAuth == null) {
			if (other.basicAuth != null) {
				return false;
			}
		} else if (!this.basicAuth.equals(other.basicAuth)) {
			return false;
		}
		if (this.oauth2 == null) {
			if (other.oauth2 != null) {
				return false;
			}
		} else if (!this.oauth2.equals(other.oauth2)) {
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
		return "ApiAuthorizations [oauth2=" + this.oauth2 + ", basicAuth=" + this.basicAuth + ", apiKey=" + this.apiKey + "]";
	}

}
