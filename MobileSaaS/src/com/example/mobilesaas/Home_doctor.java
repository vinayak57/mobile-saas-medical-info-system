package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home_doctor extends Activity {

	ImageView clinical, history, appointment, prescribe;
	TextView welcome;
	String user,result;
	int userid,code;
	int staffid;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_doctor);
        
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        
        HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/staff/6/"+userid;
        
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
				JSONObject json = new JSONObject(result);
				staffid= (Integer)json.get("hospitalStaffId");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        
		
		
		clinical=(ImageView)findViewById(R.id.ivDoctorClinical);
		history=(ImageView)findViewById(R.id.ivDoctorMedical);
		appointment=(ImageView)findViewById(R.id.ivDoctorAppointment);
		prescribe=(ImageView)findViewById(R.id.ivDoctorPrescription);
		welcome=(TextView)findViewById(R.id.tvDocWelcome);
		
		welcome.setText(welcome.getText().toString()+user);
		
		
		clinical.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),ClinicalSearch.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username",user );
				browserIntent.putExtra("staffid",staffid );
				startActivity(browserIntent);
				
			}
		});
		
		history.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		appointment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		prescribe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_doctor, menu);
        return true;
    }
}
