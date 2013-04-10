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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	EditText username;
	EditText password;
	Button login,signup;
	String result;
	int code;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        username=(EditText)findViewById(R.id.etUsername);
        password=(EditText)findViewById(R.id.etPassword);
        login=(Button)findViewById(R.id.bLogin);
        signup=(Button)findViewById(R.id.bSignup);
        
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String uname,pwd;
				uname=username.getText().toString();
				pwd=password.getText().toString();
				
			
							
				HttpClient httpclient = new DefaultHttpClient();  
		    	
		        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/users/6/"+uname;
		        
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
						String pass=(String)json.get("password");
						String userid=(String)json.get("userid");
						pass=pass.toString();
						
						Toast toast1 = Toast.makeText(getApplicationContext(), result,
				   				 Toast.LENGTH_LONG);
				   				 toast1.show();
						Log.i(pwd, "pass");
						Log.i(pass, "pass11");
						
						if(pass.toString().equalsIgnoreCase(pwd))
						{
							
							Intent browserIntent = new Intent(getApplicationContext(),Home_patient.class);
							browserIntent.putExtra("username", uname);
							browserIntent.putExtra("userid", userid);
							startActivity(browserIntent);
						}
					}
					else
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Invalid Username/Password!!",
				   				 Toast.LENGTH_LONG);
				   				 toast.show();
				   				 //System.out.println(result);
				   				 
						//display that username does not exists
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
				
				
			}
		});
        
        signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),Signup1.class);
				//browserIntent.putExtra("username", uname);
				//browserIntent.putExtra("userid", userid);
				startActivity(browserIntent);
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
}
