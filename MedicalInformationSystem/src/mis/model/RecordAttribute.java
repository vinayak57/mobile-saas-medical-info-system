package mis.model;

import java.io.Serializable;
import java.util.Date;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

import org.bson.types.ObjectId;

@Embedded
public class RecordAttribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private ObjectId id;
	
	@Indexed(value=IndexDirection.ASC, name="diagnostic_area1", background= true) 
	private String diagnostic_area;
	
	@Indexed(value=IndexDirection.ASC, name="issue_1", background= true) 
	private String issue;
	
	@Indexed(value=IndexDirection.ASC, name="severity_1", background= true) 
	private String severity;
	
	@Indexed(value=IndexDirection.ASC, name="dateCreated_1", background= true) 
	private Date dateCreated;
	
	
	@Indexed(value=IndexDirection.ASC, name="dateModified_1", background= true) 
	private Date dateModified;
	
	public RecordAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecordAttribute(String diagnostic_area, String issue,
			String severity, Date dateCreated, Date dateModified) {
		super();
		this.diagnostic_area = diagnostic_area;
		this.issue = issue;
		this.severity = severity;
		this.dateCreated = dateCreated;
		this.dateModified = dateModified;
	}
	
	public String getDiagnostic_area() {
		return diagnostic_area;
	}

	

	public void setDiagnostic_area(String diagnostic_area) {
		this.diagnostic_area = diagnostic_area;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	
//	public ObjectId getId() {
//		return id;
//	}
//
//	public void setId(ObjectId id) {
//		this.id = id;
//	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
	
}
