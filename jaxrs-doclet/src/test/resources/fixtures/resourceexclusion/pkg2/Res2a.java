package fixtures.resourceexclusion.pkg2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/r2a")
public class Res2a {

	@GET
	@Path("/2a")
	public void get1() {
		// noop
	}

}
