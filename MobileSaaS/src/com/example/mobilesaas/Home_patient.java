package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Home_patient extends Activity {

	ImageView appointment, medical, prescription, clinical,emergancy;
	TextView welcome;
	Button signout;
	String user,temp;
	String userid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);
        
        userid=getIntent().getExtras().getString("userid");
        
        appointment=(ImageView)findViewById(R.id.ivAppointment);
        medical=(ImageView)findViewById(R.id.ivMedical);
        prescription=(ImageView)findViewById(R.id.ivPrescription);
        clinical=(ImageView)findViewById(R.id.ivClinical);
        emergancy=(ImageView)findViewById(R.id.ivEmergancy);
        welcome=(TextView)findViewById(R.id.tvWelcome);
        signout=(Button)findViewById(R.id.bSignout);
        
        user=getIntent().getExtras().getString("username");
        welcome.setText(welcome.getText().toString()+user);
        
        appointment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),Manage_appointment.class);
				browserIntent.putExtra("username", user);
				startActivity(browserIntent);
				
			}
		});
       
        
        emergancy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),Map.class);
				//browserIntent.putExtra("username", user);
				startActivity(browserIntent);
				
			}
		});
        
        clinical.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),Home_clinical.class);
				browserIntent.putExtra("userid", userid);
				startActivity(browserIntent);
				
			}
		});
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_patient, menu);
        return true;
    }
}
