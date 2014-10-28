package fixtures.namingconvention.upperunless;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fixtures.namingconvention.Data;

@Path("/upperUnless")
@SuppressWarnings("javadoc")
public class UpperUnlessResource {

	@GET
	public Data get() {
		return new Data();
	}

}
