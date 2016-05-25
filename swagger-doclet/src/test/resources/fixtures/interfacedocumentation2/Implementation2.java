package fixtures.interfacedocumentation2;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@SuppressWarnings("javadoc")
public class Implementation2 implements ParentInterface2 {

	@Path("/query/{param}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	@Override
	public String findAll(@PathParam("param") String param) {
		return null;
	}
}
