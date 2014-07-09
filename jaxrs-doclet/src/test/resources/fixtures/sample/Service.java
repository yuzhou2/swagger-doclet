package fixtures.sample;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/foo")
@SuppressWarnings("javadoc")
public class Service {

	/**
	 * @errorResponse 404 not found
	 * @errorResponse ABC invalid code won't be parsed
	 */
	@GET
	public String sayHello(@QueryParam("name") @DefaultValue("World") String name) {
		return "Hello, " + name + "!";
	}

	@POST
	public int createSpeech(String speech) {
		return speech.hashCode();
	}

	@Path("/annotated")
	@POST
	public int createSpeechWithAnnotatedPayload(@Deprecated String speech) {
		return speech.hashCode();
	}

	@Path("{fooId}/sub")
	public SubResource getSubResource(@PathParam("fooId") String fooId) {
		return new SubResource();
	}
}
