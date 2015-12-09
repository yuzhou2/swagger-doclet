package fixtures.interfacedocumentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("javadoc")
@Path("/users")
public interface ParentInterface {

	/**
	 * first sentence.
	 * remaining sentence
	 */
	@Path("/query/{param}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	String findAll(@PathParam("param") String param);
}
