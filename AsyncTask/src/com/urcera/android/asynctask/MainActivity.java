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
import android.view.Gravity;
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
	    dialog.setTitle("Progreso");
	    dialog.setMessage("Descargando...");
	    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	    dialog.setCancelable(false);
   
	    urlFilename = "http://dl.dropboxusercontent.com/u/34909625/"; 	// Ruta de la imagen a descargar
	    filename = "andrea.jpg";			// rodrigo.jpg y jesusadrian.jpg (Fichero a descargar)
	    urlConexion = urlFilename + filename;							// URL completa
	    URL miUrl = null;
	    
		try {
			miUrl = new URL(urlConexion);
			new MiTarea().execute(miUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
	}
	
	 
	private class MiTarea extends AsyncTask<URL, Float, Integer>{

		
		protected void onPreExecute() {
			dialog.setProgress(0);
			dialog.setMax(100);
			dialog.show(); //Mostramos el diálogo antes de comenzar
		}

		
		protected Integer doInBackground(URL... urls) {
			
			int totalSize = 0;
			int downloadedSize = 0;
			
			try {
			
				//primero especificaremos el origen de nuestro archivo a descargar utilizando
				
				URL url = urls[0];				
				
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
					// Creamos un buffer y una variable para ir almacenando el tamaño temporal de éste
					byte[] buffer = new byte[1024];
					int bufferLength = 0; 
					
					//ahora iremos recorriendo el buffer para escribir el archivo de destino
					while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
						fileOutput.write(buffer, 0, bufferLength);
						downloadedSize += bufferLength;
						// Actualizamos el progreso de la descarga
						publishProgress(((downloadedSize * 100) / (float) totalSize)); 
					}
					fileOutput.close();
			
				} else {
					Log.d("SUSO","Error de conexión");
				}
						
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
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
			// Muestra el archivo descargado
		
			if (descarga == 1){
				Toast toast = Toast.makeText(getApplicationContext(),"Descarga correcta", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 0, 0);
				toast.show();
				Intent intent = new Intent();
				intent.setAction(android.content.Intent.ACTION_VIEW);
				MimeTypeMap mime = MimeTypeMap.getSingleton();
				String ext=file.getName().substring(file.getName().indexOf(".")+1);
				String type = mime.getMimeTypeFromExtension(ext);
				intent.setDataAndType(Uri.fromFile(file),type);
				startActivity(intent);
			} else {
				Toast.makeText(getApplicationContext(),	"Error en la descarga", Toast.LENGTH_SHORT).show();
			}
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
