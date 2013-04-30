package com.example.mobilesaas;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Base64DataException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends Activity {
	Button submit;
	TextView test;
	String result;
	String result1;
	Bitmap bmp;
	ImageView ch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        submit=(Button)findViewById(R.id.bSubmit);
        test=(TextView)findViewById(R.id.tvValue);
        //ch=(ImageView)findViewById(R.id.ivYeda);
        
       
        
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//callRest();
				
				 Calendar now = Calendar.getInstance();
		    	 
			      // SimpleDateFormat sf = new SimpleDateFormat("yyyy/mm/dd");
			     
			     //   cal.add(Calendar.DAY_OF_MONTH, 5);
			     
			      /*  System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-"
			            + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));
				*/
				 
				 now.add(Calendar.DATE, 1);
				test.setText("Date : " + (now.get(Calendar.MONTH) + 1) + "-"
			            + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR)+" "+ "Time : " +"9:00:00");
				 
				/*"Date : " + (now.get(Calendar.MONTH) + 1) + "-"
		            + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR)+" "+ "Time : " +j+":00:00"
				*/
				String product=test.getText().toString();
				
				String[] tokens =product.split("Date : ");
				test.setText("legth "+ tokens.length+"   "+tokens[1]+"   ");
				
				String t1[]=tokens[1].split("Time : ");
				
				test.setText(t1[0].trim() +"T"+ t1[1].trim());
				
				
				/*Toast toast = Toast.makeText(getApplicationContext(),tokens[1],
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
				*/
				
				
				/*File imgFile = new File("/mnt/sdcard/mobilesaas.png");
				if(imgFile.exists()){

					Toast toast = Toast.makeText(getApplicationContext(), "file",
			   				 Toast.LENGTH_LONG);
			   				 toast.show();
			   				 
				    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

				    
				    Toast toast234 = Toast.makeText(getApplicationContext(), "actual path  "+imgFile.getAbsolutePath(),
			   				 Toast.LENGTH_LONG);
			   				 toast234.show();
			   				 
				    ImageView myImage = (ImageView) findViewById(R.id.ivYeda);
				    myImage.setImageBitmap(myBitmap);
*/
				}
			        
				//String abc=test.getText().toString();
				//System.out.println(abc);
				
//			/}
		});
        
    }

    public void callRest(){
    	
    	
    	HttpClient httpclient = new DefaultHttpClient();  
    	String uname="mandar";
        String q="http://10.0.2.2:8080/MedicalInformationSystem/rest/clinicalrecords/patient/4";
        
        HttpGet request = new HttpGet(q);
        request.addHeader("Accept","application/json");
        HttpResponse res;
        try {
        	
        	
        	ResponseHandler<String> handler =new BasicResponseHandler();
        	//result= httpclient.execute(request,handler);
        	res=httpclient.execute(request);
        	HttpEntity entity=res.getEntity();
        	result=EntityUtils.toString(entity);
        	
        	int code=res.getStatusLine().getStatusCode();
        	
        	/*Toast toast = Toast.makeText(getApplicationContext(), "my codee"+code,
	   				 Toast.LENGTH_LONG);
	   				 toast.show();
	   				 */
        	//System.out.println(result);
        	
        	//result=result+"  my codeee"+code;
        	
        	
        	
        	
        	
        	//JSONObject json = new JSONObject(result);
			//String pass=(String)json.get("allergies");
			
        	JSONArray start_object=new JSONArray(result);
        	int count = start_object.length();
        	  Toast toast1223 = Toast.makeText(getApplicationContext(), "count "+count,
		  				 Toast.LENGTH_LONG);
		  				 toast1223.show();
        	
        	
        	for (int i = 0; i < count; i++) {
				
        		JSONObject mainobj = start_object.getJSONObject(i);
        		
        		String desc=mainobj.getString("description");
				int pid= mainobj.getInt("patient_id");
				int hsid= mainobj.getInt("hospital_staff_id");
        		
				String xray=mainobj.getString("xray");
				
				// for for xray
				
				
				String mri=mainobj.getString("mriscan");
				
				// for and obj create
				
        		
				
        		
        		
			} // main for
        	
        	
			JSONObject jObject = start_object.getJSONObject(6);
			
				String pass= jObject.getString("xray");
        
				JSONArray start_object1=new JSONArray(pass);
			
				String desc=jObject.getString("description");
				int pid= jObject.getInt("patient_id");
				int hsid= jObject.getInt("hospital_staff_id");
				
				JSONObject se= start_object1.getJSONObject(1);
				
				String xrayname=se.getString("name");
				
				String image=se.getString("image");
				
				
				
				byte[] barray=Base64.decode(image,Base64.DEFAULT);
				
				//byte[] barray = image.getBytes();
				
				Toast toast12 = Toast.makeText(getApplicationContext(), "before set",
		  				 Toast.LENGTH_LONG);
		  				 toast12.show();
				
				bmp=BitmapFactory.decodeByteArray(barray,0,barray.length);
				
				try {
				       
					/*File f = new File("c:/test.png");
					f.createNewFile();*/
					
					
				       
				       
				       File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "mobilesaas.png");
				       if(file.exists())
				       {
				    	   Toast toast122 = Toast.makeText(getApplicationContext(), "deleting   ",
					  				 Toast.LENGTH_LONG);
					  				 toast122.show();
				    	   
				    	   file.delete();
				    	   file.createNewFile();   
				       }
				       
				       
				     
				       
				       FileOutputStream out = new FileOutputStream(file);
				       bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
				       out.flush();
				       
				       
				       
				       
				} catch (Exception e) {
				       e.printStackTrace();
				}
				
				//ImageView image=new ImageView(this);
				
				Toast toast13 = Toast.makeText(getApplicationContext(), "after set",
		  				 Toast.LENGTH_LONG);
		  				 toast13.show();
		  				 
				String attr=se.getString("attr");
				
				//JSONArray thirda = new JSONArray(attr);
				JSONObject th =new JSONObject(attr);
				
				String da =th.getString("diagnostic_area");
				String iss =th.getString("issue");
				String sv =th.getString("severity");
				String dc =th.getString("dateCreated");
				String dm =th.getString("dateModified");
				
			int cc= start_object1.length();
			
			String mri= jObject.getString("mriscan");
			
			JSONArray start_object12=new JSONArray(pass);
			
			int count2=start_object12.length();
			
			
			
			Toast toast11 = Toast.makeText(getApplicationContext(), "count "+count+"second cpung  "+cc+" thrid  "+ count2,
	   				 Toast.LENGTH_LONG);
	   				 toast11.show();
			
			
			result1= result1+"desc "+desc+" pid "+pid+" hid "+hsid+" xray name "+xrayname+ " da "+da+" issue "+iss+" serv "+sv+" date c "+dc+" date m "+dm;
			 
	   				 
	   				 
			
        	httpclient.getConnectionManager().shutdown();
        	
        	//Log.d("msg",result);
			//Log.i("you log cat", result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_test, menu);
        return true;
    }
    public void click(View v)
    {
    	Toast toast = Toast.makeText(getApplicationContext(), "i clicked you monkey",
  				 Toast.LENGTH_LONG);
  				 toast.show();
    }
}
