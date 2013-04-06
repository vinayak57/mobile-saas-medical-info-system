package com.example.mobilesaas;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

class MyOverlay extends Overlay {
	private GeoPoint pointToDraw;
	private Context context;

	public MyOverlay(Context context) {
		this.context = context;
	}

	public void setPointToDraw(GeoPoint point) {
		pointToDraw = point;
	}

	public GeoPoint getPointToDraw() {
		return pointToDraw;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
			long when) {
		super.draw(canvas, mapView, shadow);

		// get the point
		Point screenPts = new Point();
		mapView.getProjection().toPixels(pointToDraw, screenPts);

		// add marker on the map
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.red);
		canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 24, null);
		return true;
	}
}
