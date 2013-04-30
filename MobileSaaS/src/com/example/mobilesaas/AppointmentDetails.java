package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentDetails extends Activity {

	int userid,patientid,code;
	String user,result;
	String hosname,visittype,doctorname,status;
	TextView hname,vtype,docname,tvstatus;
	Button cancelapp,back;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        patientid=getIntent().getExtras().getInt("patientid");
        
        hosname=getIntent().getExtras().getString("hosname");
        visittype=getIntent().getExtras().getString("visit");
        doctorname=getIntent().getExtras().getString("docname");
        status=getIntent().getExtras().getString("status");
        
        hname=(TextView)findViewById(R.id.tvDataHospitalName);
        vtype=(TextView)findViewById(R.id.tvDataVisitType);
        docname=(TextView)findViewById(R.id.tvDataDoctorName);
        tvstatus=(TextView)findViewById(R.id.tvDataStatus);
        
        hname.setText(hosname);
        vtype.setText(visittype);
        docname.setText(doctorname);
        tvstatus.setText(status);
        
        cancelapp=(Button)findViewById(R.id.bPCancelAppointment);
        back=(Button)findViewById(R.id.bPAppointmentBack);
        
        
        
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),ListViewAppointment.class);
				
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
				
				
			}
		});
        
        
        cancelapp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				Toast toast = Toast.makeText(getApplicationContext(), "Appointment is Cancelled",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
				
		   				Intent browserIntent = new Intent(getApplicationContext(),Manage_appointment.class);
						
						browserIntent.putExtra("userid", userid);
						browserIntent.putExtra("username", user);
						browserIntent.putExtra("patientid", patientid);
						startActivity(browserIntent);
			
			}
		});
}
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_appointment_details, menu);
        return true;
    }
}
