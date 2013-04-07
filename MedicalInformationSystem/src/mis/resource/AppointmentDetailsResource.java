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

import mis.dao.AppointmentDetailsDao;
import mis.dao.PatientDao;
import mis.model.AppointmentDetail;
import mis.model.Patient;

@Path("/appointments")
public class AppointmentDetailsResource {

	UriInfo uriInfo;
	Request request;
	String id;
	
	@GET
	@Path("/{tenantid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<AppointmentDetail> getAppointments(@PathParam("tenantid") int tenantid) {
		List<AppointmentDetail> appointmentObjs = new ArrayList<AppointmentDetail>();
		appointmentObjs.addAll(AppointmentDetailsDao.instance.getAllAppointmentDetails(tenantid).values());
		return appointmentObjs;
	}
	
	@GET
	@Path("/{tenantid}/{appointment_id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AppointmentDetail getAppointment(@PathParam("tenantid") int tenantid, @PathParam("appointment_id") int appointment_id)
	{

		return AppointmentDetailsDao.instance.getAppointmentById(appointment_id);
	}
	
	@GET
	@Path("/{tenantid}/{hospital_staff_id}/appointment")

	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<AppointmentDetail> getAppointmentByStaff(@PathParam("tenantid") int tenantid, @PathParam("hospital_staff_id") int hospital_staff_id)
	{
		List<AppointmentDetail> appointmentObjs = new ArrayList<AppointmentDetail>();
		appointmentObjs.addAll(AppointmentDetailsDao.instance.getAllAppointmentByStaff(tenantid, hospital_staff_id).values());
		return appointmentObjs;
		//return AppointmentDetailsDao.instance.getAppointmentById(appointment_id);
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putPatient(AppointmentDetail appointment) {
		// return Response.status(201).entity(result).build();
		//System.out.println(patient.getUsername());
		return putAndGetResponse(appointment);
	}

	private Response putAndGetResponse(AppointmentDetail appointment) {
		Response res;
		// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
		// res = Response.noContent().build();
		// } else {
		// res = Response.created(uriInfo.getAbsolutePath()).build();
		// }
		System.out.println("here");
		if (AppointmentDetailsDao.instance.putAppointmentDetails(appointment) != 0) {
			String result = "User updated";
			return Response.status(201).entity(result).build();
		} else {
			return Response.noContent().build();
		}
	}
	
}
