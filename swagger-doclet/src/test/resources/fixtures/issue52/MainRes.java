package fixtures.issue52;

import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/bidir")
public class MainRes {

	@Path("/a")
	public ResA getA() {
		return null;
	}

}
