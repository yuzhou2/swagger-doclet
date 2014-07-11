package fixtures.paramrequired;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * The ParamRequiredResource represents a jaxrs resource that uses optional and required parameter tags
 * @version $Id$
 * @author conor.roche
 */
@Path("/paramrequired")
@SuppressWarnings("javadoc")
public class ParamRequiredResource {

	/**
	 * @requiredParams p1
	 */
	@GET
	public void getParams1(@QueryParam("p1") int p1, @QueryParam("p2") int p2) {
		// noop
	}

	/**
	 * @requiredParams p1,p2
	 */
	@PUT
	public void putParams2(@QueryParam("p1") int p1, @QueryParam("p2") int p2, Data data) {
		// noop
	}

	/**
	 * @optionalParams data
	 */
	@POST
	public void postParams3(@QueryParam("p1") int p1, @QueryParam("p2") int p2, Data data) {
		// noop
	}

	/**
	 * @optionalParams id,data
	 */
	@POST
	@Path("/{id}")
	public void postParams4(@PathParam("id") String id, @QueryParam("p1") int p1, @QueryParam("p2") int p2, Data data) {
		// noop
	}

}
