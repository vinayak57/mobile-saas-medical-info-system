package mis.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Location {

	private int location_id;
	private String addr1;
	private String addr2;
	private String city;
	private String state;
	private String country;
	private int zipcode;
	private int tenantid;
	
	
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
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
	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}
	public int getTenantid() {
		return tenantid;
	}
	
}
