package fixtures.subresource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * The MainResource represents the main jaxrs resource that uses a sub resource.
 * @version $Id$
 * @author conor.roche
 */
@Path("/a")
@SuppressWarnings("javadoc")
public class MainResource {

	@Path("{id}/sub")
	public SubResource getSubResource(@PathParam("id") String fooId) {
		return new SubResource();
	}

	@Path("{id}/subb")
	public Class<SubResourceB> getSubResourceB(@PathParam("id") String fooId) {
		return null;
	}

}
