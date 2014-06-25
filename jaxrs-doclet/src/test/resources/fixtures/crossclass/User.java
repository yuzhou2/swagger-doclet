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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * A class representing a user.
 * @version $Id: User.java 120597 2014-06-12 10:19:06Z martin.gerner $
 * @author martingerner
 */
@XmlRootElement(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private boolean deleted;

	private String firstName;
	private String lastName;
	private String alias;

	private String email;
	private String phoneNumber;
	private String bio;
	private String webpage;
	private Boolean isFavourite;
	private Gender userGender;

	private Long registrationTime;
	private Long lastLogin;
	private Long lastSeen;

	private Boolean userHasPhoto;
	private String photoUrl;
	private Long imageId;

	private Double avgUserRating;
	private Integer numUserRatings;
	private Integer numUserComments;

	private Boolean isRidesharing;

	private Boolean isTestAccount;

	private Long lastChangedTimestampBasic;

	private Double relativeDistance;

	private Boolean welcomed;
	private Integer numWelcomes;

	private String trackedProfileUrl;

	/**
	 * The user ID. Always returned.
	 * @return The user ID. Always returned.
	 */
	@XmlAttribute
	public long getUserId() {
		return this.userId;
	}

	/**
	 * This sets the userId
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Whether the user account is deleted. Always returned.
	 * @return Whether the user account is deleted. Always returned.
	 */
	@XmlAttribute
	public boolean isDeleted() {
		return this.deleted;
	}

	/**
	 * This sets the deleted
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * The user's first name. Field name: NAME (private) or ALIAS (public)
	 * @return The user's first name. Field name: NAME (private) or ALIAS (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * This sets the firstName
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * The user's last name. Field name: NAME (private)
	 * @return The user's last name. Field name: NAME (private)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * This sets the lastName
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * The user's alias. Field name: ALIAS (public)
	 * @return The user's alias. Field name: ALIAS (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getAlias() {
		return this.alias;
	}

	/**
	 * This sets the alias
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * The user's email address. Field name: EMAIL (private)
	 * @return The user's email address. Field name: EMAIL (private)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public String getEmail() {
		return this.email;
	}

	/**
	 * This sets the email
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * A short (max 120 chars) description of the user. Field name: BIO (public)
	 * @return A short (max 120 chars) description of the user. Field name: BIO (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getBio() {
		return this.bio;
	}

	/**
	 * This sets the bio
	 * @param bio the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * The user's webpage url. Field name: WEBPAGE (public)
	 * @return The user's webpage url. Field name: WEBPAGE (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public String getWebpage() {
		return this.webpage;
	}

	/**
	 * This sets the webpage
	 * @param webpage the webpage to set
	 */
	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	/**
	 * Whether the user is favourited by the caller. Field name: IS_FAVOURITE (public)
	 * @return Whether the user is favourited by the caller. Field name: IS_FAVOURITE (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Boolean getIsFavourite() {
		return this.isFavourite;
	}

	/**
	 * This sets the isFavourite
	 * @param isFavourite the isFavourite to set
	 */
	public void setIsFavourite(Boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	/**
	 * The user's gender. Field name: GENDER (public)
	 * @return The user's gender. Field name: GENDER (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Gender getUserGender() {
		return this.userGender;
	}

	/**
	 * This sets the gender
	 * @param userGender the gender to set
	 */
	public void setUserGender(Gender userGender) {
		this.userGender = userGender;
	}

	/**
	 * The user's gender. Field name: GENDER
	 * @return The user's gender. Field name: GENDER. Deprecated.
	 * @deprecated
	 */
	@Deprecated
	@XmlAttribute
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public String getGender() {
		if (this.userGender != null) {
			return String.valueOf(this.userGender.toString().toLowerCase().charAt(0));
		}
		return null;
	}

	/**
	 * The timestamp in milliseconds from the epoch when the user signed up to carma. Field name: REGISTRATION_TIME (public)
	 * @return The timestamp in milliseconds from the epoch when the user signed up to carma. Field name: REGISTRATION_TIME (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Long getRegistrationTime() {
		return this.registrationTime;
	}

	/**
	 * This sets the registrationTime
	 * @param registrationTime the registrationTime to set
	 */
	public void setRegistrationTime(Long registrationTime) {
		this.registrationTime = registrationTime;
	}

	/**
	 * The timestamp in milliseconds from the epoch when the user last logged in to carma. Field name: LAST_LOGIN_TIMESTAMP (public)
	 * @return The timestamp in milliseconds from the epoch when the user last logged in to carma. Field name: LAST_LOGIN_TIMESTAMP (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Long getLastLogin() {
		return this.lastLogin;
	}

	/**
	 * This sets the lastLogin
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Long lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * The timestamp in milliseconds from the epoch when the user was last seen to use carma. Field name: LAST_SEEN_TIMESTAMP (public)
	 * @return The timestamp in milliseconds from the epoch when the user was last seen to use carma. Field name: LAST_SEEN_TIMESTAMP (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Long getLastSeen() {
		return this.lastSeen;
	}

	/**
	 * This sets the lastSeen
	 * @param lastSeen the lastSeen to set
	 */
	public void setLastSeen(Long lastSeen) {
		this.lastSeen = lastSeen;
	}

	/**
	 * Whether the user has uploaded a photo or not. Field name: PHOTO_URL (public)
	 * @return Whether the user has uploaded a photo or not. Field name: PHOTO_URL (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Boolean getUserHasPhoto() {
		return this.userHasPhoto;
	}

	/**
	 * This sets the userHasPhoto
	 * @param userHasPhoto the userHasPhoto to set
	 */
	public void setUserHasPhoto(Boolean userHasPhoto) {
		this.userHasPhoto = userHasPhoto;
	}

	/**
	 * The URL of the user's photo (can be a default photo). Field name: PHOTO_URL (public)
	 * @return The URL of the user's photo (can be a default photo). Field name: PHOTO_URL (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public String getPhotoUrl() {
		return this.photoUrl;
	}

	/**
	 * This sets the photoUrl
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * The average rating of the user. Field name: RATING (public)
	 * @return The average rating of the user. Field name: RATING (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Double getAvgUserRating() {
		return this.avgUserRating;
	}

	/**
	 * This sets the avgUserRating
	 * @param avgUserRating the avgUserRating to set
	 */
	public void setAvgUserRating(Double avgUserRating) {
		this.avgUserRating = avgUserRating;
	}

	/**
	 * The number of ratings done of this user. Field name: RATING (public)
	 * @return The number of ratings done of this user. Field name: RATING (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Integer getNumUserRatings() {
		return this.numUserRatings;
	}

	/**
	 * This sets the numUserRatings
	 * @param numUserRatings the numUserRatings to set
	 */
	public void setNumUserRatings(Integer numUserRatings) {
		this.numUserRatings = numUserRatings;
	}

	/**
	 * The number of textual comments written about this user. Field name: RATING (public)
	 * @return The number of textual comments written about this user. Field name: RATING (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Integer getNumUserComments() {
		return this.numUserComments;
	}

	/**
	 * This sets the numUserComments
	 * @param numUserComments the numUserComments to set
	 */
	public void setNumUserComments(Integer numUserComments) {
		this.numUserComments = numUserComments;
	}

	/**
	 * The user's telephone number. Field name: PHONE_NUMBER (public)
	 * @return The user's telephone number. Field name: PHONE_NUMBER (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * This sets the phoneNumber
	 * @param phoneNumber the telephoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Whether the user is currently ridesharing or not. Field name: IS_CURRENTLY_RIDESHARING (public)
	 * @return Whether the user is currently ridesharing or not. Field name: IS_CURRENTLY_RIDESHARING (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Boolean getIsRidesharing() {
		return this.isRidesharing;
	}

	/**
	 * @param isRidesharing the isRidesharing to set
	 */
	public void setIsRidesharing(Boolean isRidesharing) {
		this.isRidesharing = isRidesharing;
	}

	/**
	 * The id of the user's photo, if present. Field name: PHOTO_URL (public)
	 * @return The id of the user's photo, if present. Field name: PHOTO_URL (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Long getImageId() {
		return this.imageId;
	}

	/**
	 * This sets the imageId
	 * @param imageId the imageId to set
	 */
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	/**
	 * Whether the account is a test account or not. Field name: TEST_ACCOUNT_STATUS (public)
	 * @return Whether the account is a test account or not. Field name: TEST_ACCOUNT_STATUS (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Boolean getIsTestAccount() {
		return this.isTestAccount;
	}

	/**
	 * This sets the isTestAccount
	 * @param isTestAccount the isTestAccount to set
	 */
	public void setIsTestAccount(Boolean isTestAccount) {
		this.isTestAccount = isTestAccount;
	}

	/**
	 * This gets the timestamp when this user last updated his or her name or photo. Field name: LAST_CHANGED_TIMESTAMP_BASIC (public)
	 * @return This gets the timestamp when this user last updated his or her name or photo. Field name: LAST_CHANGED_TIMESTAMP_BASIC (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Long getLastChangedTimestampBasic() {
		return this.lastChangedTimestampBasic;
	}

	/**
	 * This sets the timestamp when this user last updated his or her name or photo
	 * @param lastChangedTimestampBasic the lastChangedTimestampBasic to set
	 */
	public void setLastChangedTimestampBasic(Long lastChangedTimestampBasic) {
		this.lastChangedTimestampBasic = lastChangedTimestampBasic;
	}

	/**
	 * Gets the distance of this user, relative to some other location. This is only available for a very restricted set of endpoints. Field name: DISTANCE
	 * (public)
	 * @return The distance of this user, relative to some other location. This is only available for a very restricted set of endpoints. Field name: DISTANCE
	 *         (public)
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Double getRelativeDistance() {
		return this.relativeDistance;
	}

	/**
	 * This sets the the distance of this user, relative to some other location. This is only available for a very restricted set of endpoints. Field name:
	 * DISTANCE (public)
	 * @param relativeDistance the distance of this user, relative to some other location. This is only available for a very restricted set of endpoints. Field
	 *            name: DISTANCE
	 */
	public void setRelativeDistance(Double relativeDistance) {
		this.relativeDistance = relativeDistance;
	}

	/**
	 * Whether the calling user has welcomed this user
	 * @return Whether the calling user has welcomed this user
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Boolean getWelcomed() {
		return this.welcomed;
	}

	/**
	 * This whether the calling user has welcomed this user
	 * @param welcomed whether the calling user has welcomed this user
	 */
	public void setWelcomed(Boolean welcomed) {
		this.welcomed = welcomed;
	}

	/**
	 * The number of times this user has been welcomed
	 * @return The number of times this user has been welcomed
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public Integer getNumWelcomes() {
		return this.numWelcomes;
	}

	/**
	 * This sets the number of times this user has been welcomed
	 * @param numWelcomes the number of times this user has been welcomed
	 */
	public void setNumWelcomes(Integer numWelcomes) {
		this.numWelcomes = numWelcomes;
	}

	/**
	 * The url to the user profile. This is typically not available apart from in certain cases
	 * such as when profiles are being included in the welcome email.
	 * @return The url to the user profile. This is typically not available apart from in certain cases
	 *         such as when profiles are being included in the welcome email.
	 */
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@XmlAttribute
	public String getTrackedProfileUrl() {
		return this.trackedProfileUrl;
	}

	/**
	 * This sets a url to the user profile. This is typically not available apart from in certain cases
	 * such as when profiles are being included in the welcome email.
	 * @param trackedProfileUrl a url to the user profile. This is typically not available apart from in certain cases
	 *            such as when profiles are being included in the welcome email.
	 */
	public void setTrackedProfileUrl(String trackedProfileUrl) {
		this.trackedProfileUrl = trackedProfileUrl;
	}

}