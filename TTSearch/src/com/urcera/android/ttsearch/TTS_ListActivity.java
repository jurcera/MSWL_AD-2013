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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TTS_ListActivity extends ListActivity  {

	public static String MPLAT = "MPLATITUD";			// To pass latitude to map activity
	public static String MPLON = "MPLONGITUD";			// To pass longitude to map activity
	public static String LAT_DEF = "42.5623502994";		// Default latitude
	public static String LON_DEF = "-8.8257980346";		// Default longitude
	
	ArrayList<HashMap<String,String>> Twits; 			// Array to store tweets
	String[] from=new String[] {"Index","Name","Date","Message","Location"};		// What data we store each tweet
	int[] to=new int[]{R.id.index,R.id.name,R.id.date,R.id.message,R.id.location}; 	// Where does each data in xml layout
	
	String Url = null;					// Complete url to make de search
	String urlSearch = null;			// Base url to make the search (twitter API)
	String query = null;				// Query
	String latitude = null;				// Search range center (latitude)
	String longitude = null;			// Search range center (longitude)
	String gpsLatitude = null;			// Latitude from GPS/network service
	String gpsLongitude = null;			// Longitude from GPS/network service
	String range = null;				// Search range value
	String units = null;				// Search range measure units 
	String rpp = null;					// Maximum results per query
	String mapLatitude = null;			// Tweet latitude on the map 
	String mapLongitude = null;			// Tweet longitude on the map
	boolean locManual = true;			// Manual/automatic location
	ArrayList<String[]> list = new ArrayList<String[]>();
	//String readJSON = null;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
			
		// Retrieve the data passed by the intent from MainActivity
		
		Intent intent = getIntent();		// Capture the intent that started this activity
		if (intent != null)  				// Confirms if this activity was started through an intent
		{
			String data = intent.getStringExtra(TTS_MainActivity.DATA); 	// Data from MainActivity
			if (data != null)  				// Checks if data has value
			{
				query = data;				// Assigns to query the data value received from MainActivity
			}
			
			String myLatitude = intent.getStringExtra(TTS_MainActivity.LAT); // Latitude from MainActivity
			if (myLatitude != null)  		// Checks if myLatitude has value
			{
				gpsLatitude = myLatitude;	// Assigns to gpsLatitude the value received from MainActivity
			} else {
				gpsLatitude = LAT_DEF;		// Assigns to gpsLatitude a default value
			}
			
			String myLongitude = intent.getStringExtra(TTS_MainActivity.LON); // Longitude from MainActivity
			if (myLongitude != null)  		// Checks if myLongitude has value
			{
				gpsLongitude = myLongitude;	// Assigns to gpsLongitude the value received from MainActivity
			} else {
				gpsLongitude = LON_DEF;		// Assigns to gpsLongitude a default value
			}
		}
		
		// >>>>>>>>>> Para que funcione en el dispositivo móvil hasta que no habilite el asynctask <<<<<<<<<<<<
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
		//StrictMode.setThreadPolicy(policy);
	
		dialog = new ProgressDialog(this);
	    dialog.setTitle("Progreso");
	    dialog.setMessage("Descargando ...");
	    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    dialog.setCancelable(true);
		
		new bgTask().execute();
	
	}


	// Download JSON from Twitter API
	
	public String JSONFeed() {
				
		urlSearch = Prefs.getUrlSearch(getBaseContext());	// Read the url to make the search from preferences
		locManual = Prefs.getlocManual(getBaseContext());	// Read the location method manual/authomatic from preferences
		
		if (locManual) {
			latitude = Prefs.getLatitud(getBaseContext());	// Read the latitude from preferences
			longitude = Prefs.getLongitud(getBaseContext());// Read the longitude from preferences
		} else {
			latitude = gpsLatitude;							// Use latitude from GPS
			longitude = gpsLongitude;						// Use longitude from GPS
		}
		
		range = Prefs.getDistancia(getBaseContext());		// Read range from preferences
		units = Prefs.getUnidades(getBaseContext());		// Read units (km or mi) from preferences
		rpp = Prefs.getResultadosPorPag(getBaseContext());	// Obtengo el dato de las preferencias de la aplicación

		// Build the full url
		Url = urlSearch + query + "&geocode=" + latitude + "," + longitude + "," + range + units + "&rpp=" + rpp;
		// Example: Url = "http://search.twitter.com/search.json?q=Raspberry+Pi&geocode=40.417,-3.703,100km&rpp=25";
		
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(Url);

		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			Log.d("JSONFeed", "EstatusLine: " + statusLine.toString());

			int statusCode = statusLine.getStatusCode();
			Log.d("JSONFeed", "EstatusCode: " + statusCode);

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
					// Progress ...
				}
			} else {
				Log.d("JSONFeed", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();

	}
	
	// The definition of our task class
	
	private class bgTask extends AsyncTask<Void, Float, String> {
		
	   @Override
	   protected void onPreExecute() {
	      super.onPreExecute();
	      dialog.setProgress(0);
	      dialog.setMax(100);
	      dialog.show(); 			// Shows the dialog before start download
	   }
	 
	   @Override
	   protected String doInBackground(Void... params) {
		 
	      String json = JSONFeed();
	      
	      //publishProgress(50);
	      
	      return json;
	   }
	 
	   @Override
	   protected void onProgressUpdate(Float... values) {
	      super.onProgressUpdate(values);
	      int p = Math.round(values[0]);
	      dialog.setProgress(p);
	   }
	 
	   @Override
	   protected void onPostExecute(String result) {
		   super.onPostExecute(result);
		   dialog.dismiss();
	
		   String readJSON = result;
		   boolean emptyList = false;
		   
		   //Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
		   Log.d("Suso",result);
		   
		   // Extract data that I need from JSON
		   try {
			   JSONObject jsonObject = new JSONObject(readJSON);
			   JSONArray jsonArray = jsonObject.getJSONArray("results");
			   
			   if (jsonArray.length() == 0) {		// If there is no results
				   
				   TextView empty = (TextView) getListView().getEmptyView(); 
				   empty.setText(R.string.sin_datos);
				   emptyList = true;
				   
			   } else {

				   for (int i = 0; i < jsonArray.length(); i++) {
					   JSONObject jsObject = jsonArray.getJSONObject(i);

					   // Obtains tweet location to open the map below

					   String geo = jsObject.getString("geo");
					   String tweetLoc = null;

					   if (geo.equals("null")) {  	// No location info available
						   tweetLoc = jsObject.getString("location");
					   } else {						// Location info available

						   // Geo format: {"type":"Point","coordinates":[40.4165,-3.70256]}
						   // Notice: we must escape the \ with another \ in regex.

						   // Extract tweet latitude from string
						   String regexLat = "(\\-?\\d+(\\.\\d+)?),";
						   Matcher matcherLat = Pattern.compile(regexLat).matcher(geo);
						   if (matcherLat.find())
						   {
							   mapLatitude = matcherLat.group();
							   mapLatitude = mapLatitude.substring(0,mapLatitude.length()-1);
						   } else {
							   mapLatitude = LAT_DEF;		// No location info available. Put default
							   // Vilanova de Arousa
						   }

						   // Extract tweet longitude from string
						   String regexLon = ",(\\-?\\d+(\\.\\d+)?)";
						   Matcher matcherLon = Pattern.compile(regexLon).matcher(geo);
						   if (matcherLon.find())
						   {
							   mapLongitude = matcherLon.group();
							   mapLongitude = mapLongitude.substring(1,mapLongitude.length());
						   }
						   else {
							   mapLongitude = LON_DEF;  	// No location info available. Put default
						   }

						   tweetLoc = "MPLAT:" + mapLatitude + ", MPLON:" + mapLongitude;

					   }

					   String[] twitInfo = {	String.valueOf(i+1), 
							   jsObject.getString("from_user_name"), 
							   jsObject.getString("created_at"),
							   jsObject.getString("text"), 
							   tweetLoc};
					   list.add(twitInfo);

				   }
			   }
		   } catch (Exception e) {
			   Log.d("ParseJSON", "jsonArray catch");
			   e.printStackTrace();
		   } 

		   
		   if (emptyList) {

		   } else {
			   // Transformamos los elementos String[] en HashMap para
			   // posteriormente incluirlos en el Array Global que se utilizará
			   // para rellenar la lista
			   Twits = new ArrayList<HashMap<String, String>>();

			   for(String[] element:list){
				   HashMap<String,String> datosTwit=new HashMap<String, String>();

				   // Here we use the references originally created in the element "from"
				   datosTwit.put("Index", element[0]);
				   datosTwit.put("Name", element[1]);
				   datosTwit.put("Date", element[2]);
				   datosTwit.put("Message", element[3]);
				   datosTwit.put("Location", element[4]);

				   Twits.add(datosTwit);
			   }

			   // Una vez tenemos toda la información necesaria para rellenar la lista creamos un elemento 
			   // que nos facilitará la tarea: SimpleAdapter(Actividad, Array de HashMap con elementos, 
			   // Fichero XML del diseño de cada fila, Cadenas del HashMap, Ids del Fichero XML del diseño 
			   // de cada fila)
			   SimpleAdapter ListadoAdapter=new SimpleAdapter(getBaseContext(), Twits, R.layout.row, from, to);
			   setListAdapter(ListadoAdapter);
		   }
	   }

	}

	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	   		
		String cadenaTwit = String.valueOf(Twits.get(position));
		
		String mpLatitude = null;
		
		String regexMpLat = "MPLAT:(\\-?\\d+(\\.\\d+)?),";
    	Matcher matcherMpLat = Pattern.compile(regexMpLat).matcher(cadenaTwit);
        if (matcherMpLat.find())
        {
        	mpLatitude = matcherMpLat.group();
        	mpLatitude = mpLatitude.substring(6,mpLatitude.length()-1);
        } else {
        	mpLatitude = LAT_DEF;		// No location info available. Put default
        								// Vilanova de Arousa
        }
        
		String mpLongitude = null;
		
		String regexMpLon = "MPLON:(\\-?\\d+(\\.\\d+)?),";
    	Matcher matcherMpLon = Pattern.compile(regexMpLon).matcher(cadenaTwit);
        if (matcherMpLon.find())
        {
        	mpLongitude = matcherMpLon.group();
        	mpLongitude = mpLongitude.substring(6,mpLongitude.length()-1);
        } else {
        	mpLongitude = LON_DEF;		// No location info available. Put default
        								// Vilanova de Arousa
        }
        
        // Toast.makeText(getBaseContext(), "Lat: " + mpLatitude + "Lon:" + mpLongitude, Toast.LENGTH_SHORT).show();
        
        Intent intent = new Intent(getBaseContext(), TTS_MapActivity.class);
		intent.putExtra(MPLAT, mpLatitude);
		intent.putExtra(MPLON, mpLongitude);
		startActivity(intent);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
