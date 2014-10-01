package fixtures.issue17b;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/users")
@SuppressWarnings("javadoc")
public interface UserService {

	@POST
	@Consumes(APPLICATION_JSON)
	Response createUser(User user);

	@GET
	@Produces(APPLICATION_JSON)
	List<User> retrieveAllUsers(@QueryParam("start") @DefaultValue("0") int start, @QueryParam("max") @DefaultValue("0") int max);

	@GET
	@Produces(APPLICATION_JSON)
	@Path("/{username}")
	User retrieveUser(@PathParam("username") String username);

	@DELETE
	@Path("/{username}")
	void deleteUser(@PathParam("username") String username);

}
