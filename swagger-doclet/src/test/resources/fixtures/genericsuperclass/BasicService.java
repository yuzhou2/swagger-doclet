package fixtures.genericsuperclass;

import fixtures.genericcircular.*;
import fixtures.genericcircular.MeasurementsQuery;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("javadoc")
public abstract class BasicService<Entity> {

	@Path("/query/{parentId}")
	@GET
	public java.util.List<Entity> findAll(@PathParam("parentId") String parentId) {
		return null;
	}
}
