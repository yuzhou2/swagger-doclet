package fixtures.inputtype;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * The InputTypeResource represents a test case for custom input types on posts/puts
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
@Path("/inputtype")
public class InputTypeResource {

	@PUT
	@Path("/putData1")
	public Response putData1(Data1 data) {
		return Response.ok().build();
	}

	/**
	 * @inputType fixtures.inputtype.Data2
	 */
	@PUT
	@Path("/putData2")
	public Response putData2(Data1 data) {
		return Response.ok().build();
	}

	/**
	 * @inputType fixtures.inputtype.Data2
	 */
	@POST
	@Path("/postData2b")
	public Response postData2b(@QueryParam("p1") int p1, Data1 data) {
		return Response.ok().build();
	}

	/**
	 * @inputType fixtures.inputtype.Data2
	 */
	@POST
	@Path("/postData2c/{p2}")
	public Response postData2c(@QueryParam("p1") int p1, @PathParam("p2") String p2, Data1 data) {
		return Response.ok().build();
	}

}
