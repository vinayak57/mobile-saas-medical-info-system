package mis.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mis.model.User;

@Path("/user")
public class UserResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getXML() {
		User userObj = new User();
		userObj.setUsername("mandar");
		userObj.setPassword("mandar");
		return userObj;
	}

	// This can be used to test the integration with the browser
	@GET
	@Produces({ MediaType.TEXT_XML })
	public User getHTML() {
		User userObj = new User();
		userObj.setUsername("mandar");
		userObj.setPassword("mandar");
		return userObj;
	}

}
