package fixtures.resourcelisting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @resourcePriority 3
 * @resourceDescription C
 */
@Path("/c")
public class CService {

	@SuppressWarnings("javadoc")
	@GET
	public void getX() {
		// noop
	}
}
