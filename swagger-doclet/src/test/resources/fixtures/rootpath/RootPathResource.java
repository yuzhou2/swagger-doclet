package fixtures.rootpath;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
@SuppressWarnings("javadoc")
public class RootPathResource {

	@GET
	@Path("/v1/message")
	public String getMessage() {
		return "success";
	}

}
