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

import mis.dao.PatientMedInfoDao;
import mis.dao.PrescriptionDao;
import mis.model.PatientMedInfo;
import mis.model.Prescription;

@Path("/patientmedicalinfo")
public class PatientMedInfoResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{patientid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<PatientMedInfo> getAllPatientMedInfo(@PathParam("patientid") int patientid) {
		List<PatientMedInfo> patientMedObjs = new ArrayList<PatientMedInfo>();
		patientMedObjs.addAll(PatientMedInfoDao.instance.getAllPatientMedInfo(patientid).values());
		return patientMedObjs;
	}
	
	@GET
	@Path("/{patientid}/{patient_med_info_id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public PatientMedInfo getAllPatientMedInfoById(@PathParam("patientid") int patientid, @PathParam("patient_med_info_id") int patient_med_info_id)
	{
		return PatientMedInfoDao.instance.getAllPatientMedInfoById(patient_med_info_id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putPrescription(PatientMedInfo patientMedInfo) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(patientMedInfo);
	}

	private Response putAndGetResponse(PatientMedInfo patientMedInfo) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		if (PatientMedInfoDao.instance.putPatientMedInfoDetails(patientMedInfo) != 0) {
			String result = "prescription updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
