/*
 * Copyright © 2014 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.responsemodel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * The ResponseModelResource represents a jaxrs resource for testing custom response models
 * @version $Id$
 * @author conor.roche
 * @see fixtures.responsemodel.Response3
 */
@Path("/responsemodel")
public class ResponseModelResource {

	@SuppressWarnings("javadoc")
	@GET
	public Response unspecifiedResponse() {
		return Response.ok().build();
	}

	@SuppressWarnings("javadoc")
	@GET
	public Response1 responseDefinedViaReturn() {
		return new Response1();
	}

	/**
	 * @responseType fixtures.responsemodel.Response2
	 * @see fixtures.responsemodel.Response2
	 */
	@SuppressWarnings("javadoc")
	@GET
	public Response responseDefinedViaTag() {
		return Response.ok().entity(new Response2()).build();
	}

	/**
	 * @see fixtures.responsemodel.Response1
	 * @see fixtures.responsemodel.Response2
	 * @responseMessage 200 if ok `fixtures.responsemodel.Response1
	 * @responseMessage 404 if no result found `fixtures.responsemodel.Response2
	 * @responseMessage 500 if an internal error occurred
	 */
	@SuppressWarnings("javadoc")
	@GET
	public Response responseDefinedViaResponseCode() {
		return Response.ok().entity(new Response2()).build();
	}

	/**
	 * @responseType fixtures.responsemodel.Response2
	 * @see fixtures.responsemodel.Response2
	 * @responseMessage 404 if no result found `fixtures.responsemodel.Response3
	 */
	@SuppressWarnings("javadoc")
	@GET
	public Response responseMix() {
		return Response.ok().entity(new Response3()).build();
	}

}
