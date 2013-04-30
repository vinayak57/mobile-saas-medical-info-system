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
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAppointment extends ListActivity {

	int userid,patientid;
	int code;
	String user,result;
	List<String>appointment= new ArrayList<String>();
	List<Integer>appointment_id= new ArrayList<Integer>();
	List<String>visit_type=new ArrayList<String>();
	List<String>hospital_name=new ArrayList<String>();
	List<String>staff_name=new ArrayList<String>();
	List<String>status=new ArrayList<String>();
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_view_appointment);
        
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        patientid=getIntent().getExtras().getInt("patientid");
        
        
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
		
				int i=0;
				
				try {
					
					String product = ((TextView) view).getText().toString();
					int apid=0;
					String staffname=null,hosname=null,visitype=null,stStatus=null;
					for(String s: appointment)
					{
						if(s.equalsIgnoreCase(product));
						{
							 i= appointment.indexOf(product);
							apid = appointment_id.get(i);
							visitype= visit_type.get(i);
							hosname=hospital_name.get(i);
							staffname=staff_name.get(i);
							stStatus=status.get(i);
							break;
						}
						
					}
					
					Intent browserIntent = new Intent(getApplicationContext(),AppointmentDetails.class);
					
					browserIntent.putExtra("userid", userid);
					browserIntent.putExtra("username", user);
					browserIntent.putExtra("patientid", patientid);
					browserIntent.putExtra("appid", apid);
					browserIntent.putExtra("visit", visitype);
					browserIntent.putExtra("hosname", hosname);
					browserIntent.putExtra("docname", staffname);
					browserIntent.putExtra("status", stStatus);
					startActivity(browserIntent);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
        
    }

   public void callrest()
   {
	   
	   HttpClient httpclient = new DefaultHttpClient();  
   	
       String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/appointments/6/"+patientid;
       
       HttpGet request = new HttpGet(url);
       request.addHeader("Accept","application/json");
       HttpResponse response;
		HttpEntity entity;
		try {
			
			response=httpclient.execute(request);
			entity=response.getEntity();
			result=EntityUtils.toString(entity);
			
			code=response.getStatusLine().getStatusCode();
			
			JSONObject json;
			
			if(code==200)
			{
				JSONArray start_object = new JSONArray(result);
				int count=start_object.length();
				
				long rawdate;
				
				SimpleDateFormat sf;
				String newappdate;
				
				
				for (int i = 0; i < count; i++) {
					
					json = start_object.getJSONObject(i);
					
					appointment_id.add(json.getInt("appointment_id"));
					visit_type.add(json.getString("visit_type"));
					staff_name.add(json.getString("staff_name"));
					
					hospital_name.add(json.getString("hospital_name"));
					status.add(json.getString("status"));
					
					rawdate=Long.valueOf(json.getLong("appointment_date"));
					
					Date tempdate=new Date(rawdate);
					 sf=new SimpleDateFormat("yyyy-MM-dd");
					String cdate = sf.format(tempdate);
					
					sf =new SimpleDateFormat("HH:mm:ss");
					String tdate= sf.format(tempdate);
					
					appointment.add("Date :"+cdate+" Time :"+tdate);
					
					
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
