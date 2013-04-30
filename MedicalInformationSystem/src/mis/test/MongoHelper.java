package mis.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import mis.dao.ClinicalRecordsDao;
import mis.dao.HospitalDao;
import mis.dao.HospitalStaffDao;
import mis.dao.PatientDao;
import mis.model.ClinicalRecords;
import mis.model.Hospital;
import mis.model.HospitalStaff;
import mis.model.MRIScanRecords;
import mis.model.Patient;
import mis.model.RecordAttribute;
import mis.model.XRayRecords;
import mis.util.MongoDBHelper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.google.code.morphia.query.UpdateResults;

public class MongoHelper {

	static HashMap<XRayRecords,Key<XRayRecords>> objectidList = new HashMap<XRayRecords,Key<XRayRecords>>();
	
	static MongoDBHelper helper = new MongoDBHelper();
	
	static HashMap<String, String> notesMap = new HashMap<String, String>();
	
	static HashMap<String, String> resultsMap = new HashMap<String, String>();
	
	public static void setNotesMap()
	{
		notesMap.put("FrontFace", "A front face X-ray is a safe and painless test that uses a small amount of radiation to take a picture of a person's chest. During the examination, an X-ray machine sends a beam of radiation through the frontface, and an image is recorded on special film or a computer.");
		notesMap.put("Hand", "A front face X-ray is a safe and painless test that uses a small amount of radiation to take a picture of a person's Hand. During the examination, an X-ray machine sends a beam of radiation through the Hand, and an image is recorded on special film or a computer.");
		notesMap.put("LowerSpinalCord", "A LowerSpinalCord X-ray is a safe and painless test that uses a small amount of radiation to take a picture of a person's LowerSpinalCord. During the examination, an X-ray machine sends a beam of radiation through the LowerSpinalCord, and an image is recorded on special film or a computer.");
		notesMap.put("Palm", "A Palm X-ray is a safe and painless test that uses a small amount of radiation to take a picture of a person's Palm. During the examination, an X-ray machine sends a beam of radiation through the Palm, and an image is recorded on special film or a computer.");
		notesMap.put("SpinalCord", "A SpinalCord X-ray is a safe and painless test that uses a small amount of radiation to take a picture of a person's SpinalCord. During the examination, an X-ray machine sends a beam of radiation through the SpinalCord, and an image is recorded on special film or a computer.");
	}
	
	public static void setResultsMap()
	{
		resultsMap.put("FrontFace", "Vertebral body height is preserved. Posterior arches are intact. Bone density is within the normal range. No aggressive osteolytic or osteoblastic changes. Craniovertebral junction is normal. ADI is within the normal range for this patient's age. Disk height is maintained at the visualized levels. Uncovertebral joint spacing is preserved. No appreciable facet arthrosis. Tracheal air shadow is midline. Lung apices are clear.");
		resultsMap.put("Hand", "Vertebral body height is preserved. Posterior arches are intact. Bone density is within the normal range. No aggressive osteolytic or osteoblastic changes. Craniovertebral junction is normal. ADI is within the normal range for this patient's age. Disk height is maintained at the visualized levels. Uncovertebral joint spacing is preserved. No appreciable facet arthrosis. Tracheal air shadow is midline. Lung apices are clear.");
		resultsMap.put("LowerSpinalCord", "Vertebral body height is preserved. Posterior arches are intact. Bone density is within the normal range. No aggressive osteolytic or osteoblastic changes. Craniovertebral junction is normal. ADI is within the normal range for this patient's age. Disk height is maintained at the visualized levels. Uncovertebral joint spacing is preserved. No appreciable facet arthrosis. Tracheal air shadow is midline. Lung apices are clear.");
		resultsMap.put("Palm", "Vertebral body height is preserved. Posterior arches are intact. Bone density is within the normal range. No aggressive osteolytic or osteoblastic changes. Craniovertebral junction is normal. ADI is within the normal range for this patient's age. Disk height is maintained at the visualized levels. Uncovertebral joint spacing is preserved. No appreciable facet arthrosis. Tracheal air shadow is midline. Lung apices are clear.");
		resultsMap.put("SpinalCord", "Vertebral body height is preserved. Posterior arches are intact. Bone density is within the normal range. No aggressive osteolytic or osteoblastic changes. Craniovertebral junction is normal. ADI is within the normal range for this patient's age. Disk height is maintained at the visualized levels. Uncovertebral joint spacing is preserved. No appreciable facet arthrosis. Tracheal air shadow is midline. Lung apices are clear.");
	}
	
	public enum LabStaff{
		JonSmith, AmandaKeets, DarrylJohnson, AdamVaughan, Jack, PhilJacques;
		
		private static final List<LabStaff> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
		  private static final int SIZE = VALUES.size();
		  private static final Random RANDOM = new Random();

		  public static LabStaff randomIssue()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }
	}
	
	public enum Issue{
		BROKEN, FRACTURE, PAIN, HAIRLINE_FRACTURE;
		
		private static final List<Issue> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
		  private static final int SIZE = VALUES.size();
		  private static final Random RANDOM = new Random();

		  public static Issue randomIssue()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }
	}
	
	public enum LabName{
		HealthCareClinicalLaboratory,WeberRanch, StJosephsMedicalCenter, Linacia , BrooksidePatientServiceCenter, 
		FountainViewPatientServiceCenter;
		private static final List<LabName> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
		  private static final int SIZE = VALUES.size();
		  private static final Random RANDOM = new Random();

		  public static LabName randomLabName()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }
	}
	
	public enum LabLocation{
		StocktonCA95204,MantecaCA95336, MercedCA95340 , ModestoCA95351, TracyCA95376;
		private static final List<LabLocation> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
		  private static final int SIZE = VALUES.size();
		  private static final Random RANDOM = new Random();

		  public static LabLocation randomLabLocation()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }
	}
	
	public enum Severity{
		HIGH, LOW, MEDIUM;
		
		private static final List<Severity> VALUES =
		    Collections.unmodifiableList(Arrays.asList(values()));
		  private static final int SIZE = VALUES.size();
		  private static final Random RANDOM = new Random();

		  public static Severity randomSeverity()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		  }
	}
	
	public static String getRecordId() {
	    Random r = new Random( System.currentTimeMillis() );
	    return String.valueOf((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}
	
	public static String getNotes(String area)
	{
		
		return "";
	}
	
	public static int getPatientId()
	{
		int Low = 17;
		int High = 1001;
		Random r = new Random();
		
		int R = r.nextInt(High-Low) + Low;
		return R;
	}
	
	public static int getHospitalId()
	{
		int Low = 1;
		int High = 2;
		Random r = new Random();
		
		int R = r.nextInt(High-Low) + Low;
		return R;
	}
	
	public static String getClinicalRecordDesc(String str)
	{
		return str + " description";
	}
	
    public static Date randomDate() {
        Calendar date = Calendar.getInstance(); // the current date/time
        int Low = 1;
		int High = 730;
		Random r = new Random();
		
		int R = r.nextInt(High-Low) + Low;

        
        int numberOfDaysToSub = (-1)*R;  // i.e. up to approximately ten years
        date.add(Calendar.DAY_OF_YEAR, numberOfDaysToSub); // add to the current date
       
        return date.getTime();
      }
	
	public static byte[] LoadImage(String filePath){
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
	
	public static XRayRecords loadXrayFile(String path, String xrayName, int hospital_staff_id){
		byte[] imageBytes = LoadImage(path);
		//System.out.println(xrayName);
		XRayRecords xray = new XRayRecords();
		String name =xrayName +"Xray";
		xray.setName(name);
		xray.setImage(imageBytes);

		xray.setRecordId(getRecordId());
		xray.setLabName(LabName.randomLabName().toString());
		xray.setLocation(LabLocation.randomLabLocation().toString());
		xray.setLab_staff(LabStaff.randomIssue().toString());
		xray.setNotes(notesMap.get(xrayName));
		xray.setResults(resultsMap.get(xrayName));
		
		List<HospitalStaff> staffList = HospitalStaffDao.instance.getStaffByHospitalId(6, hospital_staff_id);
		
		xray.setHospital_staff_id(hospital_staff_id);
		xray.setStaff_fname(staffList.get(0).getFname());
		xray.setStaff_lname(staffList.get(0).getLname());
		xray.setDetails(staffList.get(0).getDetails());
		xray.setSpeciality(staffList.get(0).getSpeciality());
		
		int hospital = staffList.get(0).getHospital_id();
		Hospital obj = HospitalDao.instance.getHospitalById(hospital);
		
		xray.setHospital_name(obj.getHospital_name());
		xray.setLocation(obj.getLocation());
		
		Date date = randomDate();
		
		RecordAttribute attr = new RecordAttribute(xrayName,Issue.randomIssue().toString(),
				Severity.randomSeverity().toString(),date , null);

		xray.setAttr(attr);
		Datastore ds = helper.getConnection();
		Key<XRayRecords> savedRecords = ds.save(xray);
		//System.out.println(xray.toString());
		objectidList.put(xray,savedRecords);
		
		return xray;
	}
	
	public static MRIScanRecords loadMRIScanFile(String path, String mriName){
		byte[] imageBytes = LoadImage(path);
		//System.out.println(mriName);
		
		String name = mriName + "Mri";
		MRIScanRecords mri = new MRIScanRecords();
		mri.setName(name);
		mri.setImage(imageBytes);

		Date date = randomDate();
		
		RecordAttribute attr = new RecordAttribute(mriName,Issue.randomIssue().toString(),
				Severity.randomSeverity().toString(),date , null);

		mri.setAttr(attr);
		Datastore ds = helper.getConnection();
		ds.save(mri);
		
		return mri;
	}
	
	public static ClinicalRecords insertClinicalRecords(String path, String name)
	{
		int patient_id = getPatientId();
		int hospital_staff_id = getHospitalId();
		
		setNotesMap();
		setResultsMap();
		
		//ImagingFiles file = ImagingFilesDao.instance.insertImagingFile();
		XRayRecords xray = loadXrayFile(path,name,hospital_staff_id);
		MRIScanRecords mri;// = loadMRIScanFile(path,name);

		
		
		String description = getClinicalRecordDesc(String.valueOf(patient_id));
		
		List<ClinicalRecords> recordList = ClinicalRecordsDao.instance.getAllClinicalRecordsByPatient(patient_id);
		
		if(recordList == null || recordList.size()==0)
		{
			ClinicalRecords newRecord = new ClinicalRecords();
			newRecord.setDescription(description);
			newRecord.setPatient_id(patient_id);
			newRecord.setHospital_staff_id(hospital_staff_id);
			
			Patient obj = PatientDao.instance.getPatientByPatientId(patient_id);
			
			newRecord.setFname(obj.getFname());
			newRecord.setLname(obj.getLname());
			newRecord.setEmail(obj.getEmail());
			newRecord.setDob(obj.getDob());
			newRecord.setGender(obj.getGender());
			
			newRecord.getXray().add(xray);
			//newRecord.getMriscan().add(mri);
			
			//System.out.println(newRecord.toString());

			Datastore ds = helper.getConnection();
			Key<ClinicalRecords> savedRecords = ds.save(newRecord);
			
			return newRecord;
		}
		else
		{
			ClinicalRecords obj = recordList.get(0);
			obj.getXray().add(xray);
			//obj.getMriscan().add(mri);
			Datastore ds = helper.getConnection();
			
			//UpdateResults<ClinicalRecords> res = ds.update(recordList.get(0), ds.createUpdateOperations(ClinicalRecords.class).add("xray", objectidList.get(xray))); 
			
			System.out.println("in update : id = " + recordList.get(0).getId() + recordList.get(0).getPatient_id());
			Query<ClinicalRecords> q = ds.createQuery(ClinicalRecords.class).field("patient_id").equal(recordList.get(0).getPatient_id());
			UpdateOperations<ClinicalRecords> ops = ds.createUpdateOperations(ClinicalRecords.class).add("xray", objectidList.get(xray));
			ds.update(q, ops);//adds the comment object to the comment array.
			
			return obj;
		}


	}
	
	
	public static void main(String args[])
	{
		File[] files = new File("C:/Users/Mandar/Desktop/295B Master Project/2005/Test/").listFiles();
	    showFiles(files);
//		for (ClinicalRecords obj: ClinicalRecordsDao.instance.getAllClinicalRecordsByPatient(4))
//		{
//			System.out.println(obj.getId().toString());
//		}
			
	}
	
	public static void showFiles(File[] files){
		String folderName="";
		
	    for (File file : files) {
	        if (file.isDirectory()) {
	        	folderName = file.getName();
	            //System.out.println("Directory: " + file.getName());
	            showFiles(file.listFiles()); // Calls same method again.
	        } else {
	        	folderName = file.getParent().substring(file.getParent().lastIndexOf('\\') +1);
	        	insertClinicalRecords(file.getAbsolutePath(),folderName);
	            //System.out.println("File: " + file.getAbsolutePath());
	        	 //System.out.println(folderName);
	        }
	       
	    }
	}
	
}
