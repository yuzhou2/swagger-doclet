/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.propertydesc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The PropertyDescResource represents a jaxrs resource that checks for description fields in the generated model
 * classes
 * @version $Id$
 * @author conor.roche
 */
@Path("/propertydesc")
public class PropertyDescResource {

	@GET
	public Data getData() {
		return new Data();
	}

}
