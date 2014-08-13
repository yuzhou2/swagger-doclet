package fixtures.propertydesc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The PropertyDescResource represents a jaxrs resource that checks for description fields in the generated model
 * classes
 * @version $Id$
 * @author conor.roche
 */
@Path("/propertydesc")
@SuppressWarnings("javadoc")
public class PropertyDescResource {

	@GET
	public Data getData() {
		return new Data();
	}

}
