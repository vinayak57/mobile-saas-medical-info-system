package mis.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatientMedInfo implements Serializable{

	private int patient_med_info_id;
private int height;
private int weight;
private String bloodgroup;

	private String allergies;
	private String precautions;
	private String side_effects;
	private int patient_id;
	
	public int getPatient_med_info_id() {
		return patient_med_info_id;
	}
	public void setPatient_med_info_id(int patient_med_info_id) {
		this.patient_med_info_id = patient_med_info_id;
	}
	
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getPrecautions() {
		return precautions;
	}
	public void setPrecautions(String precautions) {
		this.precautions = precautions;
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
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	
	
}
