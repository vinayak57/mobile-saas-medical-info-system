package mis.model;

import java.io.Serializable;

public class FamilyHistory implements Serializable {

	private int family_history_id;
	private String member_name;
	private String relationship;
	private String disease;
	private int patient_id;
	private int tenant_id;
	
	
	public int getFamily_history_id() {
		return family_history_id;
	}
	public void setFamily_history_id(int family_history_id) {
		this.family_history_id = family_history_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getTenant_id() {
		return tenant_id;
	}
	public void setTenant_id(int tenant_id) {
		this.tenant_id = tenant_id;
	}
		
}
