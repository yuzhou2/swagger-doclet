package fixtures.responsemessagesorting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The ResponseMessagesSortingResource represents a test resource for sorting of response messages
 * @version $Id$
 * @author conor.roche
 */
@Path("/responsemessagesorting")
public class ResponseMessagesSortingResource {

	/**
	 * @responseMessage 302 redirect
	 * @defaultErrorType fixtures.responsemessagesorting.Response3
	 * @successCode 200|ok
	 * @errorCode 401|not authA
	 * @errorCode 401|-|not authB
	 * @errorCode 401|not_authC|If user not authenticated `fixtures.responsemessagesorting.Response1
	 */
	@GET
	public void carma() {
		// noop
	}

}
