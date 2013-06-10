package com.urcera.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Ej2Actividad3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_ej2_actividad3);
		setContentView(R.layout.activity_ej2_actividad2);	// Asigno un layout a la actividad
		
		//TextView tv = (TextView) this.findViewById(R.id.tvTitulo3);
		TextView tv = (TextView) this.findViewById(R.id.tvTitulo2); 	//Asigno a tv el TextView del layout anterior
		
		Intent i = getIntent();
		if (i != null)  			//para ver si se ha llamado a través de un intent o no
		{
			String title = i.getStringExtra(Ej2MainActivity.TIT);
			if (title != null)  	//para confirmar que se ha recibido un dato
			{
				tv.setText(title);
			}
		}
		
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Intent returnIntent = new Intent();
		returnIntent.putExtra(Ej2MainActivity.PARAM, 30);
		
		setResult(RESULT_OK,returnIntent);
		
		super.finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej2_actividad3, menu);
		return true;
	}

}
