package mis.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mis.dao.AppointmentDetailsDao;
import mis.dao.EmergencyRequestDao;
import mis.model.AppointmentDetail;
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
}
