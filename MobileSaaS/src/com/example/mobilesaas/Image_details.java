package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Image_details extends Activity {

	TextView imagename,diago,issue,sev,cdate,mdate,labname,labstaff;
	Button viewimage;
	int userid,patientid;
	String user;
	String clinicaldata;
	String extra_image, extra_diago, extra_sev, extra_issue, extra_datec, extra_datem,extra_lab,extra_labstaff;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        patientid=getIntent().getExtras().getInt("patientid");
        clinicaldata=getIntent().getExtras().getString("clinicaldata");
        extra_image=getIntent().getExtras().getString("extra_image");
        extra_diago=getIntent().getExtras().getString("extra_diago");
        extra_issue=getIntent().getExtras().getString("extra_issue");
        extra_sev=getIntent().getExtras().getString("extra_sev");
        extra_datec=getIntent().getExtras().getString("extra_datec");
        extra_datem=getIntent().getExtras().getString("extra_datem");
        extra_lab=getIntent().getExtras().getString("extra_lab");
        extra_labstaff=getIntent().getExtras().getString("extra_labstaff");
        
        
        
        imagename=(TextView)findViewById(R.id.tvDataImageName);
        diago=(TextView)findViewById(R.id.tvDataImageDiago);
        issue=(TextView)findViewById(R.id.tvDataImageIssue);
        sev=(TextView)findViewById(R.id.tvDataImageSev);
        cdate=(TextView)findViewById(R.id.tvDataImageCDate);
        mdate=(TextView)findViewById(R.id.tvDataImageMDate);
        labname=(TextView)findViewById(R.id.tvDataImageLabname);
        labstaff=(TextView)findViewById(R.id.tvDataImageLabstaff);
        
        viewimage=(Button)findViewById(R.id.bViewImage);
        
        
        
        imagename.setText(clinicaldata);
        labname.setText(extra_lab);
        labstaff.setText(extra_labstaff);
        diago.setText(extra_diago);
        issue.setText(extra_issue);
        sev.setText(extra_sev);
        cdate.setText(extra_datec);
        if(extra_datem.equalsIgnoreCase("null"))
        	mdate.setText("None");
        	else
        	mdate.setText(extra_datem);
        
        
        
        viewimage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent browserIntent = new Intent(getApplicationContext(),ViewImage.class);
				
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				browserIntent.putExtra("extra_image",extra_image );
				startActivity(browserIntent);
			}
		});
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_image_details, menu);
        return true;
    }
}
