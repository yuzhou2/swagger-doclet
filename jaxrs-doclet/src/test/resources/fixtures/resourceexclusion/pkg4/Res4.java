package fixtures.resourceexclusion.pkg4;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/r4")
public class Res4 {

	@GET
	@Path("/4")
	public void get1() {
		// noop
	}

}
