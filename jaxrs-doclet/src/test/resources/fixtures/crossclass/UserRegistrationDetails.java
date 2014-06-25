/*
 * Copyright © 2012 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclass;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;

/**
 * This contains the registration details for a user, it is intended for use
 * for creating and update a users profile data.
 * @version $Id: UserRegistrationDetails.java 119656 2014-06-06 14:36:48Z martin.gerner $
 * @author martingerner
 */
@XmlRootElement(name = "rtrUser")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationDetails implements Serializable {

	private static final long serialVersionUID = -1l;

	private String firstName;
	private String lastName;

	private String email;
	private String phoneNumber;

	private String password;
	private Long inviteId;

	private String country;
	private String bio;

	private String webpage;

	private String gender;
	private Long imageId;

	/**
	 * This creates a RTRUser
	 */
	public UserRegistrationDetails() {
	}

	/**
	 * The is the user's gender, either 'm', 'f', or 'o' (other).
	 * @return The user's gender, either 'm', 'f', or 'o' (other). Required for registration.
	 */
	@XmlAttribute
	@Length(min = 1, max = 1)
	@Pattern(message = "Gender invalid", regexp = "[mfo]")
	public String getGender() {
		return this.gender;
	}

	/**
	 * This sets the user's gender, either 'm', 'f', or 'o' (other).
	 * @param gender the user's gender, either 'm', 'f', or 'o' (other).
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * The user's first name.
	 * @return The user's first name. Required for registration.
	 */
	@XmlAttribute
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * This sets the user's first name.
	 * @param firstName the user's first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * The user's last name.
	 * @return The user's last name. Required for registration.
	 */
	@XmlAttribute
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * This is the user's last name.
	 * @param lastName the user's last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * The user's email address.
	 * @return The user's email address. Required for registration.
	 */
	@XmlAttribute
	public String getEmail() {
		return this.email;
	}

	/**
	 * This sets the user's email address.
	 * @param email the user's email address.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This is the users password, only used for creating/updating
	 * @return The user's password (minimum six characters). Required for registration.
	 */
	@XmlAttribute
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getPassword() {
		return this.password;
	}

	/**
	 * This sets the password for the user
	 * @param password the password for the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * The user's telephone number, without country code or leading zeroes.
	 * @return The user's telephone number, without country code or leading zeroes.
	 */
	@XmlAttribute
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * This sets The user's telephone number, without country code or leading zeroes.
	 * @param phoneNumber The user's telephone number, without country code or leading zeroes.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * The user's country, in ISO3 format.
	 * @return The user's country, in ISO3 format.
	 */
	@XmlAttribute
	public String getCountry() {
		return this.country;
	}

	/**
	 * The user's country, in ISO3 format.
	 * @param country The user's country, in ISO3 format.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * A short (max 120 chars) description of the user.
	 * @return A short (max 120 chars) description of the user.
	 */
	@XmlAttribute
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	public String getBio() {
		return this.bio;
	}

	/**
	 * This sets A short (max 120 chars) description of the user.
	 * @param bio A short (max 120 chars) description of the user.
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * This is the user's webpage url
	 * @return the user's webpage url
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	@XmlAttribute
	public String getWebpage() {
		return this.webpage;
	}

	/**
	 * This sets the user's webpage url
	 * @param webpage the user's webpage url
	 */
	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	/**
	 * This is the id of the invite that triggered the user to signup, only used for user creation
	 * @param inviteId the id of the invite that triggered the user to signup, only used for user creation
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}

	/**
	 * This gets the id of the invite that triggered the user to signup, only used for user creation
	 * @return the id of the invite that triggered the user to signup, only used for user creation
	 */
	public Long getInviteId() {
		return this.inviteId;
	}

	/**
	 * This sets the image id of the user photo
	 * @return The image id of the user photo
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public Long getImageId() {
		return this.imageId;
	}

	/**
	 * This gets the image id of the user photo
	 * @param imageId The image id of the user photo
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRegistrationDetails [firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", phoneNumber="
				+ this.phoneNumber + ", password=" + this.password + ", inviteId=" + this.inviteId + ", country=" + this.country + ", bio=" + this.bio
				+ ", webpage=" + this.webpage + ", gender=" + this.gender + ", imageId=" + this.imageId + "]";
	}

}
