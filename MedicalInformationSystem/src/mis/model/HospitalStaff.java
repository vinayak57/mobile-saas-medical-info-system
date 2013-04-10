package mis.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HospitalStaff  extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hospitalStaffId;
	private String fname;
	private String lname;
	private String details;
	private String speciality;
	private int userId;
	private int hospital_id;
	
	
	public int getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	public int getHospitalStaffId() {
		return hospitalStaffId;
	}
	public void setHospitalStaffId(int hospitalStaffId) {
		this.hospitalStaffId = hospitalStaffId;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUserId() {
		return userId;
	}
	
}
