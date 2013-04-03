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

import mis.dao.HospitalStaffDao;
import mis.dao.PatientDao;
import mis.model.HospitalStaff;
import mis.model.Patient;
import mis.model.User;

@Path("/staff")
public class HospitalStaffResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<HospitalStaff> getAllStaff(@PathParam("tenantid") int tenantid) {
		List<HospitalStaff> hospitalObjs = new ArrayList<HospitalStaff>();
		hospitalObjs.addAll(HospitalStaffDao.instance.getAllStaff(tenantid).values());
		return hospitalObjs;
	}
	
	@GET
	@Path("/{tenantid}/{staffid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUser(@PathParam("tenantid") int tenantid, @PathParam("staffid") int staffid)
	{

		return HospitalStaffDao.instance.getStaffById(staffid, tenantid);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putStaff(HospitalStaff staff) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(staff);
	}

	private Response putAndGetResponse(HospitalStaff staff) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		System.out.println("here");
		if (HospitalStaffDao.instance.putStaffDetails(staff) != 0) {
			String result = "User updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	
}
