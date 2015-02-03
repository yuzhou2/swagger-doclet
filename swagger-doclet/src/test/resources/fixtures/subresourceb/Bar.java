package fixtures.subresourceb;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
public abstract class Bar {

	@POST
	@Path("/post")
	public abstract void bar();

}
