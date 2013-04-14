package com.example.mobilesaas;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class View_medical_history extends Activity {

	Button  allergies,precautions,sideeffects,personaldata;
	String user;
	int userid,patientid,height,weight;
	String allergy,precaustion,sideeffect,bloodgroup;
	String result;
	int code;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_history);
       
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        patientid=getIntent().getExtras().getInt("patientid");
        
        
        
        callrest();
        
        personaldata=(Button)findViewById(R.id.bPerson);
        allergies=(Button)findViewById(R.id.bAllergies);
        precautions=(Button)findViewById(R.id.bPrecautions);
        sideeffects=(Button)findViewById(R.id.bSideEffects);
     
        
        personaldata.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),Personal_history.class);
				browserIntent.putExtra("height", height);
				browserIntent.putExtra("weight", weight);
				browserIntent.putExtra("bloodgroup", bloodgroup);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
			}
		});
        
        allergies.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_data.class);
				browserIntent.putExtra("title", "Allergies");
				browserIntent.putExtra("data", allergy);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
			}
		});
        
        precautions.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_data.class);
				browserIntent.putExtra("title", "Precautions");
				browserIntent.putExtra("data", precaustion);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
			}
		});
        
        sideeffects.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_data.class);
				browserIntent.putExtra("title","Side Effects");
				browserIntent.putExtra("data", sideeffect);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
			}
		});
        
    }

    public void callrest()
    {
    	
    	HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/patientmedicalinfo/"+patientid;
        
        HttpGet request = new HttpGet(url);
        request.addHeader("Accept","application/json");
        HttpResponse response;
		HttpEntity entity;
		try {

			response=httpclient.execute(request);
			entity=response.getEntity();
			result=EntityUtils.toString(entity);
			Toast toast1 = Toast.makeText(getApplicationContext(), result,
	   				 Toast.LENGTH_LONG);
	   				 toast1.show();
			code=response.getStatusLine().getStatusCode();
			
			if(code==200)
			{
				Toast toast = Toast.makeText(getApplicationContext(), "in 200 bocha",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
		   				 
				//JSONObject json = new JSONObject(result);
				
				JSONArray start_object = new JSONArray(result);
				JSONObject json = start_object.getJSONObject(0);
				
				allergy = (String) json.getString("allergies");
				sideeffect = (String) json.getString("side_effects");
				precaustion = (String) json.getString("precautions");
				height = (Integer) json.get("height");
				weight = (Integer) json.get("weight");
				bloodgroup = (String) json.getString("bloodgroup");
				if(bloodgroup==null)
					bloodgroup="O+";
			
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
        getMenuInflater().inflate(R.menu.activity_view_medical_history, menu);
        return true;
    }
}
