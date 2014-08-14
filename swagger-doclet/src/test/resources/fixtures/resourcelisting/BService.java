package fixtures.resourcelisting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @resourcePriority 1
 * @resourceDescription B
 */
@Path("/b")
public class BService {

	@SuppressWarnings("javadoc")
	@GET
	public void getX() {
		// noop
	}
}
