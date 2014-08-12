package fixtures.resourceexclusion.pkg2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/r2b")
public class Res2b {

	@GET
	@Path("/2b")
	public void get1() {
		// noop
	}

}
