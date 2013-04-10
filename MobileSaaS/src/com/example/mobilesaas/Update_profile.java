package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Update_profile extends Activity {

	Button update, cancel;
	EditText fname,lname,phone,dob,email,addr1,addr2,city,state,zip;
	RadioGroup gender;
	RadioButton male, female;
	String user,userid,patientid;
	int code;
	String result;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        
        fname=(EditText)findViewById(R.id.etufname);
        lname=(EditText)findViewById(R.id.etuLname);
        email=(EditText)findViewById(R.id.etuEmail);
        phone=(EditText)findViewById(R.id.etuPhone);
        dob=(EditText)findViewById(R.id.etuDOB);
        addr1=(EditText)findViewById(R.id.etuAddr1);
        addr2=(EditText)findViewById(R.id.etuAddr2);
        city=(EditText)findViewById(R.id.etuCity);
        state=(EditText)findViewById(R.id.etuState);
        zip=(EditText)findViewById(R.id.etuZip);
        gender=(RadioGroup)findViewById(R.id.uGender);
        male=(RadioButton)findViewById(R.id.rbuMale);
        female=(RadioButton)findViewById(R.id.rbufemale);
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getString("userid");
        
        update=(Button)findViewById(R.id.bUpdateDetails);
        cancel=(Button)findViewById(R.id.bUpdateDetailsCancel);
        
        
        // make get call form user id
        	String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/patients/6/"+userid;
        	result=callrestget(url);
			
			if(result!=null)
			{
				JSONObject json;
				try {
					json = new JSONObject(result);
					fname.setText((String)json.get("fname"));
					lname.setText((String)json.get("lname"));
					email.setText((String)json.get("email"));
					phone.setText((String)json.get("phone"));
					dob.setText((String)json.get("dob"));
					
					
					String g=(String)json.get("gender");
					if(g.equalsIgnoreCase("male"))
					{
						male.setChecked(true);
					}
					else
						female.setChecked(true);
					
					String location = (String)json.get("location_id");
					patientid= (String)json.get("patient_Id");
					
					String locationurl="http://localhost:8181/MedicalInformationSystem/rest/location/6/"+location; 
					
					String result1=callrestget(locationurl);
					if(result1!=null)
					{
						JSONObject json1;
						try {
							
							json1 = new JSONObject(result1);
							addr1.setText((String)json1.get("addr1"));
							addr2.setText((String)json1.get("addr2"));
							city.setText((String)json1.get("city"));
							state.setText((String)json1.get("state"));
							zip.setText((String)json1.get("zipcode"));
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			else
			{
				Toast toast = Toast.makeText(getApplicationContext(), "Some error occured. Please try again",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
				
			}
			
			
			update.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					String getfname=fname.getText().toString();
					String getlname=lname.getText().toString();
					String getemail=email.getText().toString();
					String getgender="";
					if(male.isChecked()==true)
						getgender="male";
					if(female.isChecked()==true)
						getgender="female";
					
					int getzip=Integer.parseInt(zip.getText().toString());
					
					int getphone=Integer.parseInt(phone.getText().toString());
					
					String getaddr1=addr1.getText().toString();
					String getaddr2=addr2.getText().toString();
					String getcity=city.getText().toString();
					String getstate=state.getText().toString();
					String getdob=dob.getText().toString();
					
					
					
					if(getfname.equalsIgnoreCase("") || getlname.equalsIgnoreCase("") || getemail.equalsIgnoreCase("") || getgender.equalsIgnoreCase("") || 
							zip.getText().toString().equalsIgnoreCase("") || phone.getText().toString().equalsIgnoreCase("") || getaddr1.equalsIgnoreCase("") || 
							getaddr2.equalsIgnoreCase("") || getcity.equalsIgnoreCase("") || getstate.equalsIgnoreCase("") || getdob.equalsIgnoreCase(""))
					{
						Toast toast = Toast.makeText(getApplicationContext(), "Fields can not be empty",
				   				 Toast.LENGTH_LONG);
				   				 toast.show();
					}
					else
					{
						
						String pass="";
						
						String json="{\"username\":\""+user+"\",\"password\":\""+pass+"\",\"tenantid\": \"6\",\"roleid\":\"11\",\"patientId\": \""+patientid+"\",\"fname\": \""+getfname+"\",\"lname\":\""+getlname+"\",\"email\": \""+getemail+"\",\"gender\":\""+getgender+"\",\"phone\": \""+getphone+"\",\"addr1\":\""+getaddr1+"\",\"addr2\": \""+getaddr2+"\",\"city\":\""+getcity+"\",\"state\": \""+getstate+"\",\"country\":\"US\",\"zipcode\": \""+getzip+"\",\"dob\": \""+getdob+"\" }";
						
						HttpClient httpClient = new DefaultHttpClient();

					    try {
					        HttpPut request = new HttpPut("http://10.0.2.2:8080/MedicalInformationSystem/rest/patients");
					        StringEntity params =new StringEntity(json);
					        request.addHeader("Content-Type", "application/json");
					        request.setEntity(params);
					        HttpResponse httpResponse = httpClient.execute(request);
						
					        if(httpResponse.getStatusLine().getStatusCode() == 201)
					        {
					        	Toast toast = Toast.makeText(getApplicationContext(), "Profile updated successfully",
						   				 Toast.LENGTH_LONG);
						   				 toast.show();
								Intent browserIntent = new Intent(getApplicationContext(), Home_patient.class);
								browserIntent.putExtra("username", user);
								browserIntent.putExtra("userid", userid);
								startActivity(browserIntent);
					        }
					        else
					        {
					        	Toast toast = Toast.makeText(getApplicationContext(), "Some Error!!",
						   				 Toast.LENGTH_LONG);
						   				 toast.show();
					        	
					        }
					        
					    }
					    catch(Exception e)
					    {
					    	
					    }
					}
				}
			});
		}
	
   
    public String callrestget(String url)
    {
    	HttpClient httpclient = new DefaultHttpClient();  
    	
       // String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/patients/6/"+userid;
        
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
				return result;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_update_profile, menu);
        return true;
    }
}
