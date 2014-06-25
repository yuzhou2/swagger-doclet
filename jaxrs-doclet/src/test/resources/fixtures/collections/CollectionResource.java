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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The CollectionResource represents a jaxrs that returns collections and objects
 * that hold collections
 * @version $Id$
 * @author conor.roche
 */
@Path("/collections")
public class CollectionResource {

	/**
	 * This gets the collection holder
	 * @return The collection holder
	 */
	@GET
	public CollectionHolder getCollectionHolder() {
		List<CollectionItem> listItems = new ArrayList<CollectionItem>();
		listItems.add(new CollectionItem("test"));
		Set<CollectionItem> setItems = new HashSet<CollectionItem>();
		setItems.add(new CollectionItem("test2"));

		List<String> listStrings = new ArrayList<String>();
		listStrings.add("test3");
		Set<String> setStrings = new HashSet<String>();
		setStrings.add("test4");

		CollectionHolder resp = new CollectionHolder();
		resp.setCollectionItems(listItems);
		resp.setListItems(listItems);
		resp.setSetItems(setItems);

		resp.setCollectionStrings(listStrings);
		resp.setListStrings(listStrings);
		resp.setSetStrings(setStrings);

		return resp;
	}

}
