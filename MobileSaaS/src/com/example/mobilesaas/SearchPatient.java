package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchPatient extends Activity {

	String user;
	int userid, staffid;
	EditText etfname,etlname;
	Button search;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_patient);
		
		etfname = (EditText) findViewById(R.id.etSearchFname);
		etlname = (EditText) findViewById(R.id.etSearchLname);
		
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        staffid=getIntent().getExtras().getInt("staffid");
		
		search=(Button)findViewById(R.id.bSearchPatient);
		
		 search.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					if(etfname.getText().toString().equalsIgnoreCase("")|| etlname.getText().toString().equalsIgnoreCase(""))
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Choose dates first",
				   				 Toast.LENGTH_LONG);
				   				 toast.show();
					}
					else{
					Intent browserIntent = new Intent(getApplicationContext(),ListPatients.class);
					
					browserIntent.putExtra("flag",4);
					browserIntent.putExtra("fname",etfname.getText().toString() );
					browserIntent.putExtra("lname",etlname.getText().toString() );
					browserIntent.putExtra("userid", userid);
					browserIntent.putExtra("staffid", staffid);
					startActivity(browserIntent);
					}
				}
			});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_patient, menu);
		return true;
	}

}
