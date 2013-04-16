package com.example.mobilesaas;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Home_clinical extends Activity {

	Button cancel, viewdata;
	Spinner appointment;
	int userid,patientid;
	String user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_clinical);
        
      
        
        cancel=(Button)findViewById(R.id.bCancel);
        viewdata=(Button)findViewById(R.id.bViewData);
        appointment=(Spinner)findViewById(R.id.spChooseAppoint);
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        patientid=getIntent().getExtras().getInt("patientid");
       
        
        callrest();
        
        // make a get call to get all appointments of the user
        // then make a Array of string or List and then set it to spinner
        
        
        final String[] test = new String[] { "View Xrays", "View MRI scans" };
        
        /*List<String> list = new ArrayList<String>();
    	list.add("list 1");
    	list.add("list 2");
    	list.add("list 3");*/
        	
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, test);
		
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		appointment.setAdapter(dataAdapter);
        
        viewdata.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				 String p=appointment.getSelectedItem().toString();
				
				 // get the selected appointment and then pass it to ListImages to get all the images
				 
				 Toast toast = Toast.makeText(getApplicationContext(), p,
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
				
		   				 
		   				Intent browserIntent = new Intent(getApplicationContext(), ListImages.class);
						browserIntent.putExtra("userid", userid);
						browserIntent.putExtra("username", user);
						browserIntent.putExtra("patientid", patientid);
						browserIntent.putExtra("clinicaldata", p);
						startActivity(browserIntent);
				
			}
		});
        
        
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(), Home_patient.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_clinical, menu);
        return true;
    }
    
    
    public void callrest()
    {
    	
    }
}
