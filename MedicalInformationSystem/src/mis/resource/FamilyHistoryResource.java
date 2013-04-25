package mis.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mis.dao.FamilyHistoryDao;
import mis.model.FamilyHistory;

@Path("/familyhistoryinfo")
public class FamilyHistoryResource {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putFamilyHistory(FamilyHistory patientMedInfo) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(patientMedInfo);
	}

	private Response putAndGetResponse(FamilyHistory patientMedInfo) {
		Response res;
		if (FamilyHistoryDao.instance.putFamilyHistoryDetails(patientMedInfo) != 0) {
			String result = "prescription updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
