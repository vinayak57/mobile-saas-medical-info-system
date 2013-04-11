package mis.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmergencyRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int emergency_request_id;
	private int userid;
	private String emergencylocation;
	private String latitude;
	private String longitude;
	private String status;
	private int tenantid;
	
	
	public int getEmergency_request_id() {
		return emergency_request_id;
	}
	public void setEmergency_request_id(int emergency_request_id) {
		this.emergency_request_id = emergency_request_id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getEmergencylocation() {
		return emergencylocation;
	}
	public void setEmergencylocation(String emergencylocation) {
		this.emergencylocation = emergencylocation;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTenantid() {
		return tenantid;
	}
	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}
	
	
	
}
