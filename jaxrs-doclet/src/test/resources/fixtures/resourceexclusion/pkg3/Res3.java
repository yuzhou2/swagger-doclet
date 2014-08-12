package fixtures.resourceexclusion.pkg3;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @hidden
 */
@SuppressWarnings("javadoc")
@Path("/r3")
public class Res3 {

	@GET
	@Path("/3")
	public void get1() {
		// noop
	}

}
