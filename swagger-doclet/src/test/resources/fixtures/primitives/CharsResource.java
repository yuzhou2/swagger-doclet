package fixtures.primitives;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@SuppressWarnings("javadoc")
@Path("/primitives/chars")
public class CharsResource {

	@GET
	public char get() {
		return 0;
	}

	@POST
	public Response create(char value) {
		return Response.ok().build();
	}
}
