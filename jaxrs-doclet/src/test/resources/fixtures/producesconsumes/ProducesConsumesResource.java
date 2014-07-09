package fixtures.producesconsumes;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The ProducesConsumesResource represents a jaxrs resource that uses the produces and consumes mimetypes
 * @version $Id$
 * @author conor.roche
 */
@Path("/producesconsumes")
public class ProducesConsumesResource {

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Data getData() {
		return new Data("test");
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Data postData(Data data) {
		return data;
	}

}
