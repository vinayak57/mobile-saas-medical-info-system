package com.example.mobilesaas;

import java.util.Calendar;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseTimestamp extends FragmentActivity {

	EditText timestamp1,timestamp2;
	Button next;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_timestamp);
    
    timestamp1=(EditText)findViewById(R.id.etTime1);
    timestamp2=(EditText)findViewById(R.id.etTime2);

    next=(Button)findViewById(R.id.bTimeStampNext);
        
    next.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(timestamp1.getText().toString().equalsIgnoreCase("")|| timestamp2.getText().toString().equalsIgnoreCase(""))
			{
				Toast toast = Toast.makeText(getApplicationContext(), "Choose dates first",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
			}
			else{
			Intent browserIntent = new Intent(getApplicationContext(),ListImagesStaff.class);
			
			
			browserIntent.putExtra("timestamp1",timestamp1.getText().toString() );
			browserIntent.putExtra("flag",3);
			browserIntent.putExtra("timestamp2",timestamp2.getText().toString() );
			
			startActivity(browserIntent);
			}
		}
	});
        
        
    }

    public void selectDate(View view) {
		DialogFragment newFragment = new SelectDateFragment();
		newFragment.show(getSupportFragmentManager(), "DatePicker");
	}

	public void populateSetDate(int year, int month, int day) {
		timestamp1 = (EditText) findViewById(R.id.etTime1);
		timestamp1.setText(month + "/" + day + "/" + year);
		
	}
	public class SelectDateFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}

		public void onDateSet(DatePicker view, int yy, int mm, int dd) {
			populateSetDate(yy, mm + 1, dd);
		}
	}
  
	
	public void selectDate1(View view) {
		DialogFragment newFragment = new SelectDateFragment1();
		newFragment.show(getSupportFragmentManager(), "DatePicker");
	}

	public void populateSetDate1(int year, int month, int day) {
		
		timestamp2 = (EditText) findViewById(R.id.etTime2);
		timestamp2.setText(month + "/" + day + "/" + year);
	}
	public class SelectDateFragment1 extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
		}

		public void onDateSet(DatePicker view, int yy, int mm, int dd) {
			populateSetDate1(yy, mm + 1, dd);
		}
	}
	
	
	
	
	
  
}
