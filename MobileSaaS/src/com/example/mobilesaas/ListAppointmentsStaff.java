package com.example.mobilesaas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListAppointmentsStaff extends ListActivity {

	int userid,hospital_staff_id,code;
	String user,result;
	List<String> appointment = new ArrayList<String>();
	List<Integer> appointment_id =new ArrayList<Integer>();
	List<String> rawappointment= new ArrayList<String>();
	private static int save= -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_appointments_staff);
		
		  userid=23;//getIntent().getExtras().getInt("userid");
		  hospital_staff_id = 1;//getIntent().getExtras().getInt("hospital_staff_id");
		  
		  
		  callrest(); 
		  
		  this.setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, appointment));
		  
		  ListView listView = getListView();

			listView.setCacheColorHint(0);
			listView.setBackgroundResource(R.drawable.background);
			
			listView.setTextFilterEnabled(true);

			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
					
					parent.getChildAt(position).setBackgroundColor(Color.parseColor("#70D0D5D9"));
					
					if(save!=-1 && save!=position)
					{
						parent.getChildAt(position).setBackgroundColor(Color.GREEN);
					}
					save=position;
					
					int apid=0, i=0;
					
					
					try {
						String product = ((TextView) view).getText().toString();
						for(String s: appointment)
						{
							if(s.equalsIgnoreCase(product));
							{
								 i= appointment.indexOf(product);
								 apid = appointment_id.get(i);
								break;
							}
							
						}
						Intent browserIntent = new Intent(getApplicationContext(),Prescribe_Drug.class);
						
						browserIntent.putExtra("userid", userid);
						//browserIntent.putExtra("username", user);
						browserIntent.putExtra("hospital_staff_id", hospital_staff_id);
						browserIntent.putExtra("appid", apid);
						startActivity(browserIntent);
					
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.list_appointments_staff, menu);
		return true;
	}
	
	public void callrest()
	{
		HttpClient httpclient = new DefaultHttpClient();  
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/appointments/6/"+hospital_staff_id + "/appointment/weekly";
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
				JSONArray start_object = new JSONArray(result);
				int count=start_object.length();
				JSONObject json;
			
				long rawdate;
				Date appdate;
				SimpleDateFormat sf;
				String newappdate;
				String newtime;
				for (int i = 0; i < count; i++) {
					json = start_object.getJSONObject(i);
					
					rawdate=Long.valueOf(json.getString("appointment_date"));
					appdate=new Date(rawdate);
					sf=new SimpleDateFormat("yyyy-mm-dd");
					newappdate = sf.format(appdate);
					sf =new SimpleDateFormat("HH:mm:ss");
					newtime=sf.format(appdate);
					
					appointment.add((i+1) + ") " + "Date: "+newappdate+" Time :"+newtime);
					rawappointment.add(json.getString("appointment_date"));
					
					appointment_id.add(Integer.valueOf((json.getInt("appointment_id"))));
					
				}
				
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

}
