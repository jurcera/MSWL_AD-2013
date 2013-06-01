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
		Toast.makeText(getBaseContext(), "Proveedor de posición: " + myProvider, Toast.LENGTH_SHORT).show();
		
				
        Button bt1 = (Button) this.findViewById(R.id.butBusqueda);	// Asignamos el botón butBusqueda a bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 			// Crea un listener para bt1 	
				
				@Override
				public void onClick(View v) {						// Cuando se pulsa bt1 ...
					// TODO Auto-generated method stub
					
					data = edText.getText().toString();				// Obtengo el valor del EditText
					data = data.replace(" ", "+");					// Sustituyo espacios por + en la cadena de búsqueda
					
					// Toast.makeText(getBaseContext(), data, Toast.LENGTH_SHORT).show();
										
					if (data.equals("")) {							// Compruebo que no está vacío
						Toast.makeText(getBaseContext(), "Introducir un término a buscar", Toast.LENGTH_SHORT).show();
					} else {
						
						
						// Comprobar si hay localización GPS válida
						
						if (myLatitud == null || myLongitud == null) {
							Toast.makeText(getBaseContext(), "Sin localización GPS válida\nusando localización por defecto", Toast.LENGTH_SHORT).show();
						}
						
						// Definimos donde estamos (getBaseContext()) y a donde vamos (TTS_ListActivity)
						
						Intent intent = new Intent(getBaseContext(), TTS_ListActivity.class); 
						intent.putExtra(DATA, data);				// Pasa la query a la ListActivity
						intent.putExtra(LAT, myLatitud);			// Pasa la latitud de la posición del móvil
						intent.putExtra(LON, myLongitud);			// Pasa la longitud de la posición del móvil
						startActivity(intent);						// ... arranca la actividad TTS_ListActivity.
						
					}
					
				}
			} );
		}
		
	}
	
	// Métodos de control del ciclo de vida de la aplicación
	
    @Override    
    protected void onResume() {
          super.onResume();
          // Activamos notificaciones de localización
          myLocMgr.requestLocationUpdates(myProvider, 10000, 15, this); // Envía localización cada 10000 ms o cada 15 m
         
    }

    @Override    
    protected void onPause() {
          super.onPause();
          // Desactivamos notificaciones de localización para ahorrar batería
          myLocMgr.removeUpdates(this);
    }

    // Métodos de la interfaz LocationListener

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
