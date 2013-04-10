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
import mis.dao.PrescriptionDao;
import mis.model.Drug;
import mis.model.Prescription;

@Path("/prescription")
public class PrescriptionResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Prescription> getAllPrescription(@PathParam("tenantid") int tenantid) {
		List<Prescription> prescObjs = new ArrayList<Prescription>();
		prescObjs.addAll(PrescriptionDao.instance.getAllPrescription(tenantid).values());
		return prescObjs;
	}
	
	@GET
	@Path("/{tenantid}/{prescription_id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Prescription getPrescription(@PathParam("tenantid") int tenantid, @PathParam("prescription_id") int prescription_id)
	{
		return PrescriptionDao.instance.getPrescriptionById(prescription_id);
	}
	
	@GET
	@Path("/{tenantid}/{appointment_id}/appointment")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Prescription> getPrescriptionByAppointment(@PathParam("tenantid") int tenantid, @PathParam("appointment_id") int appointment_id)
	{
		List<Prescription> prescObjs = new ArrayList<Prescription>();
		prescObjs.addAll(PrescriptionDao.instance.getPrescriptionByAppointmentId(appointment_id).values());
		return prescObjs;
			}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putPrescription(Prescription prescription) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(prescription);
	}

	private Response putAndGetResponse(Prescription prescription) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		if (PrescriptionDao.instance.putPrescriptionDetails(prescription) != 0) {
			String result = "prescription updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
