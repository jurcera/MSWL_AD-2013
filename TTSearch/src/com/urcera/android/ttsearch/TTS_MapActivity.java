package com.urcera.android.ttsearch;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class TTS_MapActivity extends MapActivity{
	
	private MapView mapview = null;				// Inicializar la vista del mapa
	private MapController mapControl = null;	// Inicializar el controlador de mapas
	private TextView tvLocation = null;
	private String mLatitud = null;
	private String mLongitud = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tts__map);
		
		tvLocation = (TextView) this.findViewById(R.id.tvMap);
		mapview = (MapView) findViewById(R.id.TTSMapView);
		
		// Recupero los datos pasados por el intent
		
		Intent i = getIntent();		// Captura el intent que arrancó esta actividad
		if (i != null)  			// Confirma si la actividad se arrancó a través de un intent
		{
			mLatitud = i.getStringExtra(TTS_ListActivity.MPLAT); 	// Recoje el valor de la variable LAT pasado desde la MainActivity
			mLongitud = i.getStringExtra(TTS_ListActivity.MPLON); 	// Recoje el valor de la variable LON pasado desde la MainActivity

			// Asigno valores por si falta alguno

			if (mLatitud == null)  	// Comprueba si mLatitud tiene valor
			{
				mLatitud = "0.0";
			}

			if (mLongitud == null)  // Comprueba si mLongitud tiene valor
			{
				mLongitud = "0.0";
			}
		}
		
		// Escribo el textview con la latitud y la longitud
		
		tvLocation.setText("Latitud: " + mLatitud + ", Longitud: " + mLongitud);	// Escribe en el textView el valor de longitud
		
		
		
		// Imprimo el mapa
		
		mapview.setBuiltInZoomControls(true); 	// mover poder hacer zoom
		mapview.setClickable(true);				// para poder mover el mapa
		//mapview.setSatellite(true);			// para ver vista de satélite
		
		mapControl = mapview.getController();	// para poder usar el controlador de mapas
	
		GeoPoint geoPoint = new GeoPoint (		// Definimos un punto del mapa
			(int) (Double.valueOf(mLatitud) * 1000000),		// Latitud 40.33483  (Norte-Sur)
			(int) (Double.valueOf(mLongitud) * 1000000));	// Longitud -3.87397 (Este-Oeste)
		
		mapControl.setZoom(14);					// Hace un determinado zoom
		mapControl.animateTo(geoPoint);			// Mueve el mapa al punto indicado con geoPoint
		
		

		
	}

	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
