package com.example.mobilesaas;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Make_appointment extends Activity {

	int code;
	int hid=0;
	int userid, patientid;
	String user, result;
	Button cancel,next;
	Spinner hospital,doctor,visit;
	List<String> hospitalname = new ArrayList<String>();
	List<String> hospitalstaff = new ArrayList<String>();
	List<String> visittype = new ArrayList<String>();
	List<Integer> hospitalid = new ArrayList<Integer>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        patientid=getIntent().getExtras().getInt("patientid");
        
        
        cancel=(Button)findViewById(R.id.bCancel);
        next=(Button)findViewById(R.id.bAppNext);
        
        hospital=(Spinner)findViewById(R.id.spHospital);
        
        visit=(Spinner)findViewById(R.id.spVisit);
        
        visittype.add("General");
        visittype.add("Monthly");
        visittype.add("Quarterly");
        visittype.add("Annual");
        
        ArrayAdapter<String> dataAdaptervisit = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, visittype);
		
		dataAdaptervisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		visit.setAdapter(dataAdaptervisit);
        
        callrest();
        
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, hospitalname);
		
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		hospital.setAdapter(dataAdapter);
		
		
        
        hospital.setOnItemSelectedListener(new OnItemSelectedListener() {
            
        	@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				
        		String test = hospital.getSelectedItem().toString();
        		int i;
        		
        		for (String hname : hospitalname) {
					if(hname.equalsIgnoreCase(test))
					{
						i= hospitalname.indexOf(hname);
						 hid = hospitalid.get(i);
						
						break;
					}
				}
        		
        		
   	   			callsecondrest(hid);
   	   			
   	   			doctor=(Spinner)findViewById(R.id.spDoctor);
   	   		
   	   			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, hospitalstaff);
   			
   	   			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   	   			doctor.setAdapter(dataAdapter);

   	   			
   	   			
   	   			
			}


			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}

        });
        
        
       next.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			int testv=visit.getSelectedItemPosition();
			testv=testv+1;
			Intent browserIntent = new Intent(getApplicationContext(),Patient_Appointment.class);
			
			browserIntent.putExtra("userid", userid);
			browserIntent.putExtra("username", user);
			browserIntent.putExtra("patientid", patientid);
			browserIntent.putExtra("hid",hid );
			browserIntent.putExtra("vid",testv );
			
			startActivity(browserIntent);
			
		}
	});
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_make_appointment, menu);
        return true;
    }
    
    public void callsecondrest(int hospitalid)
    {
    	HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/staff/hospital/6/"+hospitalid;
        
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
				JSONObject json=new JSONObject();
				JSONArray start_object = new JSONArray(result);
				int count=start_object.length();
				hospitalstaff.clear();
				
				for (int i = 0; i < count; i++) {
					
					json = start_object.getJSONObject(i);
					
					String name= json.getString("fname")+" "+json.getString("lname");
					
					hospitalstaff.add(name);
					//hospitalid.add(json.getInt("hospital_id"));
				}
				
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
    
    
    
    public void callrest()
    {
    	HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/hospital/6/";
        
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
				JSONObject json=new JSONObject();
				JSONArray start_object = new JSONArray(result);
				int count=start_object.length();
				
				for (int i = 0; i < count; i++) {
					
					json = start_object.getJSONObject(i);
					
					hospitalname.add(json.getString("hospital_name"));
					hospitalid.add(json.getInt("hospital_id"));
				}
				
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
    
}
