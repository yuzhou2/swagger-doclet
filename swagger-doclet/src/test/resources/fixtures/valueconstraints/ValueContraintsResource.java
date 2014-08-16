package fixtures.valueconstraints;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * The ValueContraintsResource represents a resource for testing value contrainst; enum and min/max values of params and properties.
 * @version $Id$
 * @author conor.roche
 */
@Path("/valueconstraints")
@SuppressWarnings("javadoc")
public class ValueContraintsResource {

	@POST
	public Data postData(Data data, @QueryParam(value = "enumValue") EnumValue enumValue) {
		return data;
	}

	/**
	 * @paramsMinValue p1 5
	 * @paramsMaxValue p1 15
	 */
	@POST
	@Path("/1")
	public void putMinMax(@QueryParam("p1") int p1) {
		// noop
	}

	/**
	 * @paramsMinValue p1 6.5 p2 10
	 * @paramsMaxValue p1 9 p2 17
	 */
	@POST
	@Path("/2")
	public void putMinMaxes(@QueryParam("p1") double p1, @QueryParam("p2a") long p2) {
		// noop
	}
}
