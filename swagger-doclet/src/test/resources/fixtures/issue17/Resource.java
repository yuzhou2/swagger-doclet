package fixtures.issue17;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/issue17")
@SuppressWarnings("javadoc")
public class Resource {

	@GET
	public User getUser() {
		return new User();
	}
}
