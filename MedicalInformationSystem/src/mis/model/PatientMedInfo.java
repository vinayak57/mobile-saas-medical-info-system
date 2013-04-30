package mis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatientMedInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int patient_med_info_id;
	private int height;
	private int weight;
	private String bloodgroup;
	private String allergies;
	private String precautions;
	private String side_effects;
	private int patient_id;
	private Date dob;
	private String gender;
	private int age;
	private String fname;
	private String lname;
	private int tenant_id;
	
	
	private List<SurgicalHistory> surgicalHistory = new ArrayList<SurgicalHistory>();
	
	private List<SocialHistory> socialHistory = new ArrayList<SocialHistory>();
	
	private List<FamilyHistory> familyHistory = new ArrayList<FamilyHistory>();
	

	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public List<SurgicalHistory> getSurgicalHistory() {
		return surgicalHistory;
	}

	public void setSurgicalHistory(List<SurgicalHistory> surgicalHistory) {
		this.surgicalHistory = surgicalHistory;
	}

	public List<SocialHistory> getSocialHistory() {
		return socialHistory;
	}

	public void setSocialHistory(List<SocialHistory> socialHistory) {
		this.socialHistory = socialHistory;
	}

	public List<FamilyHistory> getFamilyHistory() {
		return familyHistory;
	}

	public void setFamilyHistory(List<FamilyHistory> familyHistory) {
		this.familyHistory = familyHistory;
	}

	public int getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(int tenant_id) {
		this.tenant_id = tenant_id;
	}

}
