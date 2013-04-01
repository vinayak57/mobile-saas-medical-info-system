package mis.test;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient extends User implements Serializable{

	private int patientId;
	private String fname;
	private String lname;
	private String email;
	private String gender;
	private int phone;
	//private Date dob;
	
	private int userid;

	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFname() {
		return fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLname() {
		return lname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getPhone() {
		return phone;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getUserid() {
		return userid;
	}
	
	@Override
	public String toString() {
		return "Patient [id =" + patientId + "fname=" + fname + ",lname=" + lname + ", email=" + email + "gender=" + gender + ",phone=" + phone + ", userid=" + userid +"]";
	}
}
