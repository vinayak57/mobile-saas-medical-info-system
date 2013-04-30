package mis.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class AppointmentDetail implements Serializable {

	private int appointment_id;
	private int visit_type_id;
	private int hospital_staff_id;
	
	//@XmlJavaTypeAdapter(DateAdapter.class)
	private Date appointment_date;
	//private String appointment_time;
	private int location_id;
	private int tenantid;
	private int patient_id;
//	private int prescription_id;
	private String status;
	
	private String hospital_name;
	private String staff_name;
	private String visit_type;
	private String fname;
	private String lname;
	
	
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public int getVisit_type_id() {
		return visit_type_id;
	}
	public void setVisit_type_id(int visit_type_id) {
		this.visit_type_id = visit_type_id;
	}
	public int getHospital_staff_id() {
		return hospital_staff_id;
	}
	public void setHospital_staff_id(int hospital_staff_id) {
		this.hospital_staff_id = hospital_staff_id;
	}
	
	//@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAppointment_date() {
		return appointment_date;
	}
	

//	public String getAppointment_time() {
//		return appointment_time;
//	}
//	public void setAppointment_time(String appointment_time) {
//		this.appointment_time = appointment_time;
//	}
	//@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setAppointment_date(Date appointment_date) {
		this.appointment_date = appointment_date;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getTenantid() {
		return tenantid;
	}
	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
//	public int getPrescription_id() {
//		return prescription_id;
//	}
//	public void setPrescription_id(int prescription_id) {
//		this.prescription_id = prescription_id;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHospital_name() {
		return hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getVisit_type() {
		return visit_type;
	}
	public void setVisit_type(String visit_type) {
		this.visit_type = visit_type;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}

	
}
