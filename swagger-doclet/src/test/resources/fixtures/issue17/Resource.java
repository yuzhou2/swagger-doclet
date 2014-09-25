package fixtures.issue17;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/issue17")
@SuppressWarnings("javadoc")
public class Resource {

	@GET
	public User getUser() {
		return new User();
	}

	@POST
	public Response createUser(User2 user) {
		return Response.ok().build();
	}
}
