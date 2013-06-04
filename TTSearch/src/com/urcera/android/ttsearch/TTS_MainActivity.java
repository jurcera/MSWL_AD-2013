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

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TTS_MainActivity extends Activity implements LocationListener {
	
	public static String DATA = "DATA_TO_SEARCH";
	public static String LAT = "LATITUD";
	public static String LON = "LONGITUD";
	public String data = null;
	private LocationManager myLocMgr;
	private String myProvider;
	private String myLatitud;
	private String myLongitud;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tts__main);
		
		final EditText edText = (EditText)findViewById(R.id.edtBusqueda);
		
	
		myLocMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		myProvider = myLocMgr.getBestProvider(criteria, true);
		Toast.makeText(getBaseContext(), getString(R.string.location_provider)+ " " + myProvider, Toast.LENGTH_SHORT).show();
		
				
        Button bt1 = (Button) this.findViewById(R.id.butBusqueda);	// Assign button butBusqueda to bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 			// Create a listener for bt1 	
				
				@Override
				public void onClick(View v) {						// When I push bt1 ...
					// TODO Auto-generated method stub
					
					data = edText.getText().toString();				// Read EditText data
					data = data.replace(" ", "+");					// Replace spaces for + in query
					
					if (data.equals("")) {							// Is query empty?
						Toast.makeText(getBaseContext(), R.string.empty_query, Toast.LENGTH_SHORT).show();
					} else {
						
						// Is there GPS valid location?
						
						if (myLatitud == null || myLongitud == null) {
							Toast.makeText(getBaseContext(), R.string.no_gps_location, Toast.LENGTH_SHORT).show();
						}
						
						// Jump to ListActivity
						
						Intent intent = new Intent(getBaseContext(), TTS_ListActivity.class); 
						intent.putExtra(DATA, data);				// Send query to ListActivity
						intent.putExtra(LAT, myLatitud);			// Send latitude location to ListActivity
						intent.putExtra(LON, myLongitud);			// Send longitude location to ListActivity 
						startActivity(intent);						// start ListActivity.
						
					}
					
				}
			} );
		}
		
	}
	
    @Override    
    protected void onResume() {
          super.onResume();
          // Enable location notifications
          myLocMgr.requestLocationUpdates(myProvider, 10000, 15, this); // Send location every 10000 ms or 15 m
         
    }

    @Override    
    protected void onPause() {
          super.onPause();
          // Disable location notifications to save battery
          myLocMgr.removeUpdates(this);
    }

    // LocationListener interface methods

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		myLatitud = String.valueOf(location.getLatitude());
        myLongitud = String.valueOf(location.getLongitude());
        Toast.makeText(getBaseContext(), "Loc: " + myLatitud + "," + myLongitud, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tts__main, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
		}
		return false;
	}


}
