package com.urcera.android.ejercicio4;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
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
	private TextView tvLatitude = null;
	private TextView tvLongitude = null;
	private String mTitulo = null;
	private String mLatitud = null;
	private String mLongitud = null;
	private String mImagen = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ej4maps);
		
		tvLatitude = (TextView) this.findViewById(R.id.tvLat);
		tvLongitude = (TextView) this.findViewById(R.id.tvLon);
		mapview = (MapView) findViewById(R.id.MapView);
		
		
		Intent i = getIntent();		// Captura el intent que arrancó esta actividad
		if (i != null)  			// Confirma si la actividad se arrancó a través de un intent
		{
			mTitulo = i.getStringExtra(Ej4MainActivity.TIT);	// Recoje el valor de la variable TIT pasado desde la MainActivity
			mLatitud = i.getStringExtra(Ej4MainActivity.LAT); 	// Recoje el valor de la variable LAT pasado desde la MainActivity
			mLongitud = i.getStringExtra(Ej4MainActivity.LON); 	// Recoje el valor de la variable LON pasado desde la MainActivity
			mImagen = i.getStringExtra(Ej4MainActivity.IMG); 	// Recoje el valor de la variable LON pasado desde la MainActivity
	
			if (mTitulo != null)  	// Comprueba que mTitulo tiene valor
			{
				//tvLatitude.setText("Latitud: " + mLatitud);		// Escribe en el textView el valor de TITLE recibido de la MainActivity
			} else {
				mTitulo = "Sin título";
			}
			
			if (mLatitud != null)  	// Comprueba que mLatitud tiene valor
			{
				tvLatitude.setText("Latitud: " + mLatitud);		// Escribe en el textView el valor de latitud recibido de la MainActivity
			} else {
				mLatitud = "0.0";
			}
			
			if (mLongitud != null)  // Comprueba que mLongitud tiene valor
			{
				tvLongitude.setText("Longitud: " + mLongitud);	// Escribe en el textView el valor de longitud recibido de la MainActivity
			} else {
				mLongitud = "0.0";
			}
			
			if (mImagen != null)  // Comprueba que nImagen tiene valor
			{
				// tvLongitude.setText("Longitud: " + mLongitud);	// Escribe en el textView el valor de longitud recibido de la MainActivity
			} else {
				mImagen = "0";
			}
			
			
		}
		
		//mLatitud = "0.0";
		//mLongitud = "0.0";
		
		mapview.setBuiltInZoomControls(true); 	// mover mapa y poder hacer zoom
		mapview.setClickable(true);				// para poder mover el mapa
		
		mapControl = mapview.getController();	// para poder usar el controlador de mapas
	
		GeoPoint geoPoint = new GeoPoint (		// Definimos un punto del mapa
			(int) (Double.valueOf(mLatitud) * 1000000),		// Latitud 40.33483  (Norte-Sur)
			(int) (Double.valueOf(mLongitud) * 1000000));	// Longitud -3.87397 (Este-Oeste)
		
		mapControl.setZoom(13);					// Hace un determinado zoom
		mapControl.animateTo(geoPoint);			// Mueve el mapa al punto indicado con geoPoint
		
		MapOverlay myMapOver = new MapOverlay();
		myMapOver.setDrawable(getResources().getDrawable(Integer.valueOf(mImagen)));
		myMapOver.setGeoPoint(geoPoint);
		myMapOver.setTexto(mTitulo);

		final List<Overlay> overlays = mapview.getOverlays();
		overlays.clear();

		overlays.add(myMapOver);
		
		
	}
	
	/**************
	private void refreshMap()
    {
    	
    	if (mLoc == null)
    	{
    		Toast.makeText(getBaseContext(),
                    "Location not available!",
                    Toast.LENGTH_LONG).show();
    		
    		return;
    	}
    
		
        GeoPoint geoPoint = new GeoPoint ( (int) (Double.valueOf(mLatitud) * 1000000),
				                           (int) (Double.valueOf(mLongitud) * 1000000));

        
        
        mapControl.setZoom(18);
		mapControl.animateTo(geoPoint);



		MapOverlay myMapOver = new MapOverlay();
		myMapOver.setDrawable(getResources().getDrawable(R.drawable.drawingpin));
		myMapOver.setGeoPoint(geoPoint);

		final List<Overlay> overlays = mapview.getOverlays();
		overlays.clear();

		overlays.add(myMapOver);


		//mapview.setSatellite(true);
		//mapview.setBuiltInZoomControls(true);


		mapview.setClickable(true);
    	

		tvlocation.setText("Your Current Location: \n" + 
				           String.valueOf(mLoc.getLatitude()) + " , " +
				           String.valueOf(mLoc.getLongitude()));

    	
    } ************/

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
