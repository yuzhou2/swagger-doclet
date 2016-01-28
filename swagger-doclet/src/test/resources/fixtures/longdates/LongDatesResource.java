package fixtures.longdates;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
@Path("/longdates")
public interface LongDatesResource {

	@GET
	public Data get();

	@POST
	public void post(Data data);

	@GET
	@Path("/date")
	public Date getDate();

	@POST
	@Path("/date")
	public void setDate(Date data);

	@GET
	@Path("/sqldate")
	public java.sql.Date getSqlDate();

	@POST
	@Path("/sqldate")
	public void setSqlDate(java.sql.Date data);

	@GET
	@Path("/calendar")
	public Calendar getCalendar();

	@POST
	@Path("/calendar")
	public void setCalendar(Calendar data);

}
