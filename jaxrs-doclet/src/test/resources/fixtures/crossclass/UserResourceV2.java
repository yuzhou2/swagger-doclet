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

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @version $Id: UserResourceV2.java 120655 2014-06-12 12:23:51Z martin.gerner $
 * @author martingerner
 */
@Path("/v2/object/users")
public interface UserResourceV2 {

	/**
	 * @endpointName Get User
	 * @parentEndpointName Users
	 * @category Aspects
	 * @authentication Required for some fields.
	 * @description Retrieves the user details of a user.
	 * @successCode 200|The API call completed successfully.
	 * @errorCode 400|-|The user id was not valid.
	 * @errorCode 401|-|The user id was specified as "SELF", but no authentication credentials were provided.
	 * @errorCode 403|access_denied|The authenticated user cannot access the specified user.
	 * @errorCode 404|not_found|The object could not be found.
	 * @param id The user id of the user, can be SELF as a shortcut to the logged in user id
	 * @param userFields A comma-separated list of fields that should be included with the user object. <br>
	 *            Clients <b>SHOULD</b> ask for the minimum amount of data they require.<br>
	 *            If this parameter is not included then the default is used.<br>
	 *            If this is passed in with an empty value as in ?userFields= then this will return just the user id and a flag indicating whether the user
	 *            account has been deleted. <br>
	 *            The other supported values are:<br>
	 *            FULL_PUBLIC, FULL, NAME, ALIAS, REGISTRATION_TIME, EMAIL,PHOTO_URL, HOME_CITY, COUNTRY, WEBPAGE, <br>
	 *            BIO, LAST_KNOWN_LOCATION, LAST_KNOWN_LOCATION_ADDRESS, IS_CURRENTLY_RIDESHARING, PHONE_NUMBER, <br>
	 *            LAST_TRIP_SEARCH, RATING, IS_FAVOURITE, VALIDATIONS_EMAIL, VALIDATIONS_PHONE, VALIDATIONS_NUM_POSITIVE_REVIEWS,<br>
	 *            VALIDATIONS_CONNECTED_FACEBOOK, VALIDATIONS_AFFILIATES, LAST_SEEN_TIMESTAMP, LAST_LOGIN_TIMESTAMP, GENDER, <br>
	 *            CREDIT_BALANCES, LOCALE, TEST_ACCOUNT_STATUS, LAST_CHANGED_TIMESTAMP_BASIC, ACHIEVEMENTS, DISTANCE<br>
	 *            NOTE: The calling user can see all of their own details but will only see a subset of the public fields of another user.
	 * @return A user profile for the specified user.
	 */
	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User getUser(@PathParam("id") String id, @QueryParam("userFields") @DefaultValue("FULL_PUBLIC") String userFields);
}
