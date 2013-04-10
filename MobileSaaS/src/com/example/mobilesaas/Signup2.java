package com.example.mobilesaas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

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

public class Signup2 extends Activity {

	EditText fname, lname,email,phone,dob,addr1,addr2,city,state,zip;
	Button cancel,register;
	RadioGroup gender;
	String uname,pass;
	RadioButton male,female;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        
        fname=(EditText)findViewById(R.id.etfname);
        lname=(EditText)findViewById(R.id.etLname);
        email=(EditText)findViewById(R.id.etEmail);
        phone=(EditText)findViewById(R.id.etPhone);
        dob=(EditText)findViewById(R.id.etDOB);
        addr1=(EditText)findViewById(R.id.etAddr1);
        addr2=(EditText)findViewById(R.id.etAddr2);
        city=(EditText)findViewById(R.id.etCity);
        state=(EditText)findViewById(R.id.etState);
        zip=(EditText)findViewById(R.id.etZip);
        gender=(RadioGroup)findViewById(R.id.Gender);
        male=(RadioButton)findViewById(R.id.rbMale);
        female=(RadioButton)findViewById(R.id.rbfemale);
        
        male.setChecked(true);
        
        cancel=(Button)findViewById(R.id.bCancel);
        register=(Button)findViewById(R.id.bRegister);
        
        cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(), Login.class);
				startActivity(browserIntent);
			}
		});
        
        
        
        register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String fname1= fname.getText().toString();
				String lname1=lname.getText().toString();
				String email1=email.getText().toString();
				String phone12=phone.getText().toString();
				String dob1=dob.getText().toString();
				String addr11=addr1.getText().toString();
				String addr21=addr2.getText().toString();
				String city1=city.getText().toString();
				String state1=state.getText().toString();
				String zip12=zip.getText().toString();
				int zip1=Integer.parseInt(zip12);
				
				int phone1=Integer.parseInt(phone12);
				
				/*Date dob1=null;
				try {
					dob1 = new SimpleDateFormat("yyyy-mm-dd").parse(dob12);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				String gender1="";
				
				if(male.isChecked()==true)
					gender1="male";
				if(female.isChecked()==true)
					gender1="female";
				
				
				if(fname1.equalsIgnoreCase("") || lname1.equalsIgnoreCase("") || email1.equalsIgnoreCase("") || phone12.equalsIgnoreCase("") || dob1.equalsIgnoreCase("") || 
						addr11.equalsIgnoreCase("") || addr21.equalsIgnoreCase("") || city1.equalsIgnoreCase("") || state1.equalsIgnoreCase("") || zip12.equalsIgnoreCase("")) //check for validations
				{
					Toast toast = Toast.makeText(getApplicationContext(), "Fields can not be empty",
			   				 Toast.LENGTH_LONG);
			   				 toast.show();
				}
				else
				{
					int count=0;
					String details = getIntent().getExtras().getString("userdetails");
					StringTokenizer st= new StringTokenizer(details,":");
					while(st.hasMoreElements())
					{
						if(count==0)
						{
							uname=(String) st.nextElement();
						}
						if(count==1)
						{
							pass=(String) st.nextElement();
						}
						count++;
					}
					
					
					// set json and do post
				String json="{\"username\":\""+uname+"\",\"password\":\""+pass+"\",\"tenantid\": \"6\",\"roleid\":\"11\",\"fname\": \""+fname1+"\",\"lname\":\""+lname1+"\",\"email\": \""+email1+"\",\"gender\":\""+gender1+"\",\"phone\": \""+phone1+"\",\"addr1\":\""+addr11+"\",\"addr2\": \""+addr21+"\",\"city\":\""+city1+"\",\"state\": \""+state1+"\",\"country\":\"US\",\"zipcode\": \""+zip1+"\",\"dob\": \""+dob1+"\" }";
				//String json "{\"udfId\"   : \""+userId+"\",\"email\"  : \""+email+"\",\"firstName\": \""+fName+"\",\"lastName\":\""+lName+"\",\"phone\": \""+phone+"\",\"password\":\""+hashtext+"\",\"account\": {   \"organization\": \""+account+"\"  },\"address\": {    \"city\": \""+city+"\",\"countryCode\": \""+countryCode+"\",\"postalCode\": \""+postalCode+"\",\"stateCode\": \""+state+"\",\"streetAddress1\": \""+add+"\"  }}";					
				
				//String input"{\"addr1\":\"201 S 4th Street\",\"addr2\":\"Apt#527\", \"city\":\"san jose\",\"state\":\"CA\",\"country\":\"US\",\"zipcode\":\"95112\",\"location_id\":\"6\"}";	
					//String json = "{  \"udfId\": \""+userId+"\",\"email\": \""+email+"\",\"firstName\": \""+fName+"\",\"lastName\":\""+lName+"\",\"phone\": \""+phone+"\",\"password\":\""+hashtext+"\",\"account\": {   \"organization\": \""+account+"\"  },\"address\": {    \"city\": \""+city+"\",\"countryCode\": \""+countryCode+"\",\"postalCode\": \""+postalCode+"\",\"stateCode\": \""+state+"\",\"streetAddress1\": \""+add+"\"  }}";					
					HttpClient httpClient = new DefaultHttpClient();

				    try {
				        HttpPut request = new HttpPut("http://10.0.2.2:8080/MedicalInformationSystem/rest/patients");
				        StringEntity params =new StringEntity(json);
				        request.addHeader("Content-Type", "application/json");
				        request.setEntity(params);
				        HttpResponse httpResponse = httpClient.execute(request);
					
				        if(httpResponse.getStatusLine().getStatusCode() == 201)
				        {
				        	Toast toast = Toast.makeText(getApplicationContext(), "Object Created!!",
					   				 Toast.LENGTH_LONG);
					   				 toast.show();
							Intent browserIntent = new Intent(getApplicationContext(), Login.class);
							//browserIntent.putExtra("username", uname);
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
					
					
				}// end of else
				
				
			}
		});
        
        
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_signup2, menu);
        return true;
    }
}
