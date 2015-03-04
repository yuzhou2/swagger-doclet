package fixtures.informationcard;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("javadoc")
@Path("/v1")
public interface InformationCardResource {

	@GET
	@Path("/users/{userId}/informationCards")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public InformationCardCollection getInformationCards(@PathParam("userId") String userId, @QueryParam("lon") Double lon, @QueryParam("lat") Double lat);

}
