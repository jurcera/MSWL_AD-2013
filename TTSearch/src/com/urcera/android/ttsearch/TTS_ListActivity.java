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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class TTS_ListActivity extends ListActivity  {

	public static String MPLAT = "MPLATITUD";
	public static String MPLON = "MPLONGITUD";
	public static String LAT_DEF = "42.5623502994";
	public static String LON_DEF = "-8.8257980346";
	
	ArrayList<HashMap<String,String>> Twits; // Array para almacenar los twits
	 
	//Con los siguientes Arrays establecemos la correspondencia
	//entre los elementos del Array de HashMaps de twits (from)
	//con los elementos del diseño en XML de cada una de las filas (to)

	String[] from=new String[] {"Index","Name","Date","Message","Location"};
	int[] to=new int[]{R.id.index,R.id.name,R.id.date,R.id.message,R.id.location};
	
	String Url = null;
	String urlSearch = null;
	String query = null;
	String latitude = null;
	String longitude = null;
	String gpsLatitude = null;			// Posición gps del móvil
	String gpsLongitude = null;
	String range = null;
	String units = null;
	String rpp = null;
	String mapLatitude = null;			// Posición donde está localizado el twit
	String mapLongitude = null;
	boolean locManual = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
			
		// Leo los datos pasados desde la actividad principal
		Intent intent = getIntent();	// Captura el intent que arrancó esta actividad
		if (intent != null)  			// Confirma si la actividad se arrancó a través de un intent
		{
			String data = intent.getStringExtra(TTS_MainActivity.DATA); 	// Recoje el dato pasado desde la MainActivity
			if (data != null)  			// Comprueba que data tiene un valor
			{
				query = data;			// Asigna a query el valor recibido de la MainActivity
			}
			
			String myLatitude = intent.getStringExtra(TTS_MainActivity.LAT); 	// Recoje el dato pasado desde la MainActivity
			if (myLatitude != null)  			// Comprueba que myLatitude tiene un valor
			{
				gpsLatitude = myLatitude;			// Asigna a gpslatitude el valor recibido de la MainActivity
			} else {
				gpsLatitude = LAT_DEF;
			}
			
			String myLongitude = intent.getStringExtra(TTS_MainActivity.LON); 	// Recoje el dato pasado desde la MainActivity
			if (myLongitude != null)  			// Comprueba que myLongitude tiene un valor
			{
				gpsLongitude = myLongitude;			// Asigna a gpslongitude el valor recibido de la MainActivity
			} else {
				gpsLongitude = LON_DEF;
			}
		}
		
		//Toast.makeText(getBaseContext(), gpsLatitude +" "+ gpsLongitude, Toast.LENGTH_SHORT).show();
		
		// >>>>>>>>>> Para que funcione en el dispositivo móvil hasta que no habilite el asynctask <<<<<<<<<<<<
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
		//StrictMode.setThreadPolicy(policy);
		
		
		ArrayList<String[]> lista = new ArrayList<String[]>();
		
		/*
		String[] twit1 = {"1","Jesus Urc","Tue, 14 May 2013 08:37:43 +0000","Hola, esto es una prueba", "MAP: 0.0,0.0"};
		lista.add(twit1);
		*/
		
		// Obtengo el JSON de la url indicada
		String readJSON = readJSONFeed();
		
		// Extraigo los datos que necesito del JSON
		try {
			  JSONObject jsonObject = new JSONObject(readJSON);
		      JSONArray jsonArray = jsonObject.getJSONArray("results");
		      
		      for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jsObject = jsonArray.getJSONObject(i);
		     
		        // Obtengo la localización del twit para después abrir el mapa
		        
		        String geo = jsObject.getString("geo");
		        String twitLoc = null;
		        
		        if (geo.equals("null")) {   //Cuando no hay información de localización GPS
		        	twitLoc = jsObject.getString("location");
		        } else {					// Cuando hay información de localización GPS
		        	
		        	// El formato es: {"type":"Point","coordinates":[40.4165,-3.70256]}
		        	// Ojo, hay que escapar la \ con otra \
		        	// Obtengo la Latitud del twit
		        	String regexLat = "(\\-?\\d+(\\.\\d+)?),";
		        	Matcher matcherLat = Pattern.compile(regexLat).matcher(geo);
	                if (matcherLat.find())
	                {
	                	mapLatitude = matcherLat.group();
	                	mapLatitude = mapLatitude.substring(0,mapLatitude.length()-1);
	                } else {
	                	mapLatitude = LAT_DEF;		// Por si no obtengo una posición
	                											// Vilanova de Arousa
	                }
	                
	                // Obtengo la Longitud del twit
	                String regexLon = ",(\\-?\\d+(\\.\\d+)?)";
		        	Matcher matcherLon = Pattern.compile(regexLon).matcher(geo);
	                if (matcherLon.find())
	                {
	                	mapLongitude = matcherLon.group();
	                	mapLongitude = mapLongitude.substring(1,mapLongitude.length());
	                }
	                else {
	                	mapLongitude = LON_DEF;  	// Por si no obtengo una posición
	                }
               		        	
		        	twitLoc = "MPLAT:" + mapLatitude + ", MPLON:" + mapLongitude;
		        	//Log.d("Suso", String.valueOf(i+1) + " " + geo);
		        }
		        
		        
		        String[] twitInfo = {	String.valueOf(i+1), 
		        					jsObject.getString("from_user_name"), 
		        					jsObject.getString("created_at"),
		        					jsObject.getString("text"), 
		        					twitLoc};
		        lista.add(twitInfo);
		        
		        //Log.d("ParserJSON", jsObject.getString("from_user_name"));
		      }
		} catch (Exception e) {
			  Log.d("ParseJSON", "jsonArray catch");
		      e.printStackTrace();
		} 
			
		// Transformamos los elementos String[] en HashMap para
		// posteriormente incluirlos en el Array Global que se utilizará
		// para rellenar la lista
		Twits = new ArrayList<HashMap<String, String>>();
		
		for(String[] elemento:lista){
		        HashMap<String,String> datosTwit=new HashMap<String, String>();
		 
		        // Aquí es dónde utilizamos las referencias creadas inicialmente
		        // en el elemento "from"
		        datosTwit.put("Index", elemento[0]);
		        datosTwit.put("Name", elemento[1]);
		        datosTwit.put("Date", elemento[2]);
		        datosTwit.put("Message", elemento[3]);
		        datosTwit.put("Location", elemento[4]);
		 
		        Twits.add(datosTwit);
		        
		}
		    // Una vez tenemos toda la información necesaria para rellenar la lista
		    // creamos un elemento que nos facilitará la tarea:
		    // SimpleAdapter(Actividad, Array de HashMap con elementos, Fichero XML del
		    // diseño de cada fila, Cadenas del HashMap, Ids del Fichero XML del diseño de cada fila)
		SimpleAdapter ListadoAdapter=new SimpleAdapter(this, Twits, R.layout.row, from, to);
		setListAdapter(ListadoAdapter);
		
		//Log.d("ArrayList", String.valueOf(Twits.get(0)));
	}

		
	public String readJSONFeed() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		urlSearch = Prefs.getUrlSearch(getBaseContext());	// Obtengo el dato de las preferencias de la aplicación
		
		locManual = Prefs.getlocManual(getBaseContext());	// Obtengo el dato de las preferencias de la aplicación
		
		if (locManual) {
			latitude = Prefs.getLatitud(getBaseContext());		// Si no obtengo mi latitud actual uso los de preferencias
			longitude = Prefs.getLongitud(getBaseContext());	// Si no obtengo mi longitud actual uso los de preferencias
		} else {
			latitude = gpsLatitude;
			longitude = gpsLongitude;
		}
		
		range = Prefs.getDistancia(getBaseContext());		// Obtengo el dato de las preferencias de la aplicación
		units = Prefs.getUnidades(getBaseContext());		// Obtengo el dato de las preferencias de la aplicación
		rpp = Prefs.getResultadosPorPag(getBaseContext());	// Obtengo el dato de las preferencias de la aplicación

		Url = urlSearch + query + "&geocode=" + latitude + "," + longitude + "," + range + units + "&rpp=" + rpp;
		//Url = "http://search.twitter.com/search.json?q=Raspberry+Pi&geocode=40.417,-3.703,100km&rpp=25";
		
		Log.d("Suso",Url);

		HttpGet httpGet = new HttpGet(Url);

		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			Log.d("ParseJSON", "EstatusLine: " + statusLine.toString());

			int statusCode = statusLine.getStatusCode();
			Log.d("ParseJSON", "EstatusCode: " + statusCode);

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.d("ParseJSON", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	   /*
		Uri uri = Uri.parse("http://www.google.es");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
		*/
		
		//Toast.makeText(getBaseContext(), String.valueOf(position)+ " id: " + String.valueOf(id), Toast.LENGTH_SHORT).show();
		
		//Toast.makeText(getBaseContext(), String.valueOf(Twits.get(position)), Toast.LENGTH_SHORT).show();
		
		
		String cadenaTwit = String.valueOf(Twits.get(position));
		
		String mpLatitude = null;
		
		String regexMpLat = "MPLAT:(\\-?\\d+(\\.\\d+)?),";
    	Matcher matcherMpLat = Pattern.compile(regexMpLat).matcher(cadenaTwit);
        if (matcherMpLat.find())
        {
        	mpLatitude = matcherMpLat.group();
        	mpLatitude = mpLatitude.substring(6,mpLatitude.length()-1);
        } else {
        	mpLatitude = LAT_DEF;		// Por si no obtengo una posición
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
        	mpLongitude = LON_DEF;		// Por si no obtengo una posición
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
