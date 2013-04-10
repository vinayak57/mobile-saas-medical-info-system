package mis.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.code.morphia.Datastore;

import mis.model.ImagingFiles;
import mis.model.MRIScanRecords;
import mis.model.RecordAttribute;
import mis.model.XRayRecords;
import mis.util.MongoDBHelper;

public enum ImagingFilesDao {

	instance;

	private ImagingFilesDao() {
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

	

	public ImagingFiles insertImagingFile() {
		ImagingFiles file = new ImagingFiles();

		//XRayRecords xray = loadXrayFile();
		//MRIScanRecords mri = loadMRIScanFile();
		
		//file.getXray().add(xray);
		//file.getMriscan().add(mri);
//		
//		MongoDBHelper helper = new MongoDBHelper();
//		Datastore ds = helper.getConnection();
//		ds.save(file);
		
		return file;
	}
	
	
}
