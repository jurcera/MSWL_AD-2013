/*
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

package com.urcera.hemet;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HEmet_MainActivity extends Activity {

	private BluetoothAdapter btAdapter;
	private static final int REQUEST_ENABLE_BT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_main);

		Button bt1 = (Button) this.findViewById(R.id.butConnect);	// Assign button butBusqueda to bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 			// Create a listener for bt1 	

				@Override
				public void onClick(View v) {						// When I push bt1 ...
					// TODO Auto-generated method stub

					btAdapter = BluetoothAdapter.getDefaultAdapter();
					Toast.makeText(getBaseContext(), "Adapter: " + btAdapter, Toast.LENGTH_SHORT).show();

					CheckBTState();



				}

			} );
		}
	}

	private void CheckBTState() {
		// Check for Bluetooth support and then check to make sure it is turned on
		// If it isn't request to turn it on
		// List paired devices
		// Emulator doesn't support Bluetooth and will return null
		if(btAdapter==null) { 
			Toast.makeText(getBaseContext(), "Bluetooth NOT supported. Aborting.", Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (btAdapter.isEnabled()) {
				Toast.makeText(getBaseContext(), "Bluetooth is enabled...", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(getBaseContext(), HEmet_menu.class); 
				startActivity(intent);						// start ListActivity.
				
				// Starting the device discovery	
				// btAdapter.startDiscovery();
			} else {
				Toast.makeText(getBaseContext(), "Bluetooth is NOT enabled...", Toast.LENGTH_SHORT).show();
				Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			}
		}
	}

	/* This routine is called when an activity completes.*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_ENABLE_BT) {
			CheckBTState();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hemet__main, menu);
		return true;
	}

}
