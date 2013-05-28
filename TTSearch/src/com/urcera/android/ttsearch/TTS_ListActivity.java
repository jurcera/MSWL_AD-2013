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
import android.widget.SimpleAdapter;
import android.widget.Toast;



public class TTS_ListActivity extends ListActivity  {
	
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
	String range = null;
	String units = null;
	String rpp = null;
	String mapLatitude = null;
	String mapLongitude = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		
		Intent intent = getIntent();	// Captura el intent que arrancó esta actividad
		if (intent != null)  			// Confirma si la actividad se arrancó a través de un intent
		{
			String data = intent.getStringExtra(TTS_MainActivity.DATA); 	// Recoje el dato pasado desde la MainActivity
			if (data != null)  			// Comprueba que data tiene un valor
			{
				query = data;			// Asigna a query el valor recibido de la MainActivity
			}
		}
		
		Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();
		
		// >>>>>>>>>> Para que funcione en el dispositivo móvil <<<<<<<<<<<<
		//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
		//StrictMode.setThreadPolicy(policy);
		
		
		ArrayList<String[]> lista = new ArrayList<String[]>();
		
		/*
		String[] evento1 = {"11:30","Ofrenda de Flores","Participa la Banda Sinfónica Municipal de Albacete", "1"};
		lista.add(evento1);
		*/
		
		// Obtengo el JSON
		String readJSON = readJSONFeed();
		
		try {
			  JSONObject jsonObject = new JSONObject(readJSON);
		      JSONArray jsonArray = jsonObject.getJSONArray("results");
		      
		      for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject jsObject = jsonArray.getJSONObject(i);
		     
		        // Obtengo la localización del twit para después abrir el mapa
		        
		        String geo = jsObject.getString("geo");
		        String twitLoc = null;
		        
		        if (geo.equals("null")) {
		        	twitLoc = jsObject.getString("location");
		        	//Log.d("Suso", String.valueOf(i+1) + " " + geo + " Location= " + jsObject.getString("location"));
		        } else {
		        	
		        	// Obtengo la Latitud del twit
		        	String regexLat = "(\\-?\\d+(\\.\\d+)?),";
		        	Matcher matcherLat = Pattern.compile(regexLat).matcher(geo);
	                if (matcherLat.find())
	                {
	                	mapLatitude = matcherLat.group();
	                	mapLatitude = mapLatitude.substring(0,mapLatitude.length()-1);
	                } else {
	                	mapLatitude = "0.000";		// Por si no obtengo una posición
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
	                	mapLongitude = "0.000";  	// Por si no obtengo una posición
	                }
               		        	
		        	twitLoc = "MAP: " + mapLatitude + "," + mapLongitude;
		        	Log.d("Suso", String.valueOf(i+1) + " " + geo);
		        }
		        /**/	
		        
		        String[] evento = {	String.valueOf(i+1), 
		        					jsObject.getString("from_user_name"), 
		        					jsObject.getString("created_at"),
		        					jsObject.getString("text"), 
		        					twitLoc};
		        lista.add(evento);
		        
		        Log.d("ParserJSON", jsObject.getString("from_user_name"));
		      }
		} catch (Exception e) {
			  Log.d("ParseJSON", "jsonArray catch");
		      e.printStackTrace();
		} 
			
		// Transformamos los elementos String[] en HashMap para
		// posteriormente incluirlos en el Array Global que se utilizará
		// para rellenar la lista
		Twits = new ArrayList<HashMap<String, String>>();
		for(String[] evento:lista){
		        HashMap<String,String> datosEvento=new HashMap<String, String>();
		 
		        // Aquí es dónde utilizamos las referencias creadas inicialmente
		        // en el elemento "from"
		        datosEvento.put("Index", evento[0]);
		        datosEvento.put("Name", evento[1]);
		        datosEvento.put("Date", evento[2]);
		        datosEvento.put("Message", evento[3]);
		        datosEvento.put("Location", evento[4]);
		 
		        Twits.add(datosEvento);
		}
		    // Una vez tenemos toda la información necesaria para rellenar la lista
		    // creamos un elemento que nos facilitará la tarea:
		    // SimpleAdapter(Actividad, Array de HashMap con elementos, Fichero XML del
		    // diseño de cada fila, Cadenas del HashMap, Ids del Fichero XML del diseño de cada fila)
		SimpleAdapter ListadoAdapter=new SimpleAdapter(this, Twits, R.layout.row, from, to);
		setListAdapter(ListadoAdapter);
	}

		
	public String readJSONFeed() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		urlSearch = Prefs.getUrlSearch(getBaseContext());	// Obtengo el dato de las preferencias de la aplicación
		//if
		latitude = Prefs.getLatitud(getBaseContext());		// Si no obtengo mi latitud actual uso los de preferencias
		//if
		longitude = Prefs.getLongitud(getBaseContext());	// Si no obtengo mi longitud actual uso los de preferencias
		range = Prefs.getDistancia(getBaseContext());		// Obtengo el dato de las preferencias de la aplicación
		units = Prefs.getUnidades(getBaseContext());		// Obtengo el dato de las preferencias de la aplicación
		rpp = Prefs.getResultadosPorPag(getBaseContext());	// Obtengo el dato de las preferencias de la aplicación

		Url = urlSearch + query + "&geocode=" + latitude + "," + longitude + "," + range + units + "&rpp=" + rpp;
		//Url = "http://search.twitter.com/search.json?q=Raspberry+Pi&geocode=40.417,-3.703,100km&rpp=25";

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
