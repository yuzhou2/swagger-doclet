package fixtures.jodatime;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@SuppressWarnings("javadoc")
@Path("/jodaTime")
public interface JodaTimeResource {

	@GET
	public Data get();

	@POST
	public void post(Data data);

	@GET
	@Path("/localdate")
	public LocalDate getLocalDate();

	@POST
	@Path("/localdate")
	public void setLocalDate(LocalDate data);

	@GET
	@Path("/localtime")
	public LocalTime getLocalTime();

	@POST
	@Path("/localtime")
	public void setLocalTime(LocalTime data);

}
