package fixtures.maps;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * The MapsResource represents a resource for testing maps
 * @version $Id$
 * @author conor.roche
 */
@Path("/maps")
@SuppressWarnings("javadoc")
public class MapsResource {

	@GET
	@Path("/a")
	public ModelA getA() {
		return null;
	}

	@GET
	@Path("/b")
	public ModelB getB() {
		return null;
	}

}
