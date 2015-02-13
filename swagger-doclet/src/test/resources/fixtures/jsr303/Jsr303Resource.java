package fixtures.jsr303;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * The Jsr303Resource represents a resource for testing jsr303 annotations for model fields and params
 * @version $Id$
 * @author conor.roche
 */
@Path("/jsr303")
@SuppressWarnings("javadoc")
public class Jsr303Resource {

	@PUT
	@Path("/1")
	public void putMinMax(@Size(min = 5, max = 15) @QueryParam("p1") int p1) {
		// noop
	}

	@PUT
	@Path("/2")
	public void putDecimalMinMax(@DecimalMin("6.5") @DecimalMax("10") @QueryParam("p1") double p1) {
		// noop
	}

	@PUT
	@Path("/3")
	public void putOptionalMandatory(@NotNull @QueryParam("p1") String p1, @Null Data p2) {
		// noop
	}
}
