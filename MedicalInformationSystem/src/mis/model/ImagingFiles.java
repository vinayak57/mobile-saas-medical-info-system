package mis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;


@Embedded
public class ImagingFiles implements Serializable{

//	@Id
//	private ObjectId id;
	
	private List<XRayRecords> xray = new ArrayList<XRayRecords>();
	
	private List<MRIScanRecords> mriscan = new ArrayList<MRIScanRecords>();

//	public ObjectId getId() {
//		return id;
//	}
//
//	public void setId(ObjectId id) {
//		this.id = id;
//	}

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
