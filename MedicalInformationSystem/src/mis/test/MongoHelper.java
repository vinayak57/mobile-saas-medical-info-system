package mis.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import mis.model.ClinicalRecords;
import mis.model.MRIScanRecords;
import mis.model.RecordAttribute;
import mis.model.XRayRecords;
import mis.util.MongoDBHelper;

import com.google.code.morphia.Datastore;

public class MongoHelper {

	static MongoDBHelper helper = new MongoDBHelper();
	
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
	
	
	public static int getPatientId()
	{
		int Low = 1;
		int High = 31;
		Random r = new Random();
		
		int R = r.nextInt(High-Low) + Low;
		return R;
	}
	
	public static int getHospitalId()
	{
		int Low = 1;
		int High = 31;
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
	
	public static XRayRecords loadXrayFile(String path, String xrayName){
		byte[] imageBytes = LoadImage(path);
		
		XRayRecords xray = new XRayRecords();
		xray.setName(xrayName + " Xray");
		xray.setImage(imageBytes);

		Date date = randomDate();
		
		RecordAttribute attr = new RecordAttribute(xrayName,Issue.randomIssue().toString(),
				Severity.randomSeverity().toString(),date , null);

		xray.setAttr(attr);
		Datastore ds = helper.getConnection();
		ds.save(xray);
		
		return xray;
	}
	
	public static MRIScanRecords loadMRIScanFile(String path, String mriName){
		byte[] imageBytes = LoadImage(path);
		
		MRIScanRecords mri = new MRIScanRecords();
		mri.setName(mriName + " description");
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
		
		//ImagingFiles file = ImagingFilesDao.instance.insertImagingFile();
		XRayRecords xray = loadXrayFile(path,name);
		MRIScanRecords mri = loadMRIScanFile(path,name);
		
		int patient_id = getPatientId();
		int hospital_staff_id = getHospitalId();
		
		String description = getClinicalRecordDesc(String.valueOf(patient_id));
		
		ClinicalRecords newRecord = new ClinicalRecords();
		newRecord.setDescription(description);
		newRecord.setPatient_id(patient_id);
		newRecord.setHospital_staff_id(hospital_staff_id);
		
		newRecord.getXray().add(xray);
		newRecord.getMriscan().add(mri);
		

		Datastore ds = helper.getConnection();
		ds.save(newRecord);
		
		return newRecord;
	}
	
	
	public static void main(String args[])
	{
		File[] files = new File("C:/Users/Mandar/Desktop/295B Master Project/2005/Test/").listFiles();
	    showFiles(files);
	}
	
	public static void showFiles(File[] files) {
		String folderName="";
		
	    for (File file : files) {
	        if (file.isDirectory()) {
	        	folderName = file.getName();
	            System.out.println("Directory: " + file.getName());
	            showFiles(file.listFiles()); // Calls same method again.
	        } else {
	        	insertClinicalRecords(file.getAbsolutePath(),folderName);
	            System.out.println("File: " + file.getAbsolutePath());
	        }
	    }
	}
	
}
