package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Manage_appointment extends Activity {

	Button makeapp, viewapp;
	int userid,patientid;
	String user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_appointment);
      
        
      makeapp=(Button)findViewById(R.id.bMakeappointment);
      viewapp=(Button)findViewById(R.id.bViewCancel);
        
      userid=getIntent().getExtras().getInt("userid");
      user=getIntent().getExtras().getString("username");
      patientid=getIntent().getExtras().getInt("patientid");
        
      makeapp.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent browserIntent = new Intent(getApplicationContext(),Make_appointment.class);
			browserIntent.putExtra("username", user);
			browserIntent.putExtra("userid", userid);
			browserIntent.putExtra("patientid", patientid);
			startActivity(browserIntent);
		}
	});
      
      
      viewapp.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_make_appointment, menu);
        return true;
    }
}
