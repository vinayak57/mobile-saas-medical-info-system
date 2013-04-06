package com.example.mobilesaas;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
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

		// Display the longitude and lattitude on the screen using Toast.
		Toast.makeText(
				getBaseContext(),
				"Latitude: " + location.getLatitude() + " Longitude: "
						+ location.getLongitude(), Toast.LENGTH_SHORT).show();

		//rest calls.
		
		
		
		
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