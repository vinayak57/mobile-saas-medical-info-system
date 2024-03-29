package mis.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
	//@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dob;
	private String addr1;
	private String addr2;
	private String city;
	private String state;
	private String country;
	private int zipcode;
	private int userid;
	private int location_id;
	
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
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
	
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	@Override
	public String toString() {
		return "Patient [id =" + patientId + "fname=" + fname + ",lname=" + lname + ", email=" + email + "gender=" + gender + ",phone=" + phone + ", userid=" + userid +"]";
	}
	
//	public class PatientComparator implements Comparator<Patient> {
//
//		public int compare(Patient o1, Patient o2) {
//			 String s1=o1.getLname() + o1.getFname();
//	          String s2=o2.getLname() + o2.getFname();
//
//	          return s1.compareTo(s2);
//		}
//	}
}
