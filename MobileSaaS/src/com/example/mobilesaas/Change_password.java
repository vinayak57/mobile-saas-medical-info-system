package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change_password extends Activity {

	Button cancelpassword, update;
	EditText old, newpassword, cnewpassword;
	String userid,useroldpassword, user;
	String result;
	int code;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        
        userid=getIntent().getExtras().getString("userid");
        user=getIntent().getExtras().getString("username");
        
        cancelpassword=(Button)findViewById(R.id.bCancelPassword);
        update=(Button)findViewById(R.id.bUpdateNewPassword);
        old=(EditText)findViewById(R.id.etOldPassword);
        newpassword=(EditText)findViewById(R.id.etNewPassword);
        cnewpassword=(EditText)findViewById(R.id.etConfirmNewPassword);
        
      
        
        cancelpassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),Edit_profile.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				startActivity(browserIntent);
				
			}
		});
        
        update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
						
				
				
				String oldpassword,newpassword1,confirmpassword;
				oldpassword=old.getText().toString();
				newpassword1=newpassword.getText().toString();
				confirmpassword=cnewpassword.getText().toString();
				
				HttpClient httpclient = new DefaultHttpClient();  
		    	
		        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/users/6/"+userid;
		        
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
					useroldpassword=(String)json.get("password");
					}
					else
					{
						Toast toast = Toast
						.makeText(getApplicationContext(),
								"Something Went Wrong",
								Toast.LENGTH_LONG);
						toast.show();

					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
				if(oldpassword.equalsIgnoreCase("") || newpassword1.equalsIgnoreCase("") || confirmpassword.equalsIgnoreCase(""))
				{
					Toast toast = Toast
					.makeText(getApplicationContext(),
							"Fields can not be blank",
							Toast.LENGTH_LONG);
					toast.show();
				}
				else if(!useroldpassword.equalsIgnoreCase(oldpassword))
				{
					Toast toast = Toast
					.makeText(getApplicationContext(),
							"Wrong old Password..!",
							Toast.LENGTH_LONG);
					toast.show();
				}
				else if(!newpassword1.equalsIgnoreCase(confirmpassword))
				{
					Toast toast = Toast
					.makeText(getApplicationContext(),
							"New passwords do not match",
							Toast.LENGTH_LONG);
					toast.show();
				}
				else
				{
					HttpClient httpclient1 = new DefaultHttpClient();

					String json = "{\"userid\":\""+userid+"\",\"username\":\""+user+"\",\"password\": \""+newpassword1+"\",\"tenantid\":\"6\"}";
			
					try {
						
						HttpPut request1 = new HttpPut(
								"http://10.0.2.2:8080/MedicalInformationSystem/rest/users/");
						StringEntity params = new StringEntity(json);
						request1.addHeader("Content-Type", "application/json");
						request1.setEntity(params);
						HttpResponse httpResponse1 = httpclient1.execute(request);
						
						if(httpResponse1.getStatusLine().getStatusCode()==201)
						{
							Toast toast = Toast
							.makeText(getApplicationContext(),
									"Password Updated Successfully",
									Toast.LENGTH_LONG);
							toast.show();
							
							Intent browserIntent = new Intent(getApplicationContext(),Home_patient.class);
							browserIntent.putExtra("userid", userid);
							browserIntent.putExtra("username", user);
							startActivity(browserIntent);
						}
						else
						{
							Toast toast = Toast
							.makeText(getApplicationContext(),
									"Some Error with Server",
									Toast.LENGTH_LONG);
							toast.show();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
			}
				
				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_change_password, menu);
        return true;
    }
}
