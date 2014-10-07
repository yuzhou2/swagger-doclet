package fixtures.exclusionresponsetype;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SuppressWarnings("javadoc")
@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdersResource {

	/**
	 * @responseType fixtures.exclusionresponsetype.SomeDTO
	 */
	@GET
	@Path("/{id}")
	public Response getOrder(@PathParam("id") Long id) {
		SomeDTO someDTO = new SomeDTO();
		someDTO.setId(id);
		return Response.ok(someDTO).build();
	}

	/**
	 * @responseType java.util.List<fixtures.exclusionresponsetype.SomeDTO>
	 * @exclude
	 */
	@GET
	@Path("/testOrders")
	public Response testOrders() {
		return Response.ok().build();
	}
}
