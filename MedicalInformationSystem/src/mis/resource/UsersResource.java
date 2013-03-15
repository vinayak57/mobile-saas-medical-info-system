package mis.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mis.dao.UserDao;
import mis.model.User;

@Path("/users")
public class UsersResource {

	
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
	  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	  public List<User> getUsers() {
	    List<User> userObjs = new ArrayList<User>();
	    userObjs.addAll(UserDao.instance.getAllUsers().values());
	    return userObjs; 
	  }
	  
	  @GET @Path("{username}")
      @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
      public User getUser(@PathParam("username") String username){
      
		  return UserDao.instance.getUserByUsername(username);
      }

	  
}
