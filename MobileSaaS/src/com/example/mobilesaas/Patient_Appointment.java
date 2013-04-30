package com.example.mobilesaas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
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

public class Patient_Appointment extends ListActivity {

	int userid,patientid;
	int code;
	String user,result;
	int hid,vid;
	
	List<String>appointment= new ArrayList<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_patient__appointment);
        
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        patientid=getIntent().getExtras().getInt("patientid");
        vid=getIntent().getExtras().getInt("vid");
        hid=getIntent().getExtras().getInt("hid");
        
        //callrest();
       
        caldate();
     
     
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
				
				try {
					String product = ((TextView) view).getText().toString();
					
					String[] tokens =product.split("Date : ");
					
					//test.setText("legth "+ tokens.length+"   "+tokens[1]+"   ");
					
					String t1[]=tokens[1].split("Time : ");
					
					String apdate= t1[0].trim()+"T"+t1[1].trim();
					
					//test.setText("first :"+t1[0]+"  sec "+ t1[1]);
					
					
					
					
					String json = "{\"visit_type_id\":\""+vid+"\",\"hospital_staff_id\":\""+hid+"\",\"location_id\":\"3\",\"appointment_date\":\""+apdate+"\",\"tenantid\":\"6\",\"patient_id\":\""+patientid+"\"}";
					
					//String json=   "{\"visit_type_id\":\""+user+"\",\"password\":\""+pass+"\",\"tenantid\": \"6\",\"roleid\":\"11\",\"patientId\": \""+patientid+"\",\"fname\": \""+getfname+"\",\"lname\":\""+getlname+"\",\"email\": \""+getemail+"\",\"gender\":\""+getgender+"\",\"phone\": \""+getphone+"\",\"addr1\":\""+getaddr1+"\",\"addr2\": \""+getaddr2+"\",\"city\":\""+getcity+"\",\"state\": \""+getstate+"\",\"country\":\"US\",\"zipcode\": \""+getzip+"\",\"dob\": \""+getdob+"\" }";
					
					HttpClient httpClient = new DefaultHttpClient();

				   
				        HttpPut request = new HttpPut("http://10.0.2.2:8080/MedicalInformationSystem/rest/appointments");
				        StringEntity params =new StringEntity(json);
				        request.addHeader("Content-Type", "application/json");
				        request.setEntity(params);
				        HttpResponse httpResponse = httpClient.execute(request);
					
				        if(httpResponse.getStatusLine().getStatusCode() == 201)
				        {
				        	Toast toast = Toast.makeText(getApplicationContext(), "Appointment is booked",
					   				 Toast.LENGTH_LONG);
					   				 toast.show();
					   				
					   				 Intent browserIntent = new Intent(getApplicationContext(),Manage_appointment.class);
									
									browserIntent.putExtra("userid", userid);
									browserIntent.putExtra("username", user);
									browserIntent.putExtra("patientid", patientid);
									//browserIntent.putExtra("appid", apid);
									startActivity(browserIntent);
				        }
				        else
				        {
				        	Toast toast = Toast.makeText(getApplicationContext(), "Some Error!!",
					   				 Toast.LENGTH_LONG);
					   				 toast.show();
				        	
				        }
				        
				   
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
     
        
    }
    
    public void caldate()
    {
    	 Calendar now = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
    	 int i=0;
    	 int j=9;
    	 //ppointment.add("Today "+now.get(Calendar.DATE));
    	 for(i=1; i<6; i++)
    	 {
    		 
    		 
    		 for(j=10;j<18;j++)
    		 {
    			 appointment.add("Date : " + now.get(Calendar.YEAR) + "-"
 			            + (now.get(Calendar.MONTH)+1) + "-" + (now.get(Calendar.DATE))+" "+ "Time : " +j+":00:00");
    		 }
    		 
    		 now.add(Calendar.DATE, 1);
    		 
    		 
    	 }
    	 
    	 
       // appointment.add(sf.format(cal.getTime()));
    }

    public void callrest()
    {
    	HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/appointments/6/"+hid+"/appointment/weekly";
        
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
				
				if(count!=0)
				{
					for (int i = 0; i < count; i++) {
						
					json = start_object.getJSONObject(i);
					
					rawdate=Long.valueOf(json.getLong("appointment_date"));
					Date tempdate=new Date(rawdate);
					 sf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					String cdate = sf.format(tempdate);
					String tdate;
					String[] tokens = cdate.split("T");

					cdate=tokens[0];
					tdate=tokens[1];
					
					//newappdate = sf.format(appdate);
					//sf =new SimpleDateFormat("HH:mm:ss");
					//newtime=sf.format(appdate);
					
					appointment.add("Date: "+cdate+" Time :"+tdate);
					//rawappointment.add(json.getString("appointment_date"));
					
					}
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
