package mis.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mis.dao.PatientMedInfoDao;
import mis.dao.SurgicalHistoryDao;
import mis.model.PatientMedInfo;
import mis.model.SurgicalHistory;

@Path("/surgicalhistoryinfo")
public class SurgicalHistoryResource {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putSurgicalHistory(SurgicalHistory patientMedInfo) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(patientMedInfo);
	}

	private Response putAndGetResponse(SurgicalHistory patientMedInfo) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		if (SurgicalHistoryDao.instance.putSurgicalHistoryDetails(patientMedInfo) != 0) {
			String result = "prescription updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
