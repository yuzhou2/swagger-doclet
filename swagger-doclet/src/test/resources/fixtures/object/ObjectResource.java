package fixtures.object;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/objects")
@SuppressWarnings("javadoc")
public class ObjectResource {

	@GET
	public Object get() {
		return new Object();
	}
}
