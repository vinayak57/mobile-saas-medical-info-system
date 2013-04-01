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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
				
				//String json = "{  \"username\": \""+uname+"\",\"password\": \""+pwd+"\",\"firstName\": \""+fName+"\",\"lastName\":\""+lName+"\",\"phone\": \""+phone+"\",\"password\":\""+hashtext+"\",\"";
				
				HttpClient httpclient = new DefaultHttpClient();  
		    	
		        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/users/"+uname;
		        
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
						if(pass.equalsIgnoreCase(pwd))
						{
							//call next intent
							result="you got there";
						}
					}
					else
					{
						//display that username does not exists
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
				
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
}
