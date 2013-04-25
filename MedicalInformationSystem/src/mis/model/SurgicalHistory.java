package mis.model;

import java.io.Serializable;
import java.util.Date;

public class SurgicalHistory implements Serializable  {

	// surgical history
	private int surgical_history_id;
	private String type_of_surgery;
	private Date date_of_surgery;
	private int hospital_staff_id;
	private int patient_id;
	private int tenant_id;
	
	public int getSurgical_history_id() {
		return surgical_history_id;
	}
	public void setSurgical_history_id(int surgical_history_id) {
		this.surgical_history_id = surgical_history_id;
	}
	public String getType_of_surgery() {
		return type_of_surgery;
	}
	public void setType_of_surgery(String type_of_surgery) {
		this.type_of_surgery = type_of_surgery;
	}
	public Date getDate_of_surgery() {
		return date_of_surgery;
	}
	public void setDate_of_surgery(Date date_of_surgery) {
		this.date_of_surgery = date_of_surgery;
	}
	public int getHospital_staff_id() {
		return hospital_staff_id;
	}
	public void setHospital_staff_id(int hospital_staff_id) {
		this.hospital_staff_id = hospital_staff_id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getTenant_id() {
		return tenant_id;
	}
	public void setTenant_id(int tenant_id) {
		this.tenant_id = tenant_id;
	}
	
}
