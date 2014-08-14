package com.carma.swagger.doclet.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The OperationAuthorizations represents the authorizations element for the API
 * @version $Id$
 * @author conor.roche
 */
public class OperationAuthorizations {

	private List<Oauth2Scope> oauth2Scopes;

	/**
	 * This creates a OperationAuthorizations
	 */
	public OperationAuthorizations() {
	}

	/**
	 * This creates a OperationAuthorizations
	 * @param oauth2Scopes
	 */
	public OperationAuthorizations(List<Oauth2Scope> oauth2Scopes) {
		super();
		this.oauth2Scopes = oauth2Scopes;
	}

	/**
	 * This gets the oauth2Scopes
	 * @return the oauth2Scopes
	 */
	@JsonProperty("oauth2")
	public List<Oauth2Scope> getOauth2Scopes() {
		return this.oauth2Scopes;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.oauth2Scopes == null) ? 0 : this.oauth2Scopes.hashCode());
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
		OperationAuthorizations other = (OperationAuthorizations) obj;
		if (this.oauth2Scopes == null) {
			if (other.oauth2Scopes != null) {
				return false;
			}
		} else if (!this.oauth2Scopes.equals(other.oauth2Scopes)) {
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
		return "OperationAuthorizations [oauth2Scopes=" + this.oauth2Scopes + "]";
	}

}
