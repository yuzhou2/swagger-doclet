package fixtures.namingconvention.lowerunless;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fixtures.namingconvention.Data;

@Path("/lowerUnless")
@SuppressWarnings("javadoc")
public class LowerUnlessResource {

	@GET
	public Data get() {
		return new Data();
	}

}
