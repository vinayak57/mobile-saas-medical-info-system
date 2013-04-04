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

import mis.dao.DrugDao;
import mis.dao.HospitalStaffDao;
import mis.model.Drug;
import mis.model.HospitalStaff;
import mis.model.User;

@Path("/drug")
public class DrugResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Drug> getAllDrug(@PathParam("tenantid") int tenantid) {
		List<Drug> drugObjs = new ArrayList<Drug>();
		drugObjs.addAll(DrugDao.instance.getAllDrug(tenantid).values());
		return drugObjs;
	}
	
	@GET
	@Path("/{tenantid}/{drugid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Drug getDrug(@PathParam("tenantid") int tenantid, @PathParam("drugid") int drugid)
	{
		return DrugDao.instance.getDrugById(drugid);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putDrug(Drug drug) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(drug);
	}

	private Response putAndGetResponse(Drug drug) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		if (DrugDao.instance.putDrugDetails(drug) != 0) {
			String result = "Drug updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
