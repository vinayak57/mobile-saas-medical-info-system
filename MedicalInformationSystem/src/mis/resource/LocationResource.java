package mis.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import mis.dao.LocationDao;
import mis.dao.PatientDao;
import mis.model.Location;
import mis.model.Patient;


@Path("/location")
public class LocationResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Location> getLocations(@PathParam("tenantid") int tenantid) {
		List<Location> locationObjs = new ArrayList<Location>();
		locationObjs.addAll(LocationDao.instance.getAllLocations(tenantid).values());
		return locationObjs;
	}
	
	@GET
	@Path("/{tenantid}/{locationId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Location getLocation(@PathParam("tenantid") int tenantid, @PathParam("locationId") int locationId)
	{
		return LocationDao.instance.getLocationById(locationId);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putLocation(Location loc) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(loc);
	}

	private Response putAndGetResponse(Location loc) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		if (LocationDao.instance.putLocationDetails(loc) != 0) {
			String result = "Location updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
