package fixtures.duperesourcepaths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/resource")
public interface ResourceInterfaceOne {

	@GET
	@Path("/foo")
	void doSomething();
}
