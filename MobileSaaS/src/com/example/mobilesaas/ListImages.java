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
import android.widget.AdapterView.OnItemClickListener;
import android.R;

public class ListImages extends ListActivity {

	//ListView listView;
	
	int userid,patientid,code;
	String result;
	String user,clinicaldata,temp;
	
	String extra_image, extra_diago, extra_issue, extra_sev, extra_datec, extra_datem;
	
	List<String> xdiagno = new ArrayList<String>();
	List<String> xissue = new ArrayList<String>();
	List<String> xsev = new ArrayList<String>();
	List<String> xdatec = new ArrayList<String>();
	List<String> ximage = new ArrayList<String>();
	List<String> xdatem = new ArrayList<String>();
	List<String> display = new ArrayList<String>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_list_images);
         
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        patientid=getIntent().getExtras().getInt("patientid");
        temp=getIntent().getExtras().getString("clinicaldata");
        
        if(temp.equalsIgnoreCase("View Xrays"))
        	clinicaldata="xray";
        else if(temp.equalsIgnoreCase("View MRI scans"))
        	clinicaldata="mriscan";
        
        
         
        callrest(); 
        
        
        
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,display));
        
        //listView=(ListView)findViewById(R.id.lvTest);
        
        ListView listView= getListView();
        
		listView.setTextFilterEnabled(true);
		
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				try{
					String product = ((TextView) view).getText().toString();
					Toast toast = Toast.makeText(getApplicationContext(), product,
			   				 Toast.LENGTH_LONG);
			   				 toast.show();
			   				 
			   		int i=0;
			   		for(String s : display)
			   		{
			   			if(s.equalsIgnoreCase(product))
			   			{
			   				i=display.indexOf(product);
			   				extra_image=ximage.get(i);
			   				extra_diago=xdiagno.get(i);
			   				extra_issue=xissue.get(i);
			   				extra_sev=xsev.get(i);
			   				extra_datec=xdatec.get(i);
			   				extra_datem=xdatem.get(i);
			   				break;
			   				
			   			}
			   		}
			   		
			   		Intent browserIntent = new Intent(getApplicationContext(),Image_details.class);
					
					browserIntent.putExtra("userid", userid);
					browserIntent.putExtra("username", user);
					browserIntent.putExtra("patientid", patientid);
					browserIntent.putExtra("extra_image",extra_image );
					browserIntent.putExtra("extra_diago",extra_diago );
					browserIntent.putExtra("extra_issue",extra_issue );
					browserIntent.putExtra("extra_sev",extra_sev );
					browserIntent.putExtra("extra_datec",extra_datec );
					browserIntent.putExtra("extra_datem",extra_datem );
					browserIntent.putExtra("clinicaldata",clinicaldata );
					startActivity(browserIntent);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				}
		});
    }
    public void callrest()
    {
    	HttpClient httpclient = new DefaultHttpClient();  
    	
        String url="http://10.0.2.2:8080/MedicalInformationSystem/rest/clinicalrecords/patient/"+patientid;
        
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
				
				JSONObject mainobj;
				
				for (int i = 0; i < count; i++) {
					
					mainobj=start_object.getJSONObject(i);
	        		
					String desc=mainobj.getString("description");
					int pid= mainobj.getInt("patient_id");
					int hsid= mainobj.getInt("hospital_staff_id");
					
					String xray=mainobj.getString("xray");
					JSONArray xrayarray = new JSONArray(xray);
					int xraycount= xrayarray.length();
					
					String mriscan= mainobj.getString("mriscan");
					JSONArray mriscanarray = new JSONArray(mriscan);
					int mriscancount= mriscanarray.length();
					
					
					if(xraycount!=0 && clinicaldata.equalsIgnoreCase("xray"))
					{
					
						if(xraycount==0)
						{
							Toast toast = Toast.makeText(getApplicationContext(), "You Dont have any X-Rays",
					   				 Toast.LENGTH_LONG);
					   				 toast.show();
						}	
						else
						{
							for (int j = 0; j < xraycount; j++) {
								
								JSONObject xrayobj = xrayarray.getJSONObject(j);
								
								ximage.add(xrayobj.getString("image"));
								
								
								String attr = xrayobj.getString("attr");
								JSONObject attrobj =new JSONObject(attr);
								
								
								long rawdate=Long.valueOf(attrobj.getString("dateCreated"));
								Date tempdate=new Date(rawdate);
								SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
								String cdate = sf.format(tempdate);
								
								String[] tokens = cdate.split("T");

								cdate=tokens[0];
								
								
								xdiagno.add(attrobj.getString("diagnostic_area"));
								xissue.add(attrobj.getString("issue"));
								xsev.add(attrobj.getString("severity"));
								xdatec.add(cdate);
								xdatem.add(attrobj.getString("dateModified"));
								display.add("Date Created : "+ cdate);
								
								
							} // xray for
							
						}
					}
					else if (mriscancount!=0 && clinicaldata.equalsIgnoreCase("mriscan"))
					{
						if(mriscancount==0)
						{
							Toast toast = Toast.makeText(getApplicationContext(), "You dont have any MRI scans",
					   				 Toast.LENGTH_LONG);
					   				 toast.show();
						}
						else
						{
							for (int j = 0; j < mriscancount; j++) {
								
							}
							
							
						}
						
					}
					
					
				}//main for
				
				
				
				Toast toast = Toast.makeText(getApplicationContext(), "count " +count,
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
				
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


