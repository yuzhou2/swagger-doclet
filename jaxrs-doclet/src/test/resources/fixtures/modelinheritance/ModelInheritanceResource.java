package fixtures.modelinheritance;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * The ModelInheritanceResource represents a resource for testing model classes that extend from a base and hence
 * should have their ancestor fields
 * @version $Id$
 * @author conor.roche
 */
@Path("/modelinheritance")
public class ModelInheritanceResource {

	@SuppressWarnings("javadoc")
	@POST
	public ChildResponse modelInheritance(ChildPayload payload) {
		return new ChildResponse();
	}

}
