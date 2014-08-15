/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.modelfieldrequired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The ModelFieldRequiredResource represents a resource for testing the handling of required model fields
 * @version $Id$
 * @author conor.roche
 */
@Path("/modelfieldrequired")
@SuppressWarnings("javadoc")
public class ModelFieldRequiredResource {

	@GET
	public Data getData() {
		return new Data();
	}

}
