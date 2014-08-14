package com.carma.swagger.doclet.model;

import com.google.common.base.Objects;

/**
 * The Oauth2Scope represents an oauth2 scope
 * @version $Id$
 * @author conor.roche
 */
public class Oauth2Scope {

	private String scope;
	private String description;

	/**
	 * This creates a Oauth2Scope
	 */
	public Oauth2Scope() {
	}

	/**
	 * This creates a Oauth2Scope
	 * @param scope
	 * @param description
	 */
	public Oauth2Scope(String scope, String description) {
		super();
		this.scope = scope;
		this.description = description;
	}

	/**
	 * This gets the scope
	 * @return the scope
	 */
	public String getScope() {
		return this.scope;
	}

	/**
	 * This gets the description
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Oauth2Scope that = (Oauth2Scope) o;
		return Objects.equal(this.scope, that.scope) && Objects.equal(this.description, that.description);
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.scope, this.description);
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Oauth2Scope [scope=" + this.scope + ", description=" + this.description + "]";
	}

}
