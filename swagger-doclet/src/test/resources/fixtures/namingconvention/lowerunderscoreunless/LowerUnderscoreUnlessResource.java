package fixtures.namingconvention.lowerunderscoreunless;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fixtures.namingconvention.Data;

@Path("/lowerUnderscoreUnless")
@SuppressWarnings("javadoc")
public class LowerUnderscoreUnlessResource {

	@GET
	public Data get() {
		return new Data();
	}

}
