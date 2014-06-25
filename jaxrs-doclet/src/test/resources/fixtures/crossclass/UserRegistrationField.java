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

import javax.xml.bind.annotation.XmlEnum;

/**
 * The UserRegistrationField represents a type of field of a user's profile that may have had a problem when registering
 * or updating a user profile.
 * @version $Id: UserRegistrationField.java 117335 2014-05-12 16:39:27Z conor.roche $
 * @author martingerner
 */
@XmlEnum
public enum UserRegistrationField implements Serializable {
	/**
	 * This is the users firstname field
	 */
	FIRST_NAME,
	/**
	 * This is the users surname field
	 */
	LAST_NAME,
	/**
	 * This is the biography of the user field
	 */
	BIO,
	/**
	 * This is the country of the user field
	 */
	COUNTRY,
	/**
	 * This is the user gender field
	 */
	GENDER,
	/**
	 * This is the user password field
	 */
	PASSWORD,
	/**
	 * This is the user webpage field
	 */
	WEBPAGE,
	/**
	 * This is the user email field
	 */
	EMAIL,
	/**
	 * This is the user telephone number field
	 */
	PHONE_NUMBER

}
