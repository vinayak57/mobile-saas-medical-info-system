package com.example.mobilesaas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Prescribe_Drug extends Activity {

	int userid,hospital_staff_id,code,app_id;
	Button bPrescribe, bPrescribeCancel;
	EditText etdose,etstartDate,etendDate,etinstruction;
	Spinner ddDrug;
	String result;
	List<String> drugList = new ArrayList<String>();
	List<Integer> drugidList =new ArrayList<Integer>();
	HashMap<String,Integer> drugMap= new HashMap<String, Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescribe__drug);
		
		
		bPrescribe=(Button)findViewById(R.id.bPrescribe);
		bPrescribeCancel=(Button)findViewById(R.id.bPrescribeCancel);
		
		  userid=getIntent().getExtras().getInt("userid");
	      // user=getIntent().getExtras().getString("username");
		  //hospital_staff_id=getIntent().getExtras().getInt("hospital_staff_id");
	      app_id=getIntent().getExtras().getInt("appid");
	      
	      etdose=(EditText)findViewById(R.id.etdose);
	        etstartDate=(EditText)findViewById(R.id.etstartDate);
	        etendDate=(EditText)findViewById(R.id.etendDate);
	        etinstruction=(EditText)findViewById(R.id.etinstruction);
	        ddDrug =(Spinner)findViewById(R.id.spChooseDrug);
	        
	        callrest();
	        
	        Log.d("after rest", String.valueOf(drugList.size()));
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, drugList);
			
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			ddDrug.setAdapter(dataAdapter);
	      
			bPrescribe.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						
						 String p=ddDrug.getSelectedItem().toString();
						
						Log.d("after click", p);
						 
						 Integer drugId = drugMap.get(p);
						 
						 Log.d("after click", String.valueOf(drugId));
						
						insertPrescriptionRest(drugId.intValue());
						
					}
				});
		        
		        
			bPrescribeCancel.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						Intent browserIntent = new Intent(getApplicationContext(), Home_doctor.class);
						browserIntent.putExtra("userid", userid);
						startActivity(browserIntent);
					}
				});
	}

	
	public void insertPrescriptionRest(int drugId)
	{
		//String json="{\"username\":\""+user+"\",\"password\":\""+pass+"\",\"tenantid\": \"6\",\"roleid\":\"11\",\"patientId\": \""+patientid+"\",\"fname\": \""+getfname+"\",\"lname\":\""+getlname+"\",\"email\": \""+getemail+"\",\"gender\":\""+getgender+"\",\"phone\": \""+getphone+"\",\"addr1\":\""+getaddr1+"\",\"addr2\": \""+getaddr2+"\",\"city\":\""+getcity+"\",\"state\": \""+getstate+"\",\"country\":\"US\",\"zipcode\": \""+getzip+"\",\"dob\": \""+getdob+"\" }";
		String json = "{\"drug_id\":\"" + drugId + "\",\"dose\":\"" +etdose.getText().toString() + "\",\"start_date\":\"" +etstartDate.getText().toString() + "\",\"end_date\":\"" +etendDate.getText().toString() + "\",\"instruction\":\"" +etinstruction.getText().toString() + "\",\"tenantid\":\"6\",\"appointment_id\":\"" + app_id +"\"}";
		Log.d("insert presc", json);
		HttpClient httpClient = new DefaultHttpClient();

	    try {
	        HttpPut request = new HttpPut("http://10.0.2.2:8080/MedicalInformationSystem/rest/prescription");
	        StringEntity params =new StringEntity(json);
	        request.addHeader("Content-Type", "application/json");
	        request.setEntity(params);
	        HttpResponse httpResponse = httpClient.execute(request);
		
	        if(httpResponse.getStatusLine().getStatusCode() == 201)
	        {
//	        	Toast toast = Toast.makeText(getApplicationContext(), "Profile updated successfully",
//		   				 Toast.LENGTH_LONG);
//		   				 toast.show();
	        	
				Intent browserIntent = new Intent(getApplicationContext(), Prescribe_Drug.class);
				//browserIntent.putExtra("username", user);
				browserIntent.putExtra("userid", userid);
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
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/drug/6/";
        
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
				JSONArray start_object = new JSONArray(result);
				int count=start_object.length();
				JSONObject json;
			
				long rawdate;

				for (int i = 0; i < count; i++) {
					
					json = start_object.getJSONObject(i);
					Log.d("after rest", json.toString());
					Integer drug_id = Integer.valueOf(json.getString("drugId"));
					String name =json.getString("name");
					String power = json.getString("power");
					
								
					drugList.add("Drug: "+name+" , Power :"+power);
					drugidList.add(drug_id);
					drugMap.put("Drug: "+name+" , Power :"+power, drug_id);
					
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.prescribe__drug, menu);
		return true;
	}

}
