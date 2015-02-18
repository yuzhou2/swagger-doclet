package fixtures.issue52;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
public class ResA {

	@Path("/get")
	@GET
	public String get() {
		return null;
	}

	@Path("/b")
	public ResB getB() {
		return null;
	}

}
