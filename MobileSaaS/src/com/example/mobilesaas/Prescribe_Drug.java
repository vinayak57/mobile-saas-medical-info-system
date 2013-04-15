package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Prescribe_Drug extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescribe__drug);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prescribe__drug, menu);
		return true;
	}

}
