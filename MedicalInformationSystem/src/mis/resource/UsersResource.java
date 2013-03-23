package mis.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import mis.dao.UserDao;
import mis.model.User;

@Path("/users")
public class UsersResource {
	UriInfo uriInfo;
	Request request;
	String id;

	// public UsersResource(UriInfo uriInfo, Request request, String id)
	// {
	// this.uriInfo = uriInfo;
	// this.request = request;
	// this.id = id;
	// }

	// Return the list of users in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<User> getUsersBrowser() {
		List<User> userObjs = new ArrayList<User>();
		userObjs.addAll(UserDao.instance.getAllUsers().values());
		return userObjs;
	}

	// Return the list of users for applications
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<User> getUsers() {
		List<User> userObjs = new ArrayList<User>();
		userObjs.addAll(UserDao.instance.getAllUsers().values());
		return userObjs;
	}

	@GET
	@Path("{username}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUser(@PathParam("username") String username) {

		return UserDao.instance.getUserByUsername(username);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putUser(User user) {
		String result = "Track saved : " + user.getUsername();
		// return Response.status(201).entity(result).build();
		return putAndGetResponse(user);
	}

	private Response putAndGetResponse(User user) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		if (UserDao.instance.putUserDetails(user.getUsername(), user) != 0) {
			String result = "User inserted";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}

	@DELETE
	@Path("{userid}")
	public void deleteTodo(@PathParam("userid") int userid) {
		int result = UserDao.instance.removeUser(userid);
		if (result == 0)
			throw new RuntimeException("Delete: User with " + userid + " not found");
	}

}
