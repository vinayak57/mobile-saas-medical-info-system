package mis.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import mis.dao.PatientDao;
import mis.dao.UserDao;
import mis.model.Patient;
import mis.model.User;


@Path("/patients")
public class PatientResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Patient> getPatients(@PathParam("tenantid") int tenantid) {
		List<Patient> patientObjs = new ArrayList<Patient>();
		patientObjs.addAll(PatientDao.instance.getAllPatients(tenantid).values());
		
		Collections.sort(patientObjs, new Comparator<Patient>() {

	        public int compare(Patient o1, Patient o2) {
	        	String str2 = o2.getFname() + o2.getLname();
	        	String str1 = o1.getFname() + o1.getLname();
	        	
	            return str1.compareTo(str2);
	        }
	    });
		return patientObjs;
	}
	
	@GET
	@Path("/search/{tenantid}/{fname}/{lname}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Patient> getPatientsByName(@PathParam("tenantid") int tenantid, @PathParam("fname") String fname, @PathParam("lname") String lname) {
		List<Patient> patientObjs = new ArrayList<Patient>();
		patientObjs.addAll(PatientDao.instance.getAllPatientsByName(tenantid,fname,lname).values());
		return patientObjs;
	}
	
	@GET
	@Path("/{tenantid}/{userId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Patient getUser(@PathParam("tenantid") int tenantid, @PathParam("userId") int userId)
	{

		return PatientDao.instance.getPatientByUserId(userId, tenantid);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putPatient(Patient patient) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(patient);
	}

	private Response putAndGetResponse(Patient patient) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		System.out.println("here");
		if (PatientDao.instance.putPatientDetails(patient) != 0) {
			String result = "User updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	
}
