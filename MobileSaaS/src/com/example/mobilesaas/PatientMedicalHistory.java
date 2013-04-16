package com.example.mobilesaas;

import java.util.ArrayList;
import java.util.HashMap;
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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientMedicalHistory extends Activity {

	int userid,hospital_staff_id, code;
	String result;
	Button bSearchPatient, bSearchCancel;
	EditText etdfname,etdlname;
	List<String> patientList = new ArrayList<String>();
	List<String> patientRawList = new ArrayList<String>();
	HashMap<String, Integer> patientMap = new HashMap<String, Integer>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_medical_history);
		
		Log.d("before rest ", "here");
		bSearchPatient=(Button)findViewById(R.id.bSearchPatient);
		bSearchCancel=(Button)findViewById(R.id.bSearchCancel);
		
		  userid=getIntent().getExtras().getInt("userid");
	      // user=getIntent().getExtras().getString("username");
		  //hospital_staff_id=getIntent().getExtras().getInt("hospital_staff_id");
	      //app_id=getIntent().getExtras().getInt("appid");
	      
		  etdfname=(EditText)findViewById(R.id.etdfname);
	      etdlname=(EditText)findViewById(R.id.etdlname);


	      bSearchPatient.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub		
					Log.d("here" , etdfname.getText().toString());
					//if(etdfname.getText().toString()!="" && etdlname.getText().toString()!="")
					{
						Intent browserIntent = new Intent(getApplicationContext(), ListPatients.class);
						browserIntent.putExtra("userid", userid);
						browserIntent.putExtra("fname", etdfname.getText().toString());
						browserIntent.putExtra("lname", etdlname.getText().toString());
						startActivity(browserIntent);
					}
					
					
					
					
					
				}
			});
	      
	      bSearchCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(), Home_doctor.class);
				browserIntent.putExtra("userid", userid);
				startActivity(browserIntent);
			}
		});
	      
	      
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_medical_history, menu);
		return true;
	}

}
