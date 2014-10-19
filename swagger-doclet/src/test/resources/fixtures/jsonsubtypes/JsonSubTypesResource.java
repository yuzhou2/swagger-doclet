package fixtures.jsonsubtypes;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@SuppressWarnings("javadoc")
@Path("/jsonSubTypesResource")
public class JsonSubTypesResource {

	@GET
	@Path("/animal1")
	public Animal1 getAnimal1() {
		return null;
	}

	@POST
	@Path("/animal2")
	public Response postAnimal2(Animal2 animal) {
		return Response.ok().build();
	}

	@GET
	@Path("/animal3")
	public Collection<Animal3> getAnimal3() {
		return null;
	}

}
