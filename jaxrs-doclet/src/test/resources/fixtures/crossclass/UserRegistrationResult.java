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
import java.util.Collection;
import java.util.HashSet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * The UserRegistrationResult represents
 * @version $Id: UserRegistrationResult.java 119642 2014-06-06 14:20:36Z martin.gerner $
 * @author martingerner
 */
@XmlRootElement(name = "userRegistrationResult")
public class UserRegistrationResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private Collection<UserRegistrationField> errorFields;

	/**
	 * This creates a UserRegistrationResult with the given user and error fields
	 * @param userId
	 * @param errorFields
	 */
	public UserRegistrationResult(long userId, Collection<UserRegistrationField> errorFields) {
		this.userId = userId;
		this.errorFields = errorFields;
	}

	/**
	 * This creates a UserRegistrationResult with a single error field
	 * @param userId
	 * @param errorField The error field
	 */
	public UserRegistrationResult(long userId, UserRegistrationField errorField) {
		super();
		this.userId = userId;
		this.errorFields = new HashSet<UserRegistrationField>();
		this.errorFields.add(errorField);
	}

	/**
	 * This creates a UserRegistrationResult with the given user id
	 * @param userId
	 */
	public UserRegistrationResult(long userId) {
		super();
		this.userId = userId;
	}

	/**
	 * This creates a UserRegistrationResult
	 */
	public UserRegistrationResult() {
	}

	/**
	 * This gets the uid
	 * @return the uid
	 */
	@XmlAttribute
	public long getUserId() {
		return this.userId;
	}

	/**
	 * This sets the uid
	 * @param userId the uid to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * This gets the errorFields
	 * @return the errorFields
	 */
	@XmlElement
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public Collection<UserRegistrationField> getErrorFields() {
		return this.errorFields;
	}

	/**
	 * This sets the errorFields
	 * @param errorFields the errorFields to set
	 */
	public void setErrorFields(Collection<UserRegistrationField> errorFields) {
		this.errorFields = errorFields;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRegistrationResult [userId=" + this.userId + ", errorFields=" + this.errorFields + "]";
	}

}
