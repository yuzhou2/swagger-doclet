package fixtures.xmlaccesstype;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * The XmlAccessTypeResource represents a resource for testing XmlAccessType behaviour
 * @version $Id$
 * @author conor.roche
 */
@Path("/xmlaccesstype")
@SuppressWarnings("javadoc")
public class XmlAccessTypeResource {

	@POST
	@Path("/fieldaccess")
	public FieldAccessPojo postFieldAccess(FieldAccessPojo data) {
		return data;
	}

}
