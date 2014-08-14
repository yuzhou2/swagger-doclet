package fixtures.crossclassresourcelisting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The ResBase represents a base resource class
 * @version $Id$
 * @author conor.roche
 */
public abstract class ResBase {

	/**
	 * @resource b
	 */
	@GET
	@Path("/z")
	public void getZ() {
		// noop
	}

}
