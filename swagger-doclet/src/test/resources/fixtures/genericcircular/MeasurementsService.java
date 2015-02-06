package fixtures.genericcircular;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("javadoc")
@Path("/measurements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MeasurementsService {

	@Path("/query")
	@POST
	public <G> MeasurementsResult<G> fetchMeasurements(MeasurementsQuery<G> query) {
		return null;
	}
}
