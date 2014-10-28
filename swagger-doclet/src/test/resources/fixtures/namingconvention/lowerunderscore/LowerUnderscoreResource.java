package fixtures.namingconvention.lowerunderscore;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fixtures.namingconvention.Data;

@Path("/lowerUnderscore")
@SuppressWarnings("javadoc")
public class LowerUnderscoreResource {

	@GET
	public Data get() {
		return new Data();
	}

}
