package com.urcera.android.asynctask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String urlConexion = null;
	private ProgressDialog dialog;
	String filename = null;
	String urlFilename = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	    dialog = new ProgressDialog(this);
	    dialog.setMessage("Descargando...");
	    dialog.setTitle("Progreso");
	    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	    dialog.setCancelable(false);
	    //Realizamos cualquier otra operación necesaria
	    //Creamos una nueva instancia y llamamos al método ejecutar
	    //pasándole el string.
	    
	    
	    
	    urlFilename = "http://dl.dropboxusercontent.com/u/34909625/";
	    filename = "rodrigo.jpg";
	    	    
	    urlConexion = urlFilename + filename;
	    URL miUrl = null;
	    
		try {
			miUrl = new URL(urlConexion);
			new MiTarea().execute(miUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
	}
	
	//https://dl.dropboxusercontent.com/u/34909625/andrea.jpg
	 
	private class MiTarea extends AsyncTask<URL, Float, Integer>{

		protected void onPreExecute() {
			dialog.setProgress(0);
			dialog.setMax(100);
			dialog.show(); //Mostramos el diálogo antes de comenzar
		}

		protected Integer doInBackground(URL... urls) {
			
			Log.d("SUSO URL", String.valueOf(urls[0]));
			int totalSize = 0;
			int downloadedSize = 0;
			
			try {
				 
				Log.d("SUSO", "TRY");
				
				//primero especificaremos el origen de nuestro archivo a descargar utilizando
				
				URL url = urls[0];				
				//URL url = new URL(myUrl);
				
				Log.d("SUSO", "new url");
				
				URLConnection connection;
				connection = url.openConnection();
				HttpURLConnection urlConnection = (HttpURLConnection) connection;
				int responseCode = urlConnection.getResponseCode();
				
				if (responseCode == HttpURLConnection.HTTP_OK) {
					Log.d("SUSO","Conexión OK");
					File SDCardRoot = Environment.getExternalStorageDirectory();
					File file = new File(SDCardRoot,filename);
					FileOutputStream fileOutput = new FileOutputStream(file);
					InputStream inputStream = urlConnection.getInputStream();
					
					totalSize = urlConnection.getContentLength();
					downloadedSize = 0;
					//creamos un buffer y una variable para ir almacenando el tamaño temporal de este
					byte[] buffer = new byte[1024];
					int bufferLength = 0; 
					
					//ahora iremos recorriendo el buffer para escribir el archivo de destino
					while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
						fileOutput.write(buffer, 0, bufferLength);
						downloadedSize += bufferLength;
						//podríamos utilizar una función para ir actualizando el progreso de lo
						//actualizaProgreso(downloadedSize, totalSize);
						publishProgress(((downloadedSize * 100) / (float) totalSize)); //Actualizamos los valores
						Log.d("SUSO", String.valueOf((downloadedSize * 100) / totalSize));
					}
					fileOutput.close();
			
				} else {
					Log.d("SUSO","Error de conexión");
				}
				/*
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);
				urlConnection.connect();
				
				Log.d("SUSO", "urlconection: " + urlConnection.getResponseMessage());
				
				Log.d("SUSO", "Connect");
				
				File SDCardRoot = Environment.getExternalStorageDirectory();
				File file = new File(SDCardRoot,"test_andrea.jpg");
				
				Log.d("SUSO", "SDcard");

				FileOutputStream fileOutput = new FileOutputStream(file);
				 
				Log.d("SUSO", "fileoutput");
				
				InputStream inputStream = urlConnection.getInputStream();
				
				Log.d("SUSO", ">> inputstream");
				
				int totalSize = urlConnection.getContentLength();
				
				Log.d("SUSO", "totalsize: " + String.valueOf(totalSize));
				int downloadedSize = 0;
				//creamos un buffer y una variable para ir almacenando el
				//tamaño temporal de este
				byte[] buffer = new byte[1024];
				int bufferLength = 0; 
				Log.d("SUSO", "Total size = " + String.valueOf(totalSize));
				
				//ahora iremos recorriendo el buffer para escribir el archivo de destino
				while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
					fileOutput.write(buffer, 0, bufferLength);
					downloadedSize += bufferLength;
					//podríamos utilizar una función para ir actualizando el progreso de lo
					//actualizaProgreso(downloadedSize, totalSize);
					publishProgress(((downloadedSize * 100) / (float) totalSize)); //Actualizamos los valores
					Log.d("SUSO", String.valueOf((downloadedSize * 100) / totalSize));
				}
				fileOutput.close();
				*/
						
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), 
						"Problemas de conexión.\n    Reintentar "+e, Toast.LENGTH_SHORT)
						.show();
				e.printStackTrace();
			}
			
			/*
			Simularemos que descargamos un fichero
			mediante un sleep
		
			for (int i = 0; i < 250; i++) {
				//Simulamos cierto retraso
				try {Thread.sleep(200); }
				catch (InterruptedException e) {}

				//publishProgress(i/250f); //Actualizamos los valores
			}
			*/

			if(downloadedSize==totalSize){
				return 1;
			} else {
				return 0;
			}
		}

		protected void onProgressUpdate (Float... valores) {
			int p = Math.round(valores[0]);
			dialog.setProgress(p);
		}

		protected void onPostExecute(Integer bytes) {
			dialog.dismiss();
			int descarga = bytes;
			File SDCardRoot = Environment.getExternalStorageDirectory();
			File file = new File(SDCardRoot,filename);
			//Ejecuta el archivo descargado
		
			if (descarga == 1){
				Intent intent = new Intent();
				intent.setAction(android.content.Intent.ACTION_VIEW);
				MimeTypeMap mime = MimeTypeMap.getSingleton();
				String ext=file.getName().substring(file.getName().indexOf(".")+1);
				String type = mime.getMimeTypeFromExtension(ext);
				intent.setDataAndType(Uri.fromFile(file),type);
				startActivity(intent);
			}else
				Toast.makeText(getApplicationContext(),	"Error en la descarga.", Toast.LENGTH_SHORT).show();
			
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
