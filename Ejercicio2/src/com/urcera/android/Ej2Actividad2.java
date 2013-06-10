package com.urcera.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Ej2Actividad2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ej2_actividad2);	// Asigna un layout a la actividad
		
		TextView tv = (TextView) this.findViewById(R.id.tvTitulo2); //Asigna a tv el TextView del layout anterior
		
		Intent i = getIntent();		// Captura el intent que arrancó esta actividad
		if (i != null)  			// Confirma si la actividad se arrancó a través de un intent
		{
			String title = i.getStringExtra(Ej2MainActivity.TIT); 	// Recoje el valor de la variable TITLE pasado desde la MainActivity
			if (title != null)  	// Coprueba que TITLE tiene un valor
			{
				tv.setText(title);	// Escribe en el textView el valor de TITLE recibido de la MainActivity
			}
		}
	}

	@Override
	public void finish() {  								// Sobreescribe el método finish() para devolver valores al salir de la actividad
		// TODO Auto-generated method stub
		Intent returnIntent = new Intent();					// Crea un intent para comunicarse con la actividad que la ha llamado
		returnIntent.putExtra(Ej2MainActivity.PARAM, 20);	// Añade una pareja de variable-valor (Ej2MainActivity.PARAM, 20) para pasarla 
															// a la actividad que la ha llamado
		
		setResult(RESULT_OK,returnIntent);					// Devuelve un RESULT_OK para indicar que todo ha ido OK
		
		super.finish();										// Finaliza la actividad
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej2_actividad2, menu);
		return true;
	}

}
