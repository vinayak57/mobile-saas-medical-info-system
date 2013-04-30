package com.example.mobilesaas;

import java.net.ServerSocket;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ClinicalSearch extends Activity {
	Button severity, issue, patient, timestamp, bCDiagnosticArea;
	int userid, staffid;
	String user;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinical_search);
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        staffid=getIntent().getExtras().getInt("staffid");
        
        
        severity=(Button)findViewById(R.id.bSeverity);
        issue=(Button)findViewById(R.id.bIssue);
        patient=(Button)findViewById(R.id.bCPatient);
        timestamp=(Button)findViewById(R.id.bTimeStamp);
        bCDiagnosticArea = (Button)findViewById(R.id.bCDiagnosticArea);
        
        severity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),
						SearchSelection.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("staffid", staffid);
				browserIntent.putExtra("navigation", "sev");
				startActivity(browserIntent);
			}
		});
        
        issue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent browserIntent = new Intent(getApplicationContext(),
						SearchSelection.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("staffid", staffid);
				browserIntent.putExtra("navigation", "issue");
				startActivity(browserIntent);
			}
		});
        
        patient.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getApplicationContext(),
						SearchPatient.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("staffid", staffid);
				browserIntent.putExtra("navigation", "patient");
				startActivity(browserIntent);
				
			}
		});
        
        timestamp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getApplicationContext(),
						ChooseTimestamp.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("staffid", staffid);
				browserIntent.putExtra("navigation", "timestamp");
				startActivity(browserIntent);
				
			}
		});
        
        bCDiagnosticArea.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getApplicationContext(),
						SearchSelection.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("staffid", staffid);
				browserIntent.putExtra("navigation", "diago");
				startActivity(browserIntent);
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_clinical_search, menu);
        return true;
    }
}
