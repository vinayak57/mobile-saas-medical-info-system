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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Home_patient extends Activity {

	ImageView appointment, medical, prescription, clinical,emergancy;
	TextView welcome;
	Button signout;
	String user,temp,result;
	int userid;
	int patientid;
	int code;
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);
        
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
 
		
        
        HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/patients/6/"+userid;
        
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
				patientid= (Integer)json.get("patientId");
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
        
        
        
         
        appointment=(ImageView)findViewById(R.id.ivAppointment);
        medical=(ImageView)findViewById(R.id.ivMedical);
        prescription=(ImageView)findViewById(R.id.ivPrescription);
        clinical=(ImageView)findViewById(R.id.ivClinical);
        emergancy=(ImageView)findViewById(R.id.ivEmergancy);
        welcome=(TextView)findViewById(R.id.tvWelcome);
        signout=(Button)findViewById(R.id.bSignout);
        
        
        
        welcome.setText(welcome.getText().toString()+user);
        
        appointment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),Manage_appointment.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username",user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
				
			}
		});
       
        
        emergancy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),Map.class);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("userid", userid);
				startActivity(browserIntent);
				
			}
		});
        
        clinical.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),Home_clinical.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
				
			}
		});
        
        signout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),Login.class);
				startActivity(browserIntent);
			}
		});
        
        
        medical.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_medical_history.class);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
				
			}
		});
        
        prescription.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),ListAppointments.class);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
			}
		});
        
        
        
    }

    public void update(View v)
    {
    	Intent browserIntent = new Intent(getApplicationContext(),Edit_profile.class);
		browserIntent.putExtra("userid", userid);
		browserIntent.putExtra("username", user);
		startActivity(browserIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_patient, menu);
        return true;
    }
}
