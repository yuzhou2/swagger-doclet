package com.hypnoticocelot.jaxrs.doclet.model;

import java.util.List;

/**
 * The ApiOauth2Authorization represents the oauth 2 authorization for the api authorization
 * @version $Id$
 * @author conor.roche
 */
public class ApiOauth2Authorization {

	private String type;
	private List<Oauth2Scope> scopes;
	private Oauth2GrantTypes grantTypes;

	/**
	 * This creates a ApiOauth2Authorization
	 */
	public ApiOauth2Authorization() {
		this.type = "oauth2";
	}

	/**
	 * This creates a ApiOauth2Authorization
	 * @param scopes
	 * @param grantTypes
	 */
	public ApiOauth2Authorization(List<Oauth2Scope> scopes, Oauth2GrantTypes grantTypes) {
		super();
		this.type = "oauth2";
		this.scopes = scopes;
		this.grantTypes = grantTypes;
	}

	/**
	 * This gets the type of the authorization
	 * @return oauth2
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * This gets the scopes
	 * @return the scopes
	 */
	public List<Oauth2Scope> getScopes() {
		return this.scopes;
	}

	/**
	 * This gets the grantTypes
	 * @return the grantTypes
	 */
	public Oauth2GrantTypes getGrantTypes() {
		return this.grantTypes;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.grantTypes == null) ? 0 : this.grantTypes.hashCode());
		result = prime * result + ((this.scopes == null) ? 0 : this.scopes.hashCode());
		result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
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
		ApiOauth2Authorization other = (ApiOauth2Authorization) obj;
		if (this.grantTypes == null) {
			if (other.grantTypes != null) {
				return false;
			}
		} else if (!this.grantTypes.equals(other.grantTypes)) {
			return false;
		}
		if (this.scopes == null) {
			if (other.scopes != null) {
				return false;
			}
		} else if (!this.scopes.equals(other.scopes)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
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
		return "ApiOauth2Authorization [type=" + this.type + ", scopes=" + this.scopes + ", grantTypes=" + this.grantTypes + "]";
	}

}
