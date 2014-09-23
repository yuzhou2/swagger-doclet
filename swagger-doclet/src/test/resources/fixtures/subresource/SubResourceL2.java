package fixtures.subresource;

import javax.ws.rs.POST;

/**
 * The SubResourceL2 represents the l2 sub resource
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class SubResourceL2 {

	@POST
	public SubSubData z() {
		return null;
	}

}
