package com.carma.swagger.doclet.model;

/**
 * The Oauth2AuthorizationCodeGrantType represents
 * @version $Id$
 * @author conor.roche
 */
public class Oauth2AuthorizationCodeGrantType {

	/**
	 * The TokenRequestEndpoint represents the url for the request token endpoint
	 * @version $Id$
	 * @author conor.roche
	 */
	public static class TokenRequestEndpoint {

		private String url;
		private String clientIdName = "client_id";
		private String clientSecretName = "client_secret";

		/**
		 * This creates a TokenRequestEndpoint
		 */
		public TokenRequestEndpoint() {
			super();
		}

		/**
		 * This creates a TokenRequestEndpoint
		 * @param url
		 */
		public TokenRequestEndpoint(String url) {
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
		 * This gets the clientIdName
		 * @return the clientIdName
		 */
		public String getClientIdName() {
			return this.clientIdName;
		}

		/**
		 * This gets the clientSecretName
		 * @return the clientSecretName
		 */
		public String getClientSecretName() {
			return this.clientSecretName;
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.clientIdName == null) ? 0 : this.clientIdName.hashCode());
			result = prime * result + ((this.clientSecretName == null) ? 0 : this.clientSecretName.hashCode());
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
			TokenRequestEndpoint other = (TokenRequestEndpoint) obj;
			if (this.clientIdName == null) {
				if (other.clientIdName != null) {
					return false;
				}
			} else if (!this.clientIdName.equals(other.clientIdName)) {
				return false;
			}
			if (this.clientSecretName == null) {
				if (other.clientSecretName != null) {
					return false;
				}
			} else if (!this.clientSecretName.equals(other.clientSecretName)) {
				return false;
			}
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
			return "TokenRequestEndpoint [url=" + this.url + ", clientIdName=" + this.clientIdName + ", clientSecretName=" + this.clientSecretName + "]";
		}

	}

	/**
	 * The TokenEndpoint represents the token endpoint
	 * @version $Id$
	 * @author conor.roche
	 */
	public static class TokenEndpoint {

		private String url;
		private String tokenName = "access_token";

		/**
		 * This creates a TokenEndpoint
		 */
		public TokenEndpoint() {
			super();
		}

		/**
		 * This creates a TokenEndpoint
		 * @param url
		 */
		public TokenEndpoint(String url) {
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
		 * This gets the tokenName
		 * @return the tokenName
		 */
		public String getTokenName() {
			return this.tokenName;
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((this.tokenName == null) ? 0 : this.tokenName.hashCode());
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
			TokenEndpoint other = (TokenEndpoint) obj;
			if (this.tokenName == null) {
				if (other.tokenName != null) {
					return false;
				}
			} else if (!this.tokenName.equals(other.tokenName)) {
				return false;
			}
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
			return "TokenEndpoint [url=" + this.url + ", tokenName=" + this.tokenName + "]";
		}

	}

	private TokenRequestEndpoint tokenRequestEndpoint;
	private TokenEndpoint tokenEndpoint;

	/**
	 * This creates a Oauth2AuthorizationCodeGrantType
	 */
	public Oauth2AuthorizationCodeGrantType() {
	}

	/**
	 * This creates a Oauth2AuthorizationCodeGrantType
	 * @param tokenRequestEndpoint
	 * @param tokenEndpoint
	 */
	public Oauth2AuthorizationCodeGrantType(TokenRequestEndpoint tokenRequestEndpoint, TokenEndpoint tokenEndpoint) {
		super();
		this.tokenRequestEndpoint = tokenRequestEndpoint;
		this.tokenEndpoint = tokenEndpoint;
	}

	/**
	 * This creates a Oauth2AuthorizationCodeGrantType with the given endpoint urls
	 * @param tokenRequestEndpointUrl The url to the token request endpoint
	 * @param tokenEndpointUrl The url to the token endpoint
	 */
	public Oauth2AuthorizationCodeGrantType(String tokenRequestEndpointUrl, String tokenEndpointUrl) {
		this.tokenRequestEndpoint = new TokenRequestEndpoint(tokenRequestEndpointUrl);
		this.tokenEndpoint = new TokenEndpoint(tokenEndpointUrl);
	}

	/**
	 * This gets the tokenRequestEndpoint
	 * @return the tokenRequestEndpoint
	 */
	public TokenRequestEndpoint getTokenRequestEndpoint() {
		return this.tokenRequestEndpoint;
	}

	/**
	 * This gets the tokenEndpoint
	 * @return the tokenEndpoint
	 */
	public TokenEndpoint getTokenEndpoint() {
		return this.tokenEndpoint;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.tokenEndpoint == null) ? 0 : this.tokenEndpoint.hashCode());
		result = prime * result + ((this.tokenRequestEndpoint == null) ? 0 : this.tokenRequestEndpoint.hashCode());
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
		Oauth2AuthorizationCodeGrantType other = (Oauth2AuthorizationCodeGrantType) obj;
		if (this.tokenEndpoint == null) {
			if (other.tokenEndpoint != null) {
				return false;
			}
		} else if (!this.tokenEndpoint.equals(other.tokenEndpoint)) {
			return false;
		}
		if (this.tokenRequestEndpoint == null) {
			if (other.tokenRequestEndpoint != null) {
				return false;
			}
		} else if (!this.tokenRequestEndpoint.equals(other.tokenRequestEndpoint)) {
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
		return "Oauth2AuthorizationCodeGrantType [tokenRequestEndpoint=" + this.tokenRequestEndpoint + ", tokenEndpoint=" + this.tokenEndpoint + "]";
	}

}
