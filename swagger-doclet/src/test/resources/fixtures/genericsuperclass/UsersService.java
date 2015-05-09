package fixtures.genericsuperclass;

import fixtures.genericsuperclass.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("javadoc")
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersService extends BasicService<User> {

    @Override
	public java.util.List<User> findAll(String parentId) {
		return super.findAll(parentId);
	}
}
