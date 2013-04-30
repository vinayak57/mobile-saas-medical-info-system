package mis.util;

import java.net.UnknownHostException;

import mis.model.ClinicalRecords;
import mis.model.ImagingFiles;
import mis.model.MRIScanRecords;
import mis.model.RecordAttribute;
import mis.model.XRayRecords;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;

public class MongoDBHelper {
	
	
	public Datastore getConnection()
	{
		Datastore ds = null;
		try {

			MongoOptions options = new MongoOptions();
			options.connectionsPerHost = 40;
			options.connectTimeout = 15;
			options.socketTimeout = 60000;
			options.threadsAllowedToBlockForConnectionMultiplier = 1500;
			
			Mongo mongo = new Mongo("localhost",options);
			
			Morphia morphia = new Morphia();
			//morphia.map(entityClass);
            morphia.map(ClinicalRecords.class).map(ImagingFiles.class).map(MRIScanRecords.class).map( XRayRecords.class ).map( RecordAttribute.class );

            // Create a data store
            ds = morphia.createDatastore( mongo, "medicalinformationsystem" );
			
//			
			//ds = morphia.createDatastore("medicalinformationsystem");
			ds.ensureIndexes(); //creates indexes from @Index annotations in your entities
			ds.ensureCaps(); //creates capped collections from @Entity
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ds;		
	}
	
}
