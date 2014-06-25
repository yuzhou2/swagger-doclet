/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.collections;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The CollectionItem represents an item that can be placed in a collection
 * @version $Id$
 * @author conor.roche
 */
@XmlRootElement(name = "collectionItem")
public class CollectionItem implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * This creates a CollectionItem
	 */
	public CollectionItem() {
	}

	/**
	 * This creates a CollectionItem
	 * @param name
	 */
	public CollectionItem(String name) {
		super();
		this.name = name;
	}

	private String name;

	// NOTE if you add in getter comments you need to modify the collections.json to have in the descriptions
	@SuppressWarnings("javadoc")
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
		return "CollectionItem [name=" + this.name + "]";
	}

}
