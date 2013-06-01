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
		String mLat = null;
		String mLon = null;
				
		// Recupero los datos pasados por el intent
		
		Intent i = getIntent();		// Captura el intent que arrancó esta actividad
		if (i != null)  			// Confirma si la actividad se arrancó a través de un intent
		{
			mLatitud = i.getStringExtra(TTS_ListActivity.MPLAT); 	// Recoje el valor de la variable LAT pasado desde la MainActivity
			mLongitud = i.getStringExtra(TTS_ListActivity.MPLON); 	// Recoje el valor de la variable LON pasado desde la MainActivity

			// Asigno valores por si falta alguno

			if (mLatitud == null)  	// Comprueba si mLatitud tiene valor
			{
				mLatitud = TTS_ListActivity.LAT_DEF;
			}

			if (mLongitud == null)  // Comprueba si mLongitud tiene valor
			{
				mLongitud = TTS_ListActivity.LON_DEF;
			}
		}
		
		// Escribo el textview con la latitud y la longitud
		
		// Recorto las cadenas a un máximo de 12 caracteres
		
		if (mLatitud.length()>12) {
			mLat = mLatitud.substring(0, 12);
		} else {
			mLat = mLatitud;
		}
		
		if (mLongitud.length()>12) {
			mLon = mLongitud.substring(0, 12);
		} else {
			mLon = mLongitud;
		}
		
		tvLocation.setText("Lat: " + mLat + ", Long: " + mLon);	// Escribe en el textView el valor de longitud
		
		
		
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
		myMapOver.setDrawable(getResources().getDrawable(R.drawable.twit));
		myMapOver.setGeoPoint(geoPoint);
		myMapOver.setTexto("Tweet");

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
