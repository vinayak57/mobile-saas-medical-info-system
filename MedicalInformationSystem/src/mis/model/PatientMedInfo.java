package mis.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatientMedInfo implements Serializable{

	private int patient_med_info_id;
	private int appointment_id;
	private int prescription_id;
	private String allergies;
	private String warning;
	private String side_effects;
	private int patient_id;
	
	public int getPatient_med_info_id() {
		return patient_med_info_id;
	}
	public void setPatient_med_info_id(int patient_med_info_id) {
		this.patient_med_info_id = patient_med_info_id;
	}
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public int getPrescription_id() {
		return prescription_id;
	}
	public void setPrescription_id(int prescription_id) {
		this.prescription_id = prescription_id;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	public String getSide_effects() {
		return side_effects;
	}
	public void setSide_effects(String side_effects) {
		this.side_effects = side_effects;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	
}
