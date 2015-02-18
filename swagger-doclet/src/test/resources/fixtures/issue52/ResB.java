package fixtures.issue52;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
public class ResB {

	@Path("/get")
	@GET
	public String get() {
		return null;
	}

	@Path("/a")
	public ResA getA() {
		return null;
	}

}
