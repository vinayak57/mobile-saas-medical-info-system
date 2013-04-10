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
public class MRIScanRecords implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;
	
	
	@Indexed(value=IndexDirection.ASC, name="mriscanname", background= true) 
	private String name;
	
	private byte[] image;
	
	@Embedded
	private RecordAttribute attr;

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Object getId() {
		return id;
	}

	public MRIScanRecords(){
		super();
	}
	
	public MRIScanRecords(ObjectId id, String name, byte[] image,
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
}
