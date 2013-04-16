package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.Spinner;

public class SearchSelection extends Activity {

	Button viewdata;
	Spinner crieteria;
	int userid,staffid;
	String user;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_selection);
        
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        staffid=getIntent().getExtras().getInt("staffid");
        
        viewdata=(Button)findViewById(R.id.bViewDocClinicalData);
        crieteria= (Spinner)findViewById(R.id.spChooseCrieteria);
        
        final String[] sev = new String[] { "HIGH", "LOW", "MEDIUM" };
        final String[] issue = new String[] {  "BROKEN", "FRACTURE", "PAIN", "HAIRLINE_FRACTURE" };
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_selection, menu);
        return true;
    }
}
