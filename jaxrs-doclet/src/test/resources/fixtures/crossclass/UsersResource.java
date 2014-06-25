/*
 * Copyright © 2010 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclass;

import static fixtures.crossclass.UsersResource.URLBASE;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The UsersResource represents the users resource
 * @version $Id: UsersResource.java 120804 2014-06-14 17:01:08Z conor.roche $
 * @author conorroche
 */
@Path("/" + URLBASE)
public interface UsersResource {

	/**
	 * This is a constant for the url base this api resource is exposed under
	 */
	public static final String URLBASE = "object/users";

	/**
	 * This is the value of the user id that means the user to be accessed is the currently authenticated user
	 */
	public static final String AUTH_USER_ID = "SELF";

	/**
	 * This is the relative resource path for a preference entity
	 */
	public static final String PREFERENCE_PATH = "{userId}/preferences/{preferenceName}";

	/**
	 * This is the relative resource path for a preferences entity
	 */
	public static final String PREFERENCES_PATH = "{userId}/preferences";

	/**
	 * This is the path to the request password reset endpoint
	 */
	public static String PATH_PASSWORD_RESET_REQUEST = "passwordreset/request";

	/**
	 * This is the path to the confirm password reset endpoint
	 */
	public static String PATH_PASSWORD_RESET_CONFIRM = "passwordreset/confirm";

	/**
	 * @endpointName Rights
	 * @parentEndpointName Users
	 * @category Aspects
	 * @description This gets the rights of the user with the given user id, if you pass SELF as the user id then this will get the user rights
	 *              of the authenticated user.
	 *              The response for SELF will be a 303 see other response that redirects to the authenticated user's id.
	 * @successCode 200|If the rights were retrieved ok
	 * @successCode 303|When SELF is passed as the user id this will be a redirect to get the user rights with the id of the authenticated user
	 * @errorCode 404|user_not_found|Response if no user was found with the given id
	 * @errorCode 403|access_denied|Response if the authenticated user does not have authority to access the user rights
	 * @errorCode 403|authentication_required|Response if the user id is SELF but not authentication credentials have been provided
	 * @errorCode 403|-|Response if the authenticated user does not have authority to access this API method
	 * @errorCode 401|-|Bad credentials if the API is accessed without providing correct authentication data
	 * @errorCode 400|invalid_user_id|If the given user id is neither SELF nor a valid integer number
	 * @outputType fixtures.crossclass.UserRights
	 * @see fixtures.crossclass.UserRights
	 * @authentication Required
	 * @param userId The user id of the user to get rights of, if you pass SELF as the user id then this will get the user rights
	 *            of the authenticated user
	 * @return The rights of the user with the given user id
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{userId}/rights")
	public Response getUserRights(@PathParam("userId") String userId);

}
