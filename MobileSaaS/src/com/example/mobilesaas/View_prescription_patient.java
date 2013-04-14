package com.example.mobilesaas;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class View_prescription_patient extends Activity {

	int userid,patientid,code,app_id;
	int drug_id;
	String user,result;
	String extra_dose,extra_instruction,extra_sdate,extra_edate;
	String extra_power, extra_name;
	TextView instr,drug_name,power,sdate,edate,dose;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescription_patient);
        
        
        
 
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        patientid=getIntent().getExtras().getInt("patientid");
        app_id=getIntent().getExtras().getInt("appid");
        drug_id =getIntent().getExtras().getInt("extra_drug_id");

        
        extra_dose=getIntent().getExtras().getString("extra_dose");
        extra_instruction=getIntent().getExtras().getString("extra_instruction");
        extra_sdate=getIntent().getExtras().getString("extra_start_date");
        extra_edate=getIntent().getExtras().getString("extra_end_date");
        
        
        
        
        
        instr=(TextView)findViewById(R.id.tvDataDrugInstr);
        drug_name=(TextView)findViewById(R.id.tvDataDrugName);
        power=(TextView)findViewById(R.id.tvDataDrugPower);
        sdate=(TextView)findViewById(R.id.tvDataDrugSDate);
        edate=(TextView)findViewById(R.id.tvDataDrugEDate);
        dose=(TextView)findViewById(R.id.tvDataDrugDose);
        
        
        
        callrest(); 
        
        
        
        instr.setText(extra_instruction);
        drug_name.setText(extra_name);
        power.setText(extra_power);
        sdate.setText(extra_sdate);
        edate.setText(extra_edate);
        dose.setText(extra_dose);
        
        
        
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_prescription_patient, menu);
        return true;
    }
    
    
    public void callrest()
    {
    HttpClient httpclient = new DefaultHttpClient();  
	
    String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/drug/6/"+drug_id;
    
    
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
			
			
				extra_name=json.getString("name");
				extra_power=json.getString("power");
				
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
}
}
