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
}
