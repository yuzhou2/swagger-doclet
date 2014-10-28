package fixtures.jodatime;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/jodaTime")
public interface JodaTimeResource {

	@GET
	public Data get();

	@POST
	public void post(Data data);

}
