package mis.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VisitType {

	private int visit_type_id;
	private String visit_type;
	private String description;
	private int tenantid;
	
	public int getTenantid() {
		return tenantid;
	}
	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}
	public int getVisit_type_id() {
		return visit_type_id;
	}
	public void setVisit_type_id(int visit_type_id) {
		this.visit_type_id = visit_type_id;
	}
	public String getVisit_type() {
		return visit_type;
	}
	public void setVisit_type(String visit_type) {
		this.visit_type = visit_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
