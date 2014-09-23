package fixtures.duperesourcepaths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/resource")
public interface ResourceInterfaceTwo {

	@GET
	@Path("/bar")
	void doSomething();
}