package com.hypnoticocelot.jaxrs.doclet.model;

/**
 * The ApiAuthorizations represents the authorizations element for the API
 * @version $Id$
 * @author conor.roche
 */
public class ApiAuthorizations {

	private ApiOauth2Authorization oauth2;

	// TODO support other types

	/**
	 * This creates a Authorizations
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
	 * This gets the oauth2
	 * @return the oauth2
	 */
	public ApiOauth2Authorization getOauth2() {
		return this.oauth2;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		return "ApiAuthorizations [oauth2=" + this.oauth2 + "]";
	}

}
