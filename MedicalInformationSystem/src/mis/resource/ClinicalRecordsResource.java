package mis.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mis.dao.AppointmentDetailsDao;
import mis.dao.ClinicalRecordsDao;
import mis.model.AppointmentDetail;
import mis.model.ClinicalRecords;

@Path("/clinicalrecords")
public class ClinicalRecordsResource {

	@GET
	@Path("/patient/{patientid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<ClinicalRecords> getClinicalRecordsByPatient(@PathParam("patientid") int patientid) {
		List<ClinicalRecords> objs = new ArrayList<ClinicalRecords>();
		objs.addAll(ClinicalRecordsDao.instance.getAllClinicalRecordsByPatient(patientid));
		return objs;
	}
	
	@GET
	@Path("/hospitalstaff/{hospital_staff_id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ClinicalRecords> getClinicalRecordsByStaff(@PathParam("hospital_staff_id") int hospital_staff_id) {
		List<ClinicalRecords> objs = new ArrayList<ClinicalRecords>();
		objs.addAll(ClinicalRecordsDao.instance.getAllClinicalRecordsByStaff(hospital_staff_id));
		return objs;
	}
	
	@GET
	@Path("/xray/searchbyarea/{diagnostic_area}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ClinicalRecords> getClinicalRecordsByDiagnosticArea(@PathParam("diagnostic_area") String diagnostic_area) {
		List<ClinicalRecords> objs = new ArrayList<ClinicalRecords>();
		objs.addAll(ClinicalRecordsDao.instance.getAllXRayRecordsByDiagnosticArea(diagnostic_area.replace("%20", " ")));
		return objs;
	}
	
	@GET
	@Path("/xray/searchbyseverity/{severity}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ClinicalRecords> getClinicalRecordsBySeverity(@PathParam("severity") String severity) {
		List<ClinicalRecords> objs = new ArrayList<ClinicalRecords>();
		objs.addAll(ClinicalRecordsDao.instance.getAllXRayRecordsBySeverity(severity.replace("%20", " ")));
		return objs;
	}
	
	@GET
	@Path("/xray/searchbyissue/{issue}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ClinicalRecords> getClinicalRecordsByIssue(@PathParam("issue") String issue) {
		
		List<ClinicalRecords> objs = new ArrayList<ClinicalRecords>();
		objs.addAll(ClinicalRecordsDao.instance.getAllXRayRecordsByIssue(issue.replace("%20", " ")));
		return objs;
	}
	
	@GET
	@Path("/xray/searchbydatecreated/{startdate}/{enddate}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<ClinicalRecords> getClinicalRecordsByStartDate(@PathParam("startdate") Date start, @PathParam("enddate") Date end) {
		List<ClinicalRecords> objs = new ArrayList<ClinicalRecords>();
		objs.addAll(ClinicalRecordsDao.instance.getAllXRayRecordsByDateCreatedRange(start, end));
		return objs;
	}
	
	
	
}
