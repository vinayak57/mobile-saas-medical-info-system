package mis.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Drug {

	private int drugId;
	private String name;
	private String power;
	private int tenantid;
	
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getName() {
		return name;
	}
	public void setName(String drugName) {
		this.name = drugName;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public void setTenantid(int tenantId) {
		this.tenantid = tenantId;
	}
	public int getTenantid() {
		return tenantid;
	}
}
