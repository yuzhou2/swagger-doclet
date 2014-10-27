package fixtures.issue17d;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import fixtures.issue17d.data.BlacklistedDevice;

@SuppressWarnings("javadoc")
@Path("/issue17d")
public interface BlacklistService {

	@POST
	@Consumes(APPLICATION_JSON)
	Response addBlacklistedDevice(BlacklistedDevice blacklistedDevice);

	@GET
	@Path("/{device_name_type}-{device_name}")
	@Produces(APPLICATION_JSON)
	BlacklistedDevice getBlacklistedDevice(@PathParam("device_name_type") String deviceNameType, @PathParam("device_name") String deviceName);

	@GET
	@Produces(APPLICATION_JSON)
	List<BlacklistedDevice> getBlacklistedDevices(@QueryParam("start") @DefaultValue("-1") int startRow, @QueryParam("max") @DefaultValue("-1") int maxRows,
			@QueryParam("account_id") String accountId, @QueryParam("partial_name") String partialName);

	@PUT
	@Path("/{device_name_type}-{device_name}")
	@Consumes(APPLICATION_JSON)
	void updateBlacklistedDevice(@PathParam("device_name_type") String deviceNameType, @PathParam("device_name") String deviceName,
			BlacklistedDevice blacklistedDevice);

	@DELETE
	@Path("/{device_name_type}-{device_name}")
	@Consumes(APPLICATION_JSON)
	void deleteBlacklistedDevice(@PathParam("device_name_type") String deviceNameType, @PathParam("device_name") String deviceName);
}
