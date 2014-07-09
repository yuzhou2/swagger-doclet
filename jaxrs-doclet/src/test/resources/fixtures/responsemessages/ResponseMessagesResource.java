/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.responsemessages;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The ResponseModelResource represents a jaxrs resource for testing response messages
 * @version $Id$
 * @author conor.roche
 * @see fixtures.responsemessages.Response1
 */
@Path("/responsemessages")
public class ResponseMessagesResource {

	/**
	 * @errorResponse 404 not found
	 */
	@GET
	public void swagger11() {
		// noop
	}

	/**
	 * @responseMessage 200 ok
	 * @responseMessage 404 not found `fixtures.responsemessages.Response1
	 */
	@GET
	public void swagger12() {
		// noop
	}

	/**
	 * @successCode 200|ok
	 * @errorCode 401|not authA
	 * @errorCode 401|-|not authB
	 * @errorCode 401|not_authC|If user not authenticated `fixtures.responsemessages.Response1
	 */
	@GET
	public void carma() {
		// noop
	}

}
