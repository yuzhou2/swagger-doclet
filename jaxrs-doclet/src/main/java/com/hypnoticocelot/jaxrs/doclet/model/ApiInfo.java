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

/**
 * The ApiInfo represents the info regarding the API
 * @version $Id$
 * @author conor.roche
 */
public class ApiInfo {

	private String title;
	private String description;
	private String termsOfServiceUrl;
	private String contact;
	private String license;
	private String licenseUrl;

	/**
	 * This creates a ApiInfo
	 */
	public ApiInfo() {
	}

	/**
	 * This creates a ApiInfo
	 * @param title
	 * @param description
	 * @param termsOfServiceUrl
	 * @param contact
	 * @param license
	 * @param licenseUrl
	 */
	public ApiInfo(String title, String description, String termsOfServiceUrl, String contact, String license, String licenseUrl) {
		super();
		this.title = title;
		this.description = description;
		this.termsOfServiceUrl = termsOfServiceUrl;
		this.contact = contact;
		this.license = license;
		this.licenseUrl = licenseUrl;
	}

	/**
	 * This gets the title
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * This gets the description
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * This gets the termsOfServiceUrl
	 * @return the termsOfServiceUrl
	 */
	public String getTermsOfServiceUrl() {
		return this.termsOfServiceUrl;
	}

	/**
	 * This gets the contact
	 * @return the contact
	 */
	public String getContact() {
		return this.contact;
	}

	/**
	 * This gets the license
	 * @return the license
	 */
	public String getLicense() {
		return this.license;
	}

	/**
	 * This gets the licenseUrl
	 * @return the licenseUrl
	 */
	public String getLicenseUrl() {
		return this.licenseUrl;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.contact == null) ? 0 : this.contact.hashCode());
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.license == null) ? 0 : this.license.hashCode());
		result = prime * result + ((this.licenseUrl == null) ? 0 : this.licenseUrl.hashCode());
		result = prime * result + ((this.termsOfServiceUrl == null) ? 0 : this.termsOfServiceUrl.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
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
		ApiInfo other = (ApiInfo) obj;
		if (this.contact == null) {
			if (other.contact != null) {
				return false;
			}
		} else if (!this.contact.equals(other.contact)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.license == null) {
			if (other.license != null) {
				return false;
			}
		} else if (!this.license.equals(other.license)) {
			return false;
		}
		if (this.licenseUrl == null) {
			if (other.licenseUrl != null) {
				return false;
			}
		} else if (!this.licenseUrl.equals(other.licenseUrl)) {
			return false;
		}
		if (this.termsOfServiceUrl == null) {
			if (other.termsOfServiceUrl != null) {
				return false;
			}
		} else if (!this.termsOfServiceUrl.equals(other.termsOfServiceUrl)) {
			return false;
		}
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
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
		return "ApiInfo [title=" + this.title + ", description=" + this.description + ", termsOfServiceUrl=" + this.termsOfServiceUrl + ", contact="
				+ this.contact + ", license=" + this.license + ", licenseUrl=" + this.licenseUrl + "]";
	}

}
