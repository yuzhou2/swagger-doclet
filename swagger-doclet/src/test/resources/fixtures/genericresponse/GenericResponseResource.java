package fixtures.genericresponse;

import java.util.Collections;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.JResponse;

/**
 * The GenericResponseResource represents a test class for generic responses
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
@Path("/genericresponse")
public class GenericResponseResource {

	@GET
	public Parameterized<String, Integer> getParameterized() {
		return new Parameterized<String, Integer>();
	}

	@GET
	public JResponse<String> getJResponse() {
		return new JResponse<String>(200, null, "");
	}

	@GET
	public Response getOptional(@QueryParam("name") com.google.common.base.Optional<String> name) {
		return null;
	}

	@GET
	public Response getOptional2(@QueryParam("name") jersey.repackaged.com.google.common.base.Optional<Integer> name) {
		return null;
	}

	@GET
	public Map<String, Integer> getIntMap() {
		return Collections.emptyMap();
	}

	/**
	 * @returnType fixtures.genericresponse.Parameterized2
	 */
	@GET
	public Response getParameterized2() {
		return null;
	}
}
