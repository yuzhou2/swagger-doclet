package fixtures.subresource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
public class SubResource {

	@GET
	@Path("1")
	public SubData x() {
		return null;
	}

	@POST
	public int y() {
		return 0;
	}

	@Path("/sub2")
	public SubResourceL2 getSubResourceL2() {
		return new SubResourceL2();
	}
}
