package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class View_medical_history extends Activity {

	Button  allergies,precautions,sideeffects,personaldata;
	String user, userid,patientid;
	String allergy,precaustion,sideeffect;
	String result;
	int code;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_history);
        String result;
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getString("userid");
        patientid=getIntent().getExtras().getString("patientid");
        
        
        
        callrest();
        
        personaldata=(Button)findViewById(R.id.bPerson);
        allergies=(Button)findViewById(R.id.bAllergies);
        precautions=(Button)findViewById(R.id.bPrecautions);
        sideeffects=(Button)findViewById(R.id.bSideEffects);
     
        
        personaldata.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_data.class);
				//browserIntent.putExtra("title", userid);
				//browserIntent.putExtra("username", user);
				startActivity(browserIntent);
			}
		});
        
        allergies.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_data.class);
				browserIntent.putExtra("title", "Allergies");
				browserIntent.putExtra("data", allergy);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				startActivity(browserIntent);
			}
		});
        
        precautions.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_data.class);
				browserIntent.putExtra("title", "Precautions");
				browserIntent.putExtra("data", precaustion);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				startActivity(browserIntent);
			}
		});
        
        sideeffects.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_data.class);
				browserIntent.putExtra("title","Side Effects");
				browserIntent.putExtra("data", sideeffect);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				startActivity(browserIntent);
			}
		});
        
    }

    public void callrest()
    {
    	
    	HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/patientmedicalinfo/"+patientid;
        
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
				allergy=(String)json.get("allergies");
				sideeffect=(String)json.get("side_effects");
				precaustion=(String)json.get("precautions");
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
			
			
			
			
			
			
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_medical_history, menu);
        return true;
    }
}
