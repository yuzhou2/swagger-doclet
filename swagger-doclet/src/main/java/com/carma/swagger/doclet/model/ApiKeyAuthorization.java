package com.carma.swagger.doclet.model;

/**
 * The ApiKeyAuthorization represents the API Key authorization type
 * @version $Id$
 * @author conor.roche
 */
public class ApiKeyAuthorization {

	private String type;

	private String passAs;
	private String keyname;

	/**
	 * This creates a ApiKeyAuthorization
	 */
	public ApiKeyAuthorization() {
		this.type = "apiKey";
	}

	/**
	 * This creates a ApiKeyAuthorization
	 * @param passAs either header or query
	 * @param keyname
	 */
	public ApiKeyAuthorization(String passAs, String keyname) {
		super();
		this.type = "apiKey";
		this.passAs = passAs;
		this.keyname = keyname;
	}

	/**
	 * This gets the passAs
	 * @return the passAs
	 */
	public String getPassAs() {
		return this.passAs;
	}

	/**
	 * This sets the passAs
	 * @param passAs the passAs to set
	 */
	public void setPassAs(String passAs) {
		this.passAs = passAs;
	}

	/**
	 * This gets the keyname
	 * @return the keyname
	 */
	public String getKeyname() {
		return this.keyname;
	}

	/**
	 * This sets the keyname
	 * @param keyname the keyname to set
	 */
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}

	/**
	 * This gets the type
	 * @return the type
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
		result = prime * result + ((this.keyname == null) ? 0 : this.keyname.hashCode());
		result = prime * result + ((this.passAs == null) ? 0 : this.passAs.hashCode());
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
		ApiKeyAuthorization other = (ApiKeyAuthorization) obj;
		if (this.keyname == null) {
			if (other.keyname != null) {
				return false;
			}
		} else if (!this.keyname.equals(other.keyname)) {
			return false;
		}
		if (this.passAs == null) {
			if (other.passAs != null) {
				return false;
			}
		} else if (!this.passAs.equals(other.passAs)) {
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
		return "ApiKeyAuthorization [type=" + this.type + ", passAs=" + this.passAs + ", keyname=" + this.keyname + "]";
	}

}
