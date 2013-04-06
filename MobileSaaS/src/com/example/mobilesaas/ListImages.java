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
import android.widget.AdapterView.OnItemClickListener;
import android.R;

public class ListImages extends ListActivity {

	//ListView listView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_list_images);
         
        
        final String[] test = new String[] { "Apple", "Avocado", "Banana",
    		"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
    		"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple","Olive", "Pear", "Sugar-apple","Olive", "Pear", "Sugar-apple" };
    	
        // make a get call from the appointment data/id/type and fetch all the Images that were taken on that images.
        // Give a simple image name and id to it and pass it to next view
        //on the next view show bigger image 
         
         
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,test));
        
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
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				}
		});
    }

}
