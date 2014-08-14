package fixtures.primitives;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/primitives/dates")
@SuppressWarnings("javadoc")
public class DatesResource {

	@GET
	public Date get() {
		return new Date();
	}

	@POST
	public Response create(Date value) {
		return Response.ok().build();
	}
}
