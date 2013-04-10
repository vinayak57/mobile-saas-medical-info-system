package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class View_appointment extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_appointment);
        
        
        

        final String[] test = new String[] { "Apple", "Avocado", "Banana",
    		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
    		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple","Olive", "Pear", "Sugar-apple","Olive", "Pear", "Sugar-apple" };
        
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,test));
        
        

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
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				}
		});
        
        
    }

    
}
