package mis.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObjectBuilder;
import mis.model.ClinicalRecords;
import mis.model.ImagingFiles;
import mis.model.MRIScanRecords;
import mis.model.RecordAttribute;
import mis.model.XRayRecords;
import mis.util.MongoDBHelper;

public enum ClinicalRecordsDao {

	instance;
	MongoDBHelper helper = new MongoDBHelper();
	
	private ClinicalRecordsDao() {
	}
	
	public byte[] LoadImage(String filePath){
		File file = new File(filePath);
		int size = (int) file.length();
		byte[] buffer = new byte[size];
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			in.read(buffer);
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buffer;
	}
	
	public XRayRecords loadXrayFile(){
		byte[] imageBytes = LoadImage("C:/Users/Mandar/Desktop/295B Master Project/2005/Train01/3017.png");
		
		XRayRecords xray = new XRayRecords();
		xray.setName("Xray1");
		xray.setImage(imageBytes);

		RecordAttribute attr = new RecordAttribute("leg bone", "broken",
				"high", "2012-04-04T20:27:05", null);

		xray.setAttr(attr);
		Datastore ds = helper.getConnection();
		ds.save(xray);
		
		return xray;
	}
	
	public MRIScanRecords loadMRIScanFile(){
		byte[] imageBytes = LoadImage("C:/Users/Mandar/Desktop/295B Master Project/2005/Train01/3019.png");
		
		MRIScanRecords mri = new MRIScanRecords();
		mri.setName("MRIscan1");
		mri.setImage(imageBytes);

		RecordAttribute attr = new RecordAttribute("leg Bone", "fracture",
				"High", "2013-02-01T20:27:05", null);

		mri.setAttr(attr);
		Datastore ds = helper.getConnection();
		ds.save(mri);
		
		return mri;
	}
	
	public ClinicalRecords insertClinicalRecords()
	{
		
		//ImagingFiles file = ImagingFilesDao.instance.insertImagingFile();
		XRayRecords xray = loadXrayFile();
		MRIScanRecords mri = loadMRIScanFile();
		
		
		
		ClinicalRecords newRecord = new ClinicalRecords();
		newRecord.setDescription("patient1 desc");
		newRecord.setPatient_id(4);
		newRecord.setHospital_staff_id(1);
		
		newRecord.getXray().add(xray);
		newRecord.getMriscan().add(mri);
		

		Datastore ds = helper.getConnection();
		ds.save(newRecord);
		
		return newRecord;
	}
	
	public List<ClinicalRecords> getAllClinicalRecordsByPatient(int patient_id)
	{
		Datastore ds = helper.getConnection();
		return (List<ClinicalRecords>) ds.find(ClinicalRecords.class).filter("patient_id = ", patient_id).asList();
	}
	
	
	public List<ClinicalRecords> getAllClinicalRecordsByStaff(int hospital_staff_id)
	{
		Datastore ds = helper.getConnection();
		return (List<ClinicalRecords>) ds.find(ClinicalRecords.class).filter("hospital_staff_id = ", hospital_staff_id).asList();
	}
	
	public List<ClinicalRecords> getAllClinicalRecordsByDiagnosticArea(String diagnostic_area)
	{
		//List<ClinicalRecords> recordList = new ArrayList<ClinicalRecords>();
		
		Datastore ds = helper.getConnection();
		Query q = ds.createQuery(XRayRecords.class).filter("attr.diagnostic_area =", "leg bone");
		//XRayRecords filterXray = (XRayRecords) ds.find(XRayRecords.class).filter("attr.diagnostic_area =","fracture").get();

		//XRayRecords filterXray = new XRayRecords();
		XRayRecords filterXray = (XRayRecords) q.get();
		
		//filterXray.getName();
		System.out.println(filterXray.getName());
		
		//Query<ClinicalRecords> query =  ds.createQuery(ClinicalRecords.class).filter("xray elem",BasicDBObjectBuilder.start())
		
		//ds.find
		return (List<ClinicalRecords>) ds.find(ClinicalRecords.class).field("xray").hasThisElement(filterXray).asList();
		
		
	}
	
	public static void main(String args[])
	{
		//ClinicalRecordsDao.instance.insertClinicalRecords();
		
		//List<ClinicalRecords> recordList = ClinicalRecordsDao.instance.getAllClinicalRecordsByPatient(4);
		
		List<ClinicalRecords> recordList = ClinicalRecordsDao.instance.getAllClinicalRecordsByDiagnosticArea("leg Bone");
		System.out.println(recordList.size());
		for(ClinicalRecords obj : recordList )
		{
			System.out.println(obj.getDescription() + obj.getPatient_id() + obj.getHospital_staff_id());
			System.out.println(obj.getXray().size());
			System.out.println(obj.getMriscan().size());
			
		}
		
	}
}
