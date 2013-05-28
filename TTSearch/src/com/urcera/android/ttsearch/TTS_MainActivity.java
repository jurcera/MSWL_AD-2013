package com.urcera.android.ttsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TTS_MainActivity extends Activity {
	
	public static String DATA = "DATA_TO_SEARCH";
	public String data = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tts__main);
		
		final EditText edText = (EditText)findViewById(R.id.edtBusqueda);
		
		Button bt1 = (Button) this.findViewById(R.id.butBusqueda);	// Asignamos el botón butBusqueda a bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 			// Crea un listener para bt1 	
				
				@Override
				public void onClick(View v) {						// Cuando se pulsa bt1 ...
					// TODO Auto-generated method stub
					
					data = edText.getText().toString();				// Obtengo el valor del EditText
					data = data.replace(" ", "+");					// Sustituyo espacios por + en la cadena de búsqueda
					
					// Toast.makeText(getBaseContext(), data, Toast.LENGTH_SHORT).show();
										
					if (data.equals("")) {							// Compruebo que no está vacío
						Toast.makeText(getBaseContext(), "Introducir un término a buscar", Toast.LENGTH_SHORT).show();
					} else {
						// Definimos donde estamos (getBaseContext()) y a donde vamos (TTS_ListActivity)
						
						Intent intent = new Intent(getBaseContext(), TTS_ListActivity.class);
						intent.putExtra(DATA, data);
						startActivity(intent);						// ... arranca la actividad TTS_ListActivity.
						
					}
					
				}
			} );
		}
		
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tts__main, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.action_settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
		}
		return false;
	}

}
