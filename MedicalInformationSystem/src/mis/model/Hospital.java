package mis.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hospital  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int Hospital_id;
	private String Hospital_name;
	//private int location_id;
	private String location;
	
	private int tenantid;
	public int getHospital_id() {
		return Hospital_id;
	}
	public void setHospital_id(int hospital_id) {
		Hospital_id = hospital_id;
	}
	public String getHospital_name() {
		return Hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		Hospital_name = hospital_name;
	}
//	public int getLocation_id() {
//		return location_id;
//	}
//	public void setLocation_id(int location_id) {
//		this.location_id = location_id;
//	}
	public int getTenantid() {
		return tenantid;
	}
	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
