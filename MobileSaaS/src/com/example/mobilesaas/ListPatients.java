package com.example.mobilesaas;

import java.util.ArrayList;
import java.util.HashMap;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListPatients extends ListActivity {

	int userid,hospital_staff_id, code, flag = -1;
	String result,fname,lname;
	Button bSearchPatient, bSearchCancel;
	EditText etdfname,etdlname;
	List<String> patientList;//= new ArrayList<String>();
	List<String> patientRawList = new ArrayList<String>();
	HashMap<String, Integer> patientMap = new HashMap<String, Integer>();
	private static int save= -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_patients);
		patientList = new ArrayList<String>();
		
		//patientList.removeAll(patientList);
		userid=getIntent().getExtras().getInt("userid");
		fname=getIntent().getExtras().getString("fname");
		lname=getIntent().getExtras().getString("lname");
		hospital_staff_id=getIntent().getExtras().getInt("staffid");
        flag=getIntent().getExtras().getInt("flag");
        
		//Colelcti.removeAll(arg0)
		callrest(fname, lname);
		
		 this.setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, patientList));
		  
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
						Log.d("selected text" , product);
						
						int patientId = patientMap.get(product).intValue();
						
						Log.d("selected " , String.valueOf(patientId));
						
						if(flag!= -1 && flag==4)
						{
							Intent browserIntent = new Intent(getApplicationContext(),ListImagesStaff.class);
							
							browserIntent.putExtra("userid", userid);
							browserIntent.putExtra("flag",4);
							//browserIntent.putExtra("hospital_staff_id", hospital_staff_id);
							browserIntent.putExtra("patientid", patientId);
							startActivity(browserIntent);
						}
						else
						{
							Intent browserIntent = new Intent(getApplicationContext(),ListMedicalInfo.class);
							
							browserIntent.putExtra("userid", userid);
							//browserIntent.putExtra("username", user);
							//browserIntent.putExtra("hospital_staff_id", hospital_staff_id);
							browserIntent.putExtra("patientid", patientId);
							startActivity(browserIntent);
							
						}
					
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
		//callrest(fname, lname);
		
	}

	public void callrest(String fname, String lname)
	{
		HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/patients/search/6/" + fname + "/" + lname;
        
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
				//patientList.clear();
				JSONArray start_object = new JSONArray(result);
				int count=start_object.length();
				Log.d("inside code ", String.valueOf(count));
				JSONObject mainobj;
				Log.d("patients: " , String.valueOf(patientList.size()));
				for (int i = 0; i < count; i++) {
					
					mainobj=start_object.getJSONObject(i);
					Log.d("after rest", mainobj.toString());
					
					String fname1 = mainobj.getString("fname");
					String lname1 = mainobj.getString("lname");
					String email = mainobj.getString("email");
					int phone = mainobj.getInt("phone");
					
					String result="Name: " + fname1;
					result +=", Email: " + email;
					result +=", Phone: " + phone;
					
					//if(patientMap.ge)
					patientList.add(result);
					patientMap.put(result, mainobj.getInt("patientId"));
					
					Log.d("patients: " , String.valueOf(patientList.size()));
					
//					Integer drug_id = Integer.valueOf(json.getString("drugId"));
//					String name =json.getString("name");
//					String power = json.getString("power");
//					
//								
//					drugList.add("Drug: "+name+" , Power :"+power);
//					drugidList.add(drug_id);
//					drugMap.put("Drug: "+name+" , Power :"+power, drug_id);
					
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_patients, menu);
		return true;
	}

}
