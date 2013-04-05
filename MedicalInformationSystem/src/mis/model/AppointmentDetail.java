package mis.model;

import java.io.Serializable;
import java.util.Date;
import mis.util.CustomDateSerializer;
import mis.util.CustomJsonDateDeserializer;
import mis.util.DateAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

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

	
}
