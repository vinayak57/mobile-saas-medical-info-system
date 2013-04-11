package mis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.*;

import org.bson.types.ObjectId;

@Entity("clinicalrecords")
@XmlRootElement
public class ClinicalRecords  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private ObjectId id;
	
	private String description;
	
	private int patient_id;
	
	private int hospital_staff_id;
	
	@Reference
	private List<XRayRecords> xray = new ArrayList<XRayRecords>();
	
	@Reference
	private List<MRIScanRecords> mriscan = new ArrayList<MRIScanRecords>();

	

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getHospital_staff_id() {
		return hospital_staff_id;
	}

	public void setHospital_staff_id(int hospital_staff_id) {
		this.hospital_staff_id = hospital_staff_id;
	}

	public List<XRayRecords> getXray() {
		return xray;
	}

	public void setXray(List<XRayRecords> xray) {
		this.xray = xray;
	}

	public List<MRIScanRecords> getMriscan() {
		return mriscan;
	}

	public void setMriscan(List<MRIScanRecords> mriscan) {
		this.mriscan = mriscan;
	}
	
	
	
}
