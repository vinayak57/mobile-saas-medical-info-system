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
import javax.ws.rs.core.Response;

import mis.dao.AppointmentDetailsDao;
import mis.dao.DrugDao;
import mis.dao.EmergencyRequestDao;
import mis.model.AppointmentDetail;
import mis.model.Drug;
import mis.model.EmergencyRequest;

@Path("/emergency")
public class EmergencyRequestResource {

	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<EmergencyRequest> getOpenRequests(@PathParam("tenantid") int tenantid) {
		List<EmergencyRequest> objs = new ArrayList<EmergencyRequest>();
		objs.addAll(EmergencyRequestDao.instance.getAllOpenEmergencyRequests(tenantid));
		return objs;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putEmergency(EmergencyRequest emergency) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(emergency);
	}

	private Response putAndGetResponse(EmergencyRequest emergency) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		if (EmergencyRequestDao.instance.putEmergencyRequest(emergency) != 0) {
			String result = "Drug updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
}
