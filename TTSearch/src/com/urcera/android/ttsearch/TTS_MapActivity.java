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

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class TTS_MapActivity extends MapActivity{
	
	private MapView mapview = null;				// Initialize the map view
	private MapController mapControl = null;	// Initialize the map controller
	private TextView tvLocation = null;			// Initialize text view at the top of MapActivity
	private String mLatitude = null;			// Latitude from ListActivity
	private String mLongitude = null;			// Longitude from ListActivity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tts__map);
		
		tvLocation = (TextView) this.findViewById(R.id.tvMap);
		mapview = (MapView) findViewById(R.id.TTSMapView);
		String mLat = null;						// Latitude that I only show at the top of MapActivity
		String mLon = null;						// Longitude that I only show at the top of MapActivity
		
		// Retrieve the data passed by the intent from ListActivity
		
		Intent i = getIntent();					// Capture the intent that started this activity
		if (i != null)  						// Confirms if this activity was started through an intent
		{
			mLatitude = i.getStringExtra(TTS_ListActivity.MPLAT); 	// Latitude from ListActivity
			mLongitude = i.getStringExtra(TTS_ListActivity.MPLON); 	// Longitude from ListActivity

			// Assign default values if any are missing

			if (mLatitude == null)  			// Checks if mLatitude has value
			{
				mLatitude = TTS_ListActivity.LAT_DEF;	// Assigns default value to mLatitude
			}

			if (mLongitude == null)  			// Checks if mLongitude has value
			{
				mLongitude = TTS_ListActivity.LON_DEF;	// Assigns default value to mLongitude
			}
		}
		
		// I cut the strings up to 12 characters, for display only.
		
		if (mLatitude.length()>12) {
			mLat = mLatitude.substring(0, 12);
		} else {
			mLat = mLatitude;
		}
		
		if (mLongitude.length()>12) {
			mLon = mLongitude.substring(0, 12);
		} else {
			mLon = mLongitude;
		}
		
		// Writes latitude and longitude in text view at top
		
		tvLocation.setText("Lat: " + mLat + ", Long: " + mLon);	
		
		// Prints the map
		
		mapview.setBuiltInZoomControls(true); 	// To be able to zoom on the map
		mapview.setClickable(true);				// To be able to move the map
		//mapview.setSatellite(true);			// Satellite view
		
		mapControl = mapview.getController();	// To be able to use map controller
	
		GeoPoint geoPoint = new GeoPoint (		// Defines a map point 
			(int) (Double.valueOf(mLatitude) * 1000000),	// Latitude example: 40.33483  (North-South)
			(int) (Double.valueOf(mLongitude) * 1000000));	// Longitude example: -3.87397 (East-West)
		
		mapControl.setZoom(14);					// Makes a specified zoom
		mapControl.animateTo(geoPoint);			// Moves the map to geoPoint point
		
		// Prints the overlay
		
		MapOverlay myMapOver = new MapOverlay();	// Makes a map layout
		myMapOver.setDrawable(getResources().getDrawable(R.drawable.twit));	// Image to print in map
		myMapOver.setGeoPoint(geoPoint);			
		myMapOver.setTexto("Tweet");				// Text to print in map

		final List<Overlay> overlays = mapview.getOverlays();
		overlays.clear();

		overlays.add(myMapOver);
			
	}

	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
