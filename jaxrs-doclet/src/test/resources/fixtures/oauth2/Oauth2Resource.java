/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.oauth2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The Oauth2Resource represents a resource for testing swagger authorization
 * @version $Id$
 * @author conor.roche
 */
@Path("/oauth2")
public class Oauth2Resource {

	/**
	 * @scope write:pets
	 */
	@GET
	public void customScope() {
		// do nothing
	}

	/**
	 * @scope write:pets
	 * @scope read:pets
	 */
	@GET
	public void customScopes() {
		// do nothing
	}

	@SuppressWarnings("javadoc")
	@GET
	public void defaultNoAuth() {
		// this method should have no auth applied
	}

	/**
	 * @authentication Required
	 */
	@GET
	public void defaultAuth() {
		// this method should have default scope auth applied
	}

	/**
	 * @authentication Not required
	 */
	@GET
	public void noAuth1() {
		// this does not need authentication
	}

	/**
	 * @noAuth
	 */
	@GET
	public void noAuth2() {
		// this does not need authentication
	}

}
