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
