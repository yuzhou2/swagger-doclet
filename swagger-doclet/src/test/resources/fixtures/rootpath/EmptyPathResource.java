package fixtures.rootpath;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("")
@SuppressWarnings("javadoc")
public class EmptyPathResource {

	@GET
	@Path("/v2/message")
	public String getMessage() {
		return "success";
	}

}
