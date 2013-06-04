/*
 *
 *  Copyright (C)2013, Jesus Urcera Lopez
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/. 
 *
 *  Author : Jesus Urcera Lopez <jurcera at gmail dot com>
 *
 */

package com.urcera.android.ttsearch;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapOverlay extends Overlay {
	
	private Drawable mMarker;
	private int mMarkerXOffset;
	private int mMarkerYOffset;
	private GeoPoint mGeoPoint;
	private String mText;


	public void setDrawable(Drawable draw) {
		mMarker = draw;
	}
	
	public void setGeoPoint(GeoPoint geoPoint) {
		mGeoPoint = geoPoint;			   
	}

	public void setTexto(String text) {
		mText = text;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		if (!shadow) {
			// Make sure to give mMarker bounds so it will draw in the overlay
			
			final int intrinsicWidth = mMarker.getIntrinsicWidth();		// Image width
			final int intrinsicHeight = mMarker.getIntrinsicHeight();	// Image height
			mMarker.setBounds(0, 0, intrinsicWidth, intrinsicHeight);	// Rectangle where to put the image
																		// (left, top, right, bottom)

			mMarkerXOffset = (intrinsicWidth / 2);
			mMarkerYOffset = (intrinsicHeight / 2);

			Paint paint = new Paint();
			paint.setARGB(250,255,0,0);		// Transparency y text color
			paint.setAntiAlias(true);		// Smooth edges
			paint.setFakeBoldText(true);	// Bold face
			paint.setTextSize(22);			// Text size
			
			Point point = new Point();
			Projection proy = mapView.getProjection();	// Create a projection
			proy.toPixels(mGeoPoint, point);			// Converts map coordinates in pixels of the image
     		
			// Write the text
			canvas.drawText(mText, (float) (point.x - mMarkerXOffset * 6.0), (float) (point.y + (mMarkerYOffset * 5.6)) , paint);	
			
			// Draw the image
			super.draw(canvas, mapView, shadow);
			drawAt(canvas, mMarker, point.x - mMarkerXOffset, point.y - mMarkerYOffset, shadow);
		}
	}
}
