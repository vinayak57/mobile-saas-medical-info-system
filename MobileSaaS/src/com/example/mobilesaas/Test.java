package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends Activity {
	Button submit;
	TextView test;
	String result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        submit=(Button)findViewById(R.id.bSubmit);
        test=(TextView)findViewById(R.id.tvValue);
        
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callRest();
				test.setText(result);
				String abc=test.getText().toString();
				System.out.println(abc);
				
			}
		});
    }

    public void callRest(){
    	
    	
    	HttpClient httpclient = new DefaultHttpClient();  
    	String uname="mandar";
        String q="http://10.0.2.2:8080/MedicalInformationSystem/rest/patientmedicalinfo/5";
        
        HttpGet request = new HttpGet(q);
        request.addHeader("Accept","application/json");
        HttpResponse res;
        try {
        	ResponseHandler<String> handler =new BasicResponseHandler();
        	//result= httpclient.execute(request,handler);
        	res=httpclient.execute(request);
        	HttpEntity entity=res.getEntity();
        	result=EntityUtils.toString(entity);
        	
        	int code=res.getStatusLine().getStatusCode();
        	Toast toast = Toast.makeText(getApplicationContext(), "my codee"+code,
	   				 Toast.LENGTH_LONG);
	   				 toast.show();
        	//System.out.println(result);
        	
        	result=result+"  my codeee"+code;
        	
        	
        	
        	
        	
        	//JSONObject json = new JSONObject(result);
			//String pass=(String)json.get("allergies");
			
        	JSONArray start_object=new JSONArray(result);
			JSONObject jObject = start_object.getJSONObject(0);
			String pass= jObject.getString("allergies");
        	
			result = result+  "my pass " + pass;
			
			
			
			
			
			
        	httpclient.getConnectionManager().shutdown();
        	
        	//Log.d("msg",result);
			//Log.i("you log cat", result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_test, menu);
        return true;
    }
    public void click(View v)
    {
    	Toast toast = Toast.makeText(getApplicationContext(), "i clicked you monkey",
  				 Toast.LENGTH_LONG);
  				 toast.show();
    }
}
