package mis.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private static int count;
	
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

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = formatter.parse("2012-04-04 20:27:05");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RecordAttribute attr = new RecordAttribute("leg bone", "broken",
				"high",date , null);

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

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = formatter.parse("2013-02-01 20:27:05");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RecordAttribute attr = new RecordAttribute("leg Bone", "fracture",
				"High", date, null);

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
	
	public List<ClinicalRecords> getAllXRayRecordsByDiagnosticArea(String diagnostic_area)
	{
		Datastore ds = helper.getConnection();
		Query<XRayRecords> q = ds.createQuery(XRayRecords.class).filter("attr.diagnostic_area =", diagnostic_area);
		XRayRecords filterXray = (XRayRecords) q.get();
		if(filterXray == null) return new ArrayList<ClinicalRecords>();
		//Query<ClinicalRecords> query =  ds.createQuery(ClinicalRecords.class).filter("xray elem",BasicDBObjectBuilder.start())
		
		return (List<ClinicalRecords>) ds.find(ClinicalRecords.class).field("xray").hasThisElement(filterXray).asList();
	}
	
	public List<ClinicalRecords> getAllXRayRecordsByIssue(String issue)
	{
		Datastore ds = helper.getConnection();
		Query<XRayRecords> q = ds.createQuery(XRayRecords.class).filter("attr.issue =", issue);
		XRayRecords filterXray = (XRayRecords) q.get();
		if(filterXray == null) return new ArrayList<ClinicalRecords>();
		return (List<ClinicalRecords>) ds.find(ClinicalRecords.class).field("xray").hasThisElement(filterXray).asList();
	}
	
	public List<ClinicalRecords> getAllXRayRecordsBySeverity(String severity)
	{
		Datastore ds = helper.getConnection();
		Query<XRayRecords> q = ds.createQuery(XRayRecords.class).filter("attr.severity =", severity);
		XRayRecords filterXray = (XRayRecords) q.get();
		if(filterXray == null) return new ArrayList<ClinicalRecords>();
		return (List<ClinicalRecords>) ds.find(ClinicalRecords.class).field("xray").hasThisElement(filterXray).asList();
	}
	
	public List<ClinicalRecords> getAllXRayRecordsByDateCreatedRange(Date start, Date end)
	{
		Datastore ds = helper.getConnection();
		Query<XRayRecords> q = ds.createQuery(XRayRecords.class).filter("attr.dateCreated >=", start).filter("attr.dateCreated <=", end);
		XRayRecords filterXray = (XRayRecords) q.get();
		if(filterXray == null) return new ArrayList<ClinicalRecords>();
		return (List<ClinicalRecords>) ds.find(ClinicalRecords.class).field("xray").hasThisElement(filterXray).asList();
	}
	
	public static void main(String args[])
	{
		//ClinicalRecordsDao.instance.insertClinicalRecords();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
		Date end = null;
		try {
			start = formatter.parse("2012-02-01 20:27:05");
			end = formatter.parse("2012-03-01 20:27:05");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 1 ; i<900;i++)
		{
		List<ClinicalRecords> recordList = ClinicalRecordsDao.instance.getAllClinicalRecordsByPatient(i);
		//List<ClinicalRecords> recordList = ClinicalRecordsDao.instance.getAllXRayRecordsByDiagnosticArea("FrontNeck");
		
		//List<ClinicalRecords> recordList = ClinicalRecordsDao.instance.getAllXRayRecordsByDateCreatedRange(start, end);
		//System.out.println(recordList.size());
		for(ClinicalRecords obj : recordList )
		{
			//System.out.println(obj.getDescription() + obj.getPatient_id() + obj.getHospital_staff_id());
			//System.out.println(obj.getXray().get(0).getAttr().getDateCreated().toString());
			System.out.println(obj.getMriscan().size());
			
		}
		}
		
	}
}
