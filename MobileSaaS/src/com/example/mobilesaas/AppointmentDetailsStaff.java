package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentDetailsStaff extends Activity {

	int userid,app_id,code,patientId;
	TextView patientName,visitType,status;
	Button closeAppmnt;
	String result;
	String name, visit, statusJson;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_details_staff);
		
		  userid=getIntent().getExtras().getInt("userid");
	      app_id=getIntent().getExtras().getInt("appid");
	      patientId = getIntent().getExtras().getInt("patientId");
	      closeAppmnt=(Button)findViewById(R.id.bCloseAppmnt);
	      
	      patientName=(TextView)findViewById(R.id.tvDataPatientName);
	      visitType=(TextView)findViewById(R.id.tvDataApmntVisitType);
	      status=(TextView)findViewById(R.id.tvDataAppmntStatus);
	      
	      callrest();
	      
	      patientName.setText(name);
			visitType.setText(visit);
			status.setText(statusJson);
	      
	      
	      closeAppmnt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					updateStatus();
					
//					Intent browserIntent = new Intent(getApplicationContext(),ViewImage.class);
//					
//					browserIntent.putExtra("appid", userid);
//					startActivity(browserIntent);
				}
			});
	      
	      
	}

	public void updateStatus()
	{
    	
		String json = "{\"appointment_id\":\"" + app_id + "\"}";

        HttpClient httpClient = new DefaultHttpClient();

	    try {
	        HttpPut request = new HttpPut("http://10.0.2.2:8080/MedicalInformationSystem/rest/appointments/updatestatus");
	        StringEntity params =new StringEntity(json);
	        request.addHeader("Content-Type", "application/json");
	        request.setEntity(params);
	        HttpResponse httpResponse = httpClient.execute(request);
		
	        if(httpResponse.getStatusLine().getStatusCode() == 201)
	        {
	        	Toast toast = Toast.makeText(getApplicationContext(), "Status Updated!!",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
				Intent browserIntent = new Intent(getApplicationContext(), AppointmentDetailsStaff.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("patientId", patientId);
				//browserIntent.putExtra("hospital_staff_id", hospital_staff_id);
				browserIntent.putExtra("appid", app_id);
				startActivity(browserIntent);
	        }
	        else
	        {
	        	Toast toast = Toast.makeText(getApplicationContext(), "Some Error!!",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
	        	
	        }
	        
	    }
	    catch(Exception e)
	    {
	    	
	    }
	}
	
	public void callrest()
	{
		HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/appointments/6/" + patientId + "/" + app_id;
        
        HttpGet request = new HttpGet(url);
        request.addHeader("Accept","application/json");
        HttpResponse response;
		HttpEntity entity;
		try {

			response=httpclient.execute(request);
			entity=response.getEntity();
			result=EntityUtils.toString(entity);
			
			code=response.getStatusLine().getStatusCode();
			
			if(code==200)
			{
				//JSONArray start_object = new JSONArray(result);
				//int count=start_object.length();
				JSONObject json = new JSONObject(result);
			
//				for (int i = 0; i < count; i++) {
//					
//					json = start_object.getJSONObject(i);
					//Log.d("after rest", json.toString());
					name =json.getString("fname") + " " + json.getString("lname");
					visit = json.getString("visit_type");
					statusJson = json.getString("status");
		
					//Log.d("calling patient", String.valueOf(patientId));
					
					
				//}
				
				
			}
			else
			{
				Toast toast = Toast.makeText(getApplicationContext(), "Some error occured",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appointment_details_staff, menu);
		return true;
	}

}
