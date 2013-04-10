package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Personal_hostory extends Activity {

	TextView blood,datablood,height,dataheight,weight,dataweight;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_hostory);
        
        blood=(TextView)findViewById(R.id.tvBlood);
        datablood=(TextView)findViewById(R.id.tvDataBlood);
        height=(TextView)findViewById(R.id.tvHeight);
        dataheight=(TextView)findViewById(R.id.tvDataHeight);
        weight=(TextView)findViewById(R.id.tvWeight);
        dataweight=(TextView)findViewById(R.id.tvDataWeight);
        
        
        datablood.setText("");
        dataweight.setText("");
        dataheight.setText("");
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_personal_hostory, menu);
        return true;
    }
}
