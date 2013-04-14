package com.example.mobilesaas;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class Map extends MapActivity implements LocationListener {

	private MapView mapView;
	private MapController mapController;
	private String provider;
	private LocationManager locationManager;
	private Location location;
	String user;
	int userid;
	String add="";

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// Set the mapview to show the google maps
		setContentView(R.layout.activity_map);
		mapView = (MapView) findViewById(R.id.mvGoogle);
		mapView.setBuiltInZoomControls(true);

		// set controls such as zoom
		mapController = mapView.getController();
		mapController.setZoom(16);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// get the location by using locationa manager
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
		} else {
			Toast.makeText(this, "location not available", Toast.LENGTH_SHORT)
					.show();
		}

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

		// Set the longitude and lattitude in the GeoPoint for further use.
		GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6),
				(int) (location.getLongitude() * 1E6));

		Geocoder geoCoder =new Geocoder(getBaseContext(),Locale.getDefault());
		try
		{
			List<Address> addresses =geoCoder.getFromLocation(point.getLatitudeE6()/ 1E6, point.getLongitudeE6() / 1E6, 1);
			
			if (addresses.size() > 0) 
            {
                for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
                     i++)
                   add += addresses.get(0).getAddressLine(i) + "\n";
            }

            Toast.makeText(getBaseContext(), add, Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// Display the longitude and lattitude on the screen using Toast.
		Toast.makeText(
				getBaseContext(),
				"Latitude: " + location.getLatitude() + " Longitude: "
						+ location.getLongitude(), Toast.LENGTH_SHORT).show();

		
		user=getIntent().getExtras().getString("username");
		userid=getIntent().getExtras().getInt("userid");
		
			
		String json="{\"userid\":\""+userid+"\",\"tenantid\": \"6\",\"emergency_location\":\""+add+"\",\"longitude\":\""+location.getLongitude()+"\",\"lattitude\":\""+location.getLatitude()+"\" }";
		
		HttpClient httpClient = new DefaultHttpClient();

	    try {
	        HttpPut request = new HttpPut("http://10.0.2.2:8080/MedicalInformationSystem/rest/emergency/6");
	        StringEntity params =new StringEntity(json);
	        request.addHeader("Content-Type", "application/json");
	        request.setEntity(params);
	        HttpResponse httpResponse = httpClient.execute(request);
		
	        if(httpResponse.getStatusLine().getStatusCode() == 201)
	        {
	        	Toast toast = Toast.makeText(getApplicationContext(), "Medical Care is on the way",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
				/*Intent browserIntent = new Intent(getApplicationContext(), Login.class);
				//browserIntent.putExtra("username", uname);
				startActivity(browserIntent);*/
	        }
	        else
	        {
	        	Toast toast = Toast.makeText(getApplicationContext(), "Some Error!!",
		   				 Toast.LENGTH_LONG);
		   				 toast.show();
	        	
	        }
	        
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }

		mapController.animateTo(point);
		mapController.setZoom(16);
		mapView.invalidate();

		// Call the customized overlay class to place the marker.
		MyOverlay myOverlay = new MyOverlay(this);
		myOverlay.setPointToDraw(point);
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		listOfOverlays.add(myOverlay);

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}
}