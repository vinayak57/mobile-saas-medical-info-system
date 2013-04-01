package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
    	
        String q="http://10.0.2.2:8080/MedicalInformationSystem/rest/users/mandar";
        
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
        	//System.out.println(result);
        	result=result+"  my codeee"+code;
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
}
