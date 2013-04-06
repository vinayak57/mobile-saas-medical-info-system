package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class View_appointment extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_appointment, menu);
        return true;
    }
}
