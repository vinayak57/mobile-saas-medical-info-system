package mis.model;

import java.io.Serializable;

public class SocialHistory implements Serializable {

	private int social_history_id;
	private String isSmoker;
	private String isAlcoholic;
	private String isdrug_consumer;
	private String vaccinations;
	private int no_of_pregnancies;
	private int patient_id;
	private int tenant_id;
	
	
	public int getSocial_history_id() {
		return social_history_id;
	}
	public void setSocial_history_id(int social_history_id) {
		this.social_history_id = social_history_id;
	}

	public String getVaccinations() {
		return vaccinations;
	}
	public void setVaccinations(String vaccinations) {
		this.vaccinations = vaccinations;
	}
	public int getNo_of_pregnancies() {
		return no_of_pregnancies;
	}
	public void setNo_of_pregnancies(int no_of_pregnancies) {
		this.no_of_pregnancies = no_of_pregnancies;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getIsSmoker() {
		return isSmoker;
	}
	public void setIsSmoker(String isSmoker) {
		this.isSmoker = isSmoker;
	}
	public String getIsAlcoholic() {
		return isAlcoholic;
	}
	public void setIsAlcoholic(String isAlcoholic) {
		this.isAlcoholic = isAlcoholic;
	}
	public String getIsdrug_consumer() {
		return isdrug_consumer;
	}
	public void setIsdrug_consumer(String isdrug_consumer) {
		this.isdrug_consumer = isdrug_consumer;
	}
	public int getTenant_id() {
		return tenant_id;
	}
	public void setTenant_id(int tenant_id) {
		this.tenant_id = tenant_id;
	}
	
}
