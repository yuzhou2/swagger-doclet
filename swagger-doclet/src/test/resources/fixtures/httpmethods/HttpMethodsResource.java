package fixtures.httpmethods;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * The HttpMethodsResource represents a resource that tests various HTTP methods
 * @version $Id$
 * @author conor.roche
 */
@Path("/httpmethods")
@SuppressWarnings("javadoc")
public class HttpMethodsResource {

	@POST
	public void post(Data data) {
		// noop
	}

	@PUT
	public void put(Data data) {
		// noop
	}

	@PATCH
	public void patch(Data data) {
		// noop
	}

	@OPTIONS
	public Data options() {
		return null;
	}

	@GET
	public Data get() {
		return null;
	}

	@DELETE
	public void delete() {
		// noop
	}

	@HEAD
	public void head() {
		// noop
	}

}
