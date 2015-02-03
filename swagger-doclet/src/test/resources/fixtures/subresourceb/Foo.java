package fixtures.subresourceb;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("javadoc")
@Path("/foo")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public abstract class Foo {

	@Path("/bar")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Bar getBar();

}
