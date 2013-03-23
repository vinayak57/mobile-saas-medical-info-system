package com.example.mobilesaas;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Test extends Activity {
	Button submit;
	TextView test;
	String result=null;
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
			}
		});
    }

    public void callRest(){
    	
    	
    	HttpClient httpclient = new DefaultHttpClient();  
    	
        String q="http://localhost:8080/MedicalInformationSystem/rest/users";
        
        HttpGet request = new HttpGet(q);
        ResponseHandler<String> handler =new BasicResponseHandler();
        try {
			
        	result= httpclient.execute(request,handler);
        	
		} catch (Exception e) {
			// TODO: handle exception
			Log.d(result, "result");
		}
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_test, menu);
        return true;
    }
}
