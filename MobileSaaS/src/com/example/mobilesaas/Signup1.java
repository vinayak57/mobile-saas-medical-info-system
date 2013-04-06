package com.example.mobilesaas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup1 extends Activity {

	EditText username, password, cpassword;
	Button next, cancel;
	String result;
	int code;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup1);

		username = (EditText) findViewById(R.id.etUsername);
		password = (EditText) findViewById(R.id.etPassword);
		cpassword = (EditText) findViewById(R.id.etConfirmPassword);

		next = (Button) findViewById(R.id.bNext);
		cancel = (Button) findViewById(R.id.bCancel);

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String uname, pass,cpass;
				uname = username.getText().toString();
				pass = password.getText().toString();
				cpass=cpassword.getText().toString();
				
				if(pass.equalsIgnoreCase("") || cpass.equalsIgnoreCase("") || uname.equalsIgnoreCase("") )
				{
					Toast toast = Toast
					.makeText(getApplicationContext(),
							"Fields can not be blank",
							Toast.LENGTH_LONG);
					toast.show();
				}
				else if (!pass.equalsIgnoreCase(cpass))
				{
					Toast toast = Toast
					.makeText(getApplicationContext(),
							"passwords do not match",
							Toast.LENGTH_LONG);
					toast.show();
				}
				else
				{
				
				HttpClient httpclient = new DefaultHttpClient();

				String url = "http://10.0.2.2:8080/MedicalInformationSystem/rest/users/6/"
						+ uname;

				HttpGet request = new HttpGet(url);
				request.addHeader("Accept", "application/json");
				HttpResponse response;
				HttpEntity entity;

				try {

					response = httpclient.execute(request);
					code = response.getStatusLine().getStatusCode();
					
					entity = response.getEntity();
					if (entity != null) {
						

						password.setText("");
						cpassword.setText("");
						Toast toast = Toast
								.makeText(getApplicationContext(),
										"Username already exsists!!",
										Toast.LENGTH_LONG);
						toast.show();

					}

					else {
						
						String details= uname+":"+pass;
						Intent browserIntent = new Intent(
								getApplicationContext(), Signup2.class);
						browserIntent.putExtra("userdetails", details);
						startActivity(browserIntent);

						// already exsists
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(), Login.class);
				startActivity(browserIntent);
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_signup1, menu);
		return true;
	}
}
