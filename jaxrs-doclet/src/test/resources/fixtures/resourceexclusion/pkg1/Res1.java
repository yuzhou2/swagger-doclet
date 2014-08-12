package fixtures.resourceexclusion.pkg1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/r1")
@Deprecated
public class Res1 {

	@GET
	@Path("/1")
	public void get1() {
		// noop
	}

}
