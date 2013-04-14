package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class View_data extends Activity {

	String data,title,user;
	int userid,patientid;
	TextView tvTitle, tvData;
	Button back;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        
        data=getIntent().getExtras().getString("data");
        title=getIntent().getExtras().getString("title");
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        patientid=getIntent().getExtras().getInt("patientid");
        
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        tvData=(TextView)findViewById(R.id.tvDisplay);
        back=(Button)findViewById(R.id.bBack);
        
        
        tvTitle.setText(title.toString());
        tvData.setText(data.toString());
        
        back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),View_medical_history.class);
				
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				browserIntent.putExtra("patientid", patientid);
				startActivity(browserIntent);
			}
		});
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_data, menu);
        return true;
    }
}
