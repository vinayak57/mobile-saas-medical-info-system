package mis.model;

import java.io.Serializable;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

@Entity
//@Embedded
public class XRayRecords implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;
	
	@Indexed(value=IndexDirection.ASC, name="recordname1", background= true) 
	private String name;
	
	private byte[] image;
	
	private String recordId;
	
	private String labName;
	
	private String labLocation;
	
	private String lab_staff;
	
	private int hospital_staff_id;
	private String staff_fname;
	private String staff_lname;
	private String details;
	private String speciality;
	private String hospital_name;
	private String location;
	
	private String notes;
	
	
	@Embedded
	private RecordAttribute attr;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public XRayRecords(){
		super();
	}
	
	public XRayRecords(ObjectId id, String name, byte[] image,
			RecordAttribute attr) {
		super();
		this.name = name;
		this.image = image;
		this.attr = attr;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public RecordAttribute getAttr() {
		return attr;
	}

	public void setAttr(RecordAttribute attr) {
		this.attr = attr;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getLabLocation() {
		return labLocation;
	}

	public void setLabLocation(String labLocation) {
		this.labLocation = labLocation;
	}

	public String getLab_staff() {
		return lab_staff;
	}

	public void setLab_staff(String lab_staff) {
		this.lab_staff = lab_staff;
	}

	public int getHospital_staff_id() {
		return hospital_staff_id;
	}

	public void setHospital_staff_id(int hospital_staff_id) {
		this.hospital_staff_id = hospital_staff_id;
	}

	public String getStaff_fname() {
		return staff_fname;
	}

	public void setStaff_fname(String staff_fname) {
		this.staff_fname = staff_fname;
	}

	public String getStaff_lname() {
		return staff_lname;
	}

	public void setStaff_lname(String staff_lname) {
		this.staff_lname = staff_lname;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
