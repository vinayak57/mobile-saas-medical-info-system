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

public class ListPrescription extends ListActivity {

	int userid,patientid,code,app_id;
	
	String user,result;
	String extra_start_date,extra_end_date,extra_dose,extra_instruction;
	int extra_drug_id;
	private static int save = -1;
	
	List<String> start_date = new ArrayList<String>();
	List<String> end_date =new ArrayList<String>();
	List<String> instruction= new ArrayList<String>();
	List<String> dose= new ArrayList<String>();
	List<Integer> drug_id= new ArrayList<Integer>();
	List<String> display = new ArrayList<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_list_appointments);
    
        userid=getIntent().getExtras().getInt("userid");
        user=getIntent().getExtras().getString("username");
        patientid=getIntent().getExtras().getInt("patientid");
        app_id=getIntent().getExtras().getInt("appid");
        
        
        
        callrest(); 
		 
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, display));

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
					//parent.getChildAt(position).setBackgroundColor(Color.GREEN);
				}
				save=position;
				
				int apid=0, i=0;
				try {
					String product = ((TextView) view).getText().toString();
					for(String s: display)
					{
						if(s.equalsIgnoreCase(product));
						{
							 i= display.indexOf(product);
							 extra_dose=dose.get(i);
							 extra_end_date=end_date.get(i);
							 extra_start_date=start_date.get(i);
							 extra_instruction=instruction.get(i);
							 extra_drug_id=drug_id.get(i);
							
							break;
						}
						
					}
					Intent browserIntent = new Intent(getApplicationContext(),View_prescription_patient.class);
					
					browserIntent.putExtra("userid", userid);
					browserIntent.putExtra("username", user);
					browserIntent.putExtra("patientid", patientid);
					browserIntent.putExtra("appid", apid);
					browserIntent.putExtra("extra_dose", extra_dose);
					browserIntent.putExtra("extra_end_date", extra_end_date);
					browserIntent.putExtra("extra_start_date", extra_start_date);
					browserIntent.putExtra("extra_instruction", extra_instruction);
					browserIntent.putExtra("extra_drug_id", extra_drug_id);
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
	    	
	        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/prescription/6/"+app_id+"/appointment";
	        
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
			   		SimpleDateFormat sf;
			   		Date tempdate;
			   		String sdate,edate;
			   		String presc=null;
			   		
					for (int i = 0; i < count; i++) {
						
						json = start_object.getJSONObject(i);
						
						//json.getInt("prescription_id");
						
						drug_id.add(json.getInt("drug_id"));
						
					
						
						dose.add(json.getString("dose"));
						instruction.add(json.getString("instruction"));
						
						rawdate=Long.valueOf(json.getString("start_date"));
						Log.d("rawdate", String.valueOf(rawdate));
						tempdate=new Date(rawdate);
						sf=new SimpleDateFormat("yyyy-MM-dd");
						sdate = sf.format(tempdate);
						Log.d("start", sdate);
						start_date.add(sdate);
						
						rawdate=Long.valueOf(json.getString("end_date"));
						tempdate=new Date(rawdate);
						sf=new SimpleDateFormat("yyyy-MM-dd");
						edate = sf.format(tempdate);
						
						end_date.add(edate);
					
						presc="Prescription "+(i+1);
						
						display.add(presc);
						
						
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
