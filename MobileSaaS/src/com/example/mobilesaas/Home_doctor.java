package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Home_doctor extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_doctor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_doctor, menu);
        return true;
    }
}
