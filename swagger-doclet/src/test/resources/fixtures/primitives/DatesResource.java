package fixtures.primitives;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @priority 9
 */
@Path("/primitives/dates")
@SuppressWarnings("javadoc")
public class DatesResource {

	@GET
	public Date get() {
		return new Date();
	}

	@POST
	public Response create(Date value) {
		return Response.ok().build();
	}

	@GET
	@Path("/sqldate")
	public java.sql.Date getSqlDate() {
		return null;
	}

	@POST
	@Path("/sqldate")
	public Response createSqlDate(java.sql.Date value) {
		return Response.ok().build();
	}

	@GET
	@Path("/calendar")
	public Calendar getCalendar() {
		return Calendar.getInstance();
	}

	@POST
	@Path("/calendar")
	public Response createCalendar(Calendar value) {
		return Response.ok().build();
	}

}
