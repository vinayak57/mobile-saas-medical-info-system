package com.example.mobilesaas;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Menu;
import android.widget.ImageView;

public class ViewImage extends Activity {

	String user, extra_image;
	int userid,patientid;
	ImageView image;
	Bitmap bmp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        
        user=getIntent().getExtras().getString("username");
        userid=getIntent().getExtras().getInt("userid");
        patientid=getIntent().getExtras().getInt("patientid");
        
        extra_image=getIntent().getExtras().getString("extra_image");
        
        
        byte[] barray=Base64.decode(extra_image,Base64.DEFAULT);
        
        
        bmp=BitmapFactory.decodeByteArray(barray,0,barray.length);
        
        //Bitmap myBitmap = BitmapFactory.dedecodeFile(bmp);
        
        image=(ImageView)findViewById(R.id.ivClinicalImage);
        image.setImageBitmap(bmp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_image, menu);
        return true;
    }
}
