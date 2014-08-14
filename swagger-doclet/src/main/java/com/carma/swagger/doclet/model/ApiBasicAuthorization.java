package com.carma.swagger.doclet.model;

/**
 * The ApiBasicAuthorization represents the
 * @version $Id$
 * @author conor.roche
 */
public class ApiBasicAuthorization {

	private final String type;

	/**
	 * This creates a ApiBasicAuthorization
	 */
	public ApiBasicAuthorization() {
		this.type = "basicAuth";
	}

	/**
	 * This gets the type of the authorization
	 * @return basicAuth
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ApiBasicAuthorization other = (ApiBasicAuthorization) obj;
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
		return "ApiBasicAuthorization [type=" + this.type + "]";
	}

}
