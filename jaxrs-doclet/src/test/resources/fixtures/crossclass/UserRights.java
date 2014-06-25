/*
 * Copyright © 2013 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclass;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The UserRights represents a collection of user rights
 * @version $Id: UserRights.java 119475 2014-06-05 14:32:04Z conor.roche $
 * @author conorroche
 */
@XmlRootElement(name = "userRights")
public class UserRights implements Serializable {

	private static final long serialVersionUID = 1L;

	private long userId;
	private List<UserRight> rights;

	/**
	 * The unique id of the user the preferences are for.
	 * @return The unique id of the user the preferences are for.
	 */
	@XmlAttribute
	public long getUserId() {
		return this.userId;
	}

	/**
	 * This sets the id of the user.
	 * @param userId The unique id of the user.
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * These are the rights that have been set for this user
	 * @return These are the rights that have been set for this user
	 */
	@XmlElement(name = "right")
	public List<UserRight> getRights() {
		return this.rights;
	}

	/**
	 * This sets the rights the user has
	 * @param rights the rights the user has
	 */
	public void setRights(List<UserRight> rights) {
		this.rights = rights;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRights [userId=" + this.userId + ", rights=" + this.rights + "]";
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.rights == null) ? 0 : this.rights.hashCode());
		result = prime * result + (int) (this.userId ^ (this.userId >>> 32));
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
		UserRights other = (UserRights) obj;
		if (this.rights == null) {
			if (other.rights != null) {
				return false;
			}
		} else if (!this.rights.equals(other.rights)) {
			return false;
		}
		if (this.userId != other.userId) {
			return false;
		}
		return true;
	}

}
