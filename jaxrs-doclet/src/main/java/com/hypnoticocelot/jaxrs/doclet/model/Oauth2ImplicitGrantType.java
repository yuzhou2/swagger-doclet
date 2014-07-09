package com.hypnoticocelot.jaxrs.doclet.model;

/**
 * The Oauth2ImplicitGrantType represents the definition of the implicit flow
 * @version $Id$
 * @author conor.roche
 */
public class Oauth2ImplicitGrantType {

	/**
	 * The LoginEndpoint represents the login endpoint used for the implicit flow
	 * @version $Id$
	 * @author conor.roche
	 */
	public static class LoginEndpoint {

		private String url;

		/**
		 * This creates a LoginEndpoint
		 */
		public LoginEndpoint() {
			super();
		}

		/**
		 * This creates a LoginEndpoint
		 * @param url
		 */
		public LoginEndpoint(String url) {
			super();
			this.url = url;
		}

		/**
		 * This gets the url
		 * @return the url
		 */
		public String getUrl() {
			return this.url;
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.url == null) ? 0 : this.url.hashCode());
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
			LoginEndpoint other = (LoginEndpoint) obj;
			if (this.url == null) {
				if (other.url != null) {
					return false;
				}
			} else if (!this.url.equals(other.url)) {
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
			return "LoginEndpoint [url=" + this.url + "]";
		}

	}

	private String tokenName = "access_token";
	private LoginEndpoint loginEndpoint;

	/**
	 * This creates a Oauth2ImplicitGrantType
	 */
	public Oauth2ImplicitGrantType() {
	}

	/**
	 * This creates a Oauth2ImplicitGrantType
	 * @param loginEndpointUrl
	 */
	public Oauth2ImplicitGrantType(String loginEndpointUrl) {
		super();
		this.loginEndpoint = new LoginEndpoint(loginEndpointUrl);
	}

	/**
	 * This gets the tokenName
	 * @return the tokenName
	 */
	public String getTokenName() {
		return this.tokenName;
	}

	/**
	 * This gets the loginEndpoint
	 * @return the loginEndpoint
	 */
	public LoginEndpoint getLoginEndpoint() {
		return this.loginEndpoint;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.loginEndpoint == null) ? 0 : this.loginEndpoint.hashCode());
		result = prime * result + ((this.tokenName == null) ? 0 : this.tokenName.hashCode());
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
		Oauth2ImplicitGrantType other = (Oauth2ImplicitGrantType) obj;
		if (this.loginEndpoint == null) {
			if (other.loginEndpoint != null) {
				return false;
			}
		} else if (!this.loginEndpoint.equals(other.loginEndpoint)) {
			return false;
		}
		if (this.tokenName == null) {
			if (other.tokenName != null) {
				return false;
			}
		} else if (!this.tokenName.equals(other.tokenName)) {
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
		return "Oauth2ImplicitGrantType [tokenName=" + this.tokenName + ", loginEndpoint=" + this.loginEndpoint + "]";
	}

}
