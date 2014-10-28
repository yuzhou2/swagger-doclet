package fixtures.namingconvention.lower;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fixtures.namingconvention.Data;

@Path("/lower")
@SuppressWarnings("javadoc")
public class LowerResource {

	@GET
	public Data get() {
		return new Data();
	}

}
