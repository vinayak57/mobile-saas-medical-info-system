package mis.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.google.code.morphia.Datastore;
import com.mongodb.gridfs.GridFS;

import mis.model.RecordAttribute;
import mis.model.XRayRecords;
import mis.util.MongoDBHelper;

public enum XRayRecordsDao {

	instance;
	
	MongoDBHelper helper = new MongoDBHelper();
	
	 public static byte[] LoadImage(String filePath) throws Exception {
	        File file = new File(filePath);
	        int size = (int)file.length();
	        byte[] buffer = new byte[size];
	        FileInputStream in = new FileInputStream(file);
	        in.read(buffer);
	        in.close();
	        return buffer;
	    }
	 
	 public List<XRayRecords> getAllXRayRecordsByPatient(int patient_id) {

			Datastore ds = helper.getConnection();
			List<XRayRecords> eventsList = null;
			// eventsList = 
			eventsList	= (List<XRayRecords>) ds.find(XRayRecords.class).asList();
			System.out.println(eventsList.size());
			return eventsList;
		}
//	 
//	 public static void storeXRayRecords(XRayRecords xray) {
//			Datastore ds = MongoDBHelper.getConnection();
//			System.out.println("saving xray");
//			ds.save(xray);
//		}
//	 
	 public static void main(String[] args) throws Exception {
//	        List<XRayRecords> list = getXray();
//	        
//	        for(XRayRecords obj: list)
//	        	{
//	        		System.out.println(obj.getName());
//	        		System.out.println(obj.getImage());
//	        		RecordAttribute att= obj.getAttr();
//	        		System.out.println(att.getDiagnostic_area());
//	        	}
//		 
//		 //Load our image
//	        byte[] imageBytes = LoadImage("C:/Users/Mandar/Desktop/295B Master Project/2005/Train01/2983.png");
//	        System.out.println("image loaded");
//	        
//	        XRayRecords xray = new XRayRecords();
//	        xray.setName("Xray");
//	        xray.setImage(imageBytes);
//	        
//	        RecordAttribute attr = new RecordAttribute("Skull Bone", "Fracture", "Medium", "2012-03-03T20:27:05", null);
//	        
//	        xray.setAttr(attr);
//	        
//	        storeXRayRecords(xray);
//	        
//		 
//	        XRayRecords xray1 = new XRayRecords();
//	        xray1.setName("Xray 1");
//	        xray1.setImage(imageBytes);
//	        
//	        RecordAttribute attr1 = new RecordAttribute("Leg Bone", "Fracture", "Medium", "2013-03-03T20:27:05", "");
//	        
//	        xray1.setAttr(attr1);
//	        
//	        storeXRayRecords(xray1);
//	        
//		 
//	        list = getXray();
//	        
//	        for(XRayRecords obj: list)
//	        	{
//	        		System.out.println(obj.getName());
//	        		System.out.println(obj.getImage());
//	        		RecordAttribute att= obj.getAttr();
//	        		System.out.println(att.getDiagnostic_area());
//	        	}
//	        
	 }
	
}
