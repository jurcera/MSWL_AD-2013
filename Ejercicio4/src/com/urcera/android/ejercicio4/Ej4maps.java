package com.urcera.android.ejercicio4;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class Ej4maps extends MapActivity {
	
	private MapView mapview = null;				// Inicializar la vista del mapa
	private MapController mapControl = null;	// Inicializar el controlador de mapas
//	private Location mLoc = null;
	private TextView tvLocation = null;
	private String mTitulo = null;
	private String mLatitud = null;
	private String mLongitud = null;
	private String mImagen = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ej4maps);
		
		tvLocation = (TextView) this.findViewById(R.id.tvLoc);
		mapview = (MapView) findViewById(R.id.MapView);
		
		// Recupero los datos pasados por el intent
		
		Intent i = getIntent();		// Captura el intent que arrancó esta actividad
		if (i != null)  			// Confirma si la actividad se arrancó a través de un intent
		{
			mTitulo = i.getStringExtra(Ej4MainActivity.TIT);	// Recoje el valor de la variable TIT pasado desde la MainActivity
			mLatitud = i.getStringExtra(Ej4MainActivity.LAT); 	// Recoje el valor de la variable LAT pasado desde la MainActivity
			mLongitud = i.getStringExtra(Ej4MainActivity.LON); 	// Recoje el valor de la variable LON pasado desde la MainActivity
			mImagen = i.getStringExtra(Ej4MainActivity.IMG); 	// Recoje el valor de la variable LON pasado desde la MainActivity

			// Asigno valores por si falta alguno
			
			if (mTitulo == null)  	// Comprueba si mTitulo tiene valor
			{
				mTitulo = "Sin título";
			}
			
			if (mLatitud == null)  	// Comprueba si mLatitud tiene valor
			{
				mLatitud = "0.0";
			}
			
			if (mLongitud == null)  // Comprueba si mLongitud tiene valor
			{
				mLongitud = "0.0";
			}
			
			if (mImagen == null)  // Comprueba si nImagen tiene valor
			{
				mImagen = "0";
			}
		}
		
		// Imprimo textviews
		
		tvLocation.setText("Latitud: " + mLatitud + ", Longitud: " + mLongitud);	// Escribe en el textView el valor de longitud recibido de la MainActivity
		
		Log.d("Ej4maps", "Tit: " + mTitulo + " Longitud: " + mTitulo.length());
		
		
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
		
		// Imprimo el overlay
		
		MapOverlay myMapOver = new MapOverlay();	// Crea una capa de mapa
		myMapOver.setDrawable(getResources().getDrawable(Integer.valueOf(mImagen)));	// 
		myMapOver.setGeoPoint(geoPoint);
		myMapOver.setTexto(mTitulo);

		final List<Overlay> overlays = mapview.getOverlays();
		overlays.clear();

		overlays.add(myMapOver);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej4maps, menu);
		return true;
	}
	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
