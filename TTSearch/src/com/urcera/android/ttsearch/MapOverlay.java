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
			
			final int intrinsicWidth = mMarker.getIntrinsicWidth();		// Ancho de la imagen fija de 50 puntos
			final int intrinsicHeight = mMarker.getIntrinsicHeight();	// Alto de la imagen fija de 50 puntos
			mMarker.setBounds(0, 0, intrinsicWidth, intrinsicHeight);			// Rectángulo donde meter la imagen
																				// (izquiera, arriba, derecha, abajo)

			mMarkerXOffset = (intrinsicWidth / 2);
			mMarkerYOffset = (intrinsicHeight / 2);

			Paint paint = new Paint();
			paint.setARGB(250,255,0,0);		// Transparencia y Color del texto
			paint.setAntiAlias(true);		// Suavizar bordes
			paint.setFakeBoldText(true);	// Negrita
			paint.setTextSize(22);			// Tamaño del texto
			
			Point point = new Point();
			Projection proy = mapView.getProjection();	// Creo una proyección
			proy.toPixels(mGeoPoint, point);			// Convierte las coordenadas del mapa en pixels de la imagen
     		
			canvas.drawText(mText, (float) (point.x - mMarkerXOffset * 6.0), (float) (point.y + (mMarkerYOffset * 5.6)) , paint);	// escribe el texto
			//canvas.drawText(mText, (float) (point.x - (mText.length() * 5)), (float) (point.y + (mMarkerYOffset * 5.6)) , paint);	// escribe el texto
			
			// El cálculo del offset del eje x ha sido de forma empírica. He visto que de media 8 caracteres del texto actual ocupan 50 pixel
			// con lo que 1 caracter son 6 pixel y como está centrado divido por dos, con lo que multiplico el número de caracteres por 3 
			// para el cálculo aproximado. He probado en varias resoluciones de pantalla y el resultado es similar.
			
			super.draw(canvas, mapView, shadow);
			drawAt(canvas, mMarker, point.x - mMarkerXOffset, point.y - mMarkerYOffset, shadow);
		}
	}
}
