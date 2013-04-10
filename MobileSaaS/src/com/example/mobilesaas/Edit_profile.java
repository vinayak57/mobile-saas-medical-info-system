package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Edit_profile extends Activity {

	Button cpassword,updateprofile;
	String userid;
	String user;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        
        userid=getIntent().getExtras().getString("userid");
        user=getIntent().getExtras().getString("username");
        
        cpassword=(Button)findViewById(R.id.bChangePassword);
        updateprofile=(Button)findViewById(R.id.bUpdateProfile);
        
        cpassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),Change_password.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				startActivity(browserIntent);
			}
		});
        
        updateprofile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent browserIntent = new Intent(getApplicationContext(),Update_profile.class);
				browserIntent.putExtra("userid", userid);
				browserIntent.putExtra("username", user);
				startActivity(browserIntent);
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_profile, menu);
        return true;
    }
}
