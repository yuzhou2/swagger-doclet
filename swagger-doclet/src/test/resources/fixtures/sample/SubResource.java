package fixtures.sample;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@SuppressWarnings("javadoc")
public class SubResource {

	@GET
	@Path("annotated")
	public String sayHello(@QueryParam("name") @DefaultValue("World2") String name) {
		return "Hello, " + name + "!";
	}

	@POST
	public int createSub() {
		return 0;
	}
}
