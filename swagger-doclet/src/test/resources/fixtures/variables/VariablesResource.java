package fixtures.variables;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * The VariablesResource represents a resource that tests variables used in the javadoc
 * @version $Id$
 * @author conor.roche
 */
@Path("/variables")
@SuppressWarnings("javadoc")
public class VariablesResource {

	/**
	 * This endpoint ${v1} is for ${v2}
	 * @paramsDefaultValue enumValue ${v3}
	 */
	@POST
	public Data postData(Data data, @QueryParam(value = "enumValue") EnumValue enumValue) {
		return data;
	}

}
