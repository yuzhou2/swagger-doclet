/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hypnoticocelot.jaxrs.doclet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * The Oauth2GrantTypes represents different oauth2 grant types
 * @version $Id$
 * @author conor.roche
 */
public class Oauth2GrantTypes {

	private Oauth2AuthorizationCodeGrantType authorizationCode;
	private Oauth2ImplicitGrantType implicit;

	// TODO when swagger spec updated for other oauth2 flows ammend

	/**
	 * This creates a Oauth2GrantTypes
	 */
	public Oauth2GrantTypes() {
	}

	/**
	 * This creates a Oauth2GrantTypes
	 * @param authorizationCode
	 */
	public Oauth2GrantTypes(Oauth2AuthorizationCodeGrantType authorizationCode) {
		super();
		this.authorizationCode = authorizationCode;
	}

	/**
	 * This creates a Oauth2GrantTypes
	 * @param implicit
	 */
	public Oauth2GrantTypes(Oauth2ImplicitGrantType implicit) {
		super();
		this.implicit = implicit;
	}

	/**
	 * This creates a Oauth2GrantTypes
	 * @param authorizationCode
	 * @param implicit
	 */
	public Oauth2GrantTypes(Oauth2AuthorizationCodeGrantType authorizationCode, Oauth2ImplicitGrantType implicit) {
		super();
		this.authorizationCode = authorizationCode;
		this.implicit = implicit;
	}

	/**
	 * This gets the authorizationCode
	 * @return the authorizationCode
	 */
	@JsonProperty("authorization_code")
	public Oauth2AuthorizationCodeGrantType getAuthorizationCode() {
		return this.authorizationCode;
	}

	/**
	 * This gets the implicit
	 * @return the implicit
	 */
	public Oauth2ImplicitGrantType getImplicit() {
		return this.implicit;
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
		Oauth2GrantTypes that = (Oauth2GrantTypes) o;
		return Objects.equal(this.authorizationCode, that.authorizationCode) && Objects.equal(this.implicit, that.implicit);
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.authorizationCode, this.implicit);
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Oauth2GrantTypes [authorizationCode=" + this.authorizationCode + ", implicit=" + this.implicit + "]";
	}

}
