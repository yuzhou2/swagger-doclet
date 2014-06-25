/*
 * Copyright © 2012 Avego Ltd., All Rights Reserved.
 * For licensing terms please contact Avego LTD.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fixtures.crossclass;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @version $Id: UserResource.java 120789 2014-06-14 11:31:47Z martin.gerner $
 * @author martingerner
 */
@Path("/v1/object/users")
public interface UserResource {

	/**
	 * @deprecated This only supported an overall balance, to get a users balance you should use
	 *             the get user endpoint and user the creditBalances field.
	 * @hidden
	 * @endpointName User balance
	 * @parentEndpointName Credit Management
	 * @category Aspects
	 * @authentication Required.
	 * @description Retrieves the credit balance of a user.
	 * @successCode 200|The API call completed successfully.
	 * @errorCode 400|-|The user id was not valid.
	 * @errorCode 401|-|The user id was specified as "SELF", but no authentication credentials were provided.
	 * @errorCode 403|access_denied|The authenticated user cannot access the specified user.
	 * @errorCode 404|user_not_found|The object could not be found.
	 * @param id The user id of the user, must match the logged in user id, can be SELF
	 *            as a shortcut to the logged in user id
	 * @return The credit balance for the specified user.
	 */
	@Deprecated
	@GET
	@Path("/{id}/balance")
	public double getBalance(@PathParam("id") String id);

	/**
	 * @endpointName Register user
	 * @parentEndpointName Users
	 * @category Actions
	 * @authentication Required - restricted to authorized Carma API clients.
	 * @description Creates a user. The "destinations", "lastKnownLocation", and "userType" fields of the input object should be ignored.
	 *              If the call is successful the returned object will contain just a uid.
	 *              If the call failed then an error object will be returned and the error detail field will have the two fields: uid and errorFields. uid will
	 *              be set to the user id, and errorFields will contain a
	 *              list of "bad" fields if the data was not valid (e.g., if a name was not provided, or if the password was too short).
	 * @successCode 200|The API call completed successfully.
	 * @errorCode 400|invalid_fields|One of more of the fields did not match the validation check e.g. too long, wrong format
	 *            in this case check the errorFields of the response's errorDetail field for which fields were invalid
	 * @errorCode 404|image_not_found|An image that was specified could not be found.
	 * @errorCode 404|invite_not_found|The specified invite could not be found.
	 * @errorCode 406|email_already_exists|Another user already has the specified email address
	 * @errorCode 429|limit_exceeded|If there are too many requests made to register users with this email
	 * @outputType fixtures.crossclass.UserRegistrationResult
	 * @see fixtures.crossclass.UserRegistrationResult
	 * @param details the user details.
	 * @param template The template that the email validation message be based on
	 * @param inviteId If the person was invited by somebody, specify the invite id here.
	 * @return If the api call succeeded the response entity will be fixtures.crossclass.UserRegistrationResult
	 *         with just the uid
	 *         otherwise com.avego.carma.api.client.ErrorResponseConverter with the errorDetail field set to the
	 *         fixtures.crossclass.UserRegistrationResult
	 *         with the errorFields set
	 */
	@POST
	@Path("/create")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response registerUser(UserRegistrationDetails details, @QueryParam("template") @DefaultValue("rtr_signup_verification") String template,
			@QueryParam("inviteId") Long inviteId);

}
