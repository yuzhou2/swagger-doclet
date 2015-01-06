package fixtures.subresource;

import javax.ws.rs.POST;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The SubResourceL2 represents the l2 sub resource
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
@XmlTransient
public class SubResourceL2 {

	@POST
	public SubSubData z() {
		return null;
	}

}
