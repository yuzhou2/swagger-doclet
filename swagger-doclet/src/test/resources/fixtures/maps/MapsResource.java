package fixtures.maps;

import java.util.Map;
import java.util.Set;

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

	@GET
	@Path("/c")
	public ModelC getC() {
		return null;
	}

	@GET
	@Path("/d")
	public ModelD getD() {
		return null;
	}

	@GET
	@Path("/e")
	public Map<String, Set<String>> getE() {
		return null;
	}

	@GET
	@Path("/f")
	public Map<String, Map<String, String>> getF() {
		return null;
	}
}
