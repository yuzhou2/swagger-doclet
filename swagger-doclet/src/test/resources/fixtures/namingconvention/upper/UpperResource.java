package fixtures.namingconvention.upper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fixtures.namingconvention.Data;

@Path("/upper")
@SuppressWarnings("javadoc")
public class UpperResource {

	@GET
	public Data get() {
		return new Data();
	}

}
