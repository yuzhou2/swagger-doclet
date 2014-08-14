package fixtures.multipleparams;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("/multipleparams")
@SuppressWarnings("javadoc")
public class MultipleParamsResource {

	/**
	 * @csvParams p1
	 */
	@GET
	public void getParams1(@QueryParam("p1") String p1, @QueryParam("p2") String p2) {
		// noop
	}

	/**
	 * @csvParams p1,p2
	 */
	@PUT
	public void putParams2(@QueryParam("p1") String p1, @QueryParam("p2") String p2, Data data) {
		// noop
	}

	/**
	 * @csvParams p2
	 */
	@POST
	public void postParams3(@QueryParam("p1") String p1, @HeaderParam("p2") String p2, Data data) {
		// noop
	}

	/**
	 * @csvParams id,p1,p2
	 */
	@POST
	@Path("/{id}")
	public void postParams4(@PathParam("id") String id, @QueryParam("p1") String p1, @HeaderParam("p2") String p2, Data data) {
		// noop
	}

}
