package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchSelection extends Activity {

	Button viewdata;
	Spinner crieteria;
	int userid,staffid;
	String user;
	String navigation;
	int flag=0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_selection);
        flag=0;
        
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        staffid=getIntent().getExtras().getInt("staffid");
        navigation=getIntent().getExtras().getString("navigation");
        
        viewdata=(Button)findViewById(R.id.bViewDocClinicalData);
        crieteria= (Spinner)findViewById(R.id.spChooseCrieteria);
        
        final String[] sev = new String[] { "HIGH", "LOW", "MEDIUM" };
        final String[] issue = new String[] {  "BROKEN", "FRACTURE", "PAIN", "HAIRLINE_FRACTURE" };
        
        if(navigation.equalsIgnoreCase("issue"))
        {
        	flag=0;
        	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, issue);
    		
    		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		crieteria.setAdapter(dataAdapter);
        }
        else if(navigation.equalsIgnoreCase("sev"))
        {
        	flag=1;
        	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sev);
    		
    		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    		crieteria.setAdapter(dataAdapter);
        }
        else if(navigation.equalsIgnoreCase("diago"))
        {
        	flag=2;
        }
        
        
        
        viewdata.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String cr=crieteria.getSelectedItem().toString();
				
				Toast toast = Toast.makeText(getApplicationContext(), cr,
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
		   				 
				Intent browserIntent = new Intent(getApplicationContext(),ListImagesStaff.class);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("staffid", staffid);
				browserIntent.putExtra("cr", cr);
				browserIntent.putExtra("flag", flag);
				startActivity(browserIntent);
				
			}
		});
        
        
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_selection, menu);
        return true;
    }
}
