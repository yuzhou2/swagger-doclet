package fixtures.issue17c;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/users")
public interface UserService {

	@POST
	@Consumes(APPLICATION_JSON)
	Response createUser(User user);

	@GET
	@Produces(APPLICATION_JSON)
	List<User> retrieveAllUsers(@QueryParam("start") @DefaultValue("0") int start, @QueryParam("max") @DefaultValue("0") int max);

	@GET
	@Produces(APPLICATION_JSON)
	@Path("/{username}")
	User retrieveUser(@PathParam("username") String username);

	@DELETE
	@Path("/{username}")
	void deleteUser(@PathParam("username") String username);

	// @PUT
	// @Path("/{username}/account_roles/{account_id}-{account_role_id}")
	// void addUserAccountRole(@PathParam("username") String username, @PathParam("account_id") int accountId, @PathParam("account_role_id") int accountRoleId);
	//
	// @GET
	// @Produces(APPLICATION_JSON)
	// @Path("/{username}/account_roles/{sms_account_id}")
	// List<AccountRole> retrieveUserAccountRoles(@PathParam("username") String username, @PathParam("sms_account_id") String smsAccountId);
	//
	// @DELETE
	// @Path("/{username}/account_roles/{account_id}-{account_role_id}")
	// void removeUserAccountRole(@PathParam("username") String username, @PathParam("account_id") int accountId, @PathParam("account_role_id") int
	// accountRoleId);
	//
	// @GET
	// @Produces(APPLICATION_JSON)
	// @Path("/{username}/system_roles")
	// List<SystemRole> retrieveUserSystemRoles(@PathParam("username") String username);
	//
	// @PUT
	// @Path("/{username}/system_roles/{system_role_id}")
	// void addUserSystemRole(@PathParam("username") String username, @PathParam("system_role_id") int systemRoleId);
	//
	// @DELETE
	// @Path("/{username}/system_roles/{system_role_id}")
	// void removeUserSystemRole(@PathParam("username") String username, @PathParam("system_role_id") int systemRoleId);
}
