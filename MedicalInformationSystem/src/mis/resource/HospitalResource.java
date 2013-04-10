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

import mis.dao.HospitalDao;
import mis.dao.HospitalStaffDao;
import mis.model.Hospital;
import mis.model.HospitalStaff;
import mis.model.User;

@Path("/hospital")
public class HospitalResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Hospital> getAllHospital(@PathParam("tenantid") int tenantid) {
		List<Hospital> hospitalObjs = new ArrayList<Hospital>();
		hospitalObjs.addAll(HospitalDao.instance.getAllHospital(tenantid).values());
		return hospitalObjs;
	}
	
//	@GET
//	@Path("/{tenantid}/{location_id}")
//	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	public List<Hospital> getAllHospitalByLocation(@PathParam("tenantid") int tenantid, @PathParam("location_id") int location_id)
//	{
//		List<Hospital> hospitalObjs = new ArrayList<Hospital>();
//		hospitalObjs.addAll(HospitalDao.instance.getAllHospitalByLocation(tenantid,location_id).values());
//		return hospitalObjs;
//	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putHospital(Hospital hospital) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(hospital);
	}

	private Response putAndGetResponse(Hospital hospital) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		System.out.println("here");
		if (HospitalDao.instance.putHospitalDetails(hospital) != 0) {
			String result = "User updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
