/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.deprecation;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * The DeprecationResource represents a resource for testing swagger doc gen for deprecated fields, params and methods
 * @version $Id$
 * @author conor.roche
 */
@Path("/deprecation")
public class DeprecationResource {

	@SuppressWarnings("javadoc")
	@Deprecated
	@GET
	public void deprecatedViaAnnotation() {
		// noop
	}

	/**
	 * @deprecated
	 */
	@SuppressWarnings("dep-ann")
	@GET
	public void deprecatedViaTag() {
		// noop
	}

	@SuppressWarnings("javadoc")
	@POST
	public Data getData(@QueryParam("p1") int p1, @Deprecated @QueryParam("p2") int p2, @Deprecated int p3) {
		return new Data();
	}

}
