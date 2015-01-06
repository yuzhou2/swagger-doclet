package fixtures.subresource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("javadoc")
@XmlTransient
public class SubResourceB {

	@GET
	@Path("1")
	public SubData x() {
		return null;
	}

	@POST
	public int y() {
		return 0;
	}

	@Path("/sub2b")
	public Class<SubResourceL2> getSubResourceL2() {
		return null;
	}
}
