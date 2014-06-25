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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The UserRight represents a user right e.g. a permission
 * @version $Id: UserRight.java 106373 2014-01-28 10:37:36Z conor.roche $
 * @author conorroche
 */
@XmlRootElement(name = "right")
public class UserRight implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;

	/**
	 * This creates a UserRight
	 */
	public UserRight() {
		super();
	}

	/**
	 * This creates a UserRight
	 * @param id
	 * @param name
	 */
	public UserRight(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * This gets the id
	 * @return the id
	 */
	@XmlAttribute
	public long getId() {
		return this.id;
	}

	/**
	 * This sets the id
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * This gets the name
	 * @return the name
	 */
	@XmlAttribute
	public String getName() {
		return this.name;
	}

	/**
	 * This sets the name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRight [id=" + this.id + ", name=" + this.name + "]";
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.id ^ (this.id >>> 32));
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
		UserRight other = (UserRight) obj;
		if (this.id != other.id) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
