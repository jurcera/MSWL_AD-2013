package com.urcera.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Ej2MainActivity extends Activity {
	
	
	public static String PARAM = "PARAMETRO";	// Defino un parámetro a pasar entre las actividades secundarias y la MainActivity
	public static String TIT = "TITLE";			// Defino un parámetro a pasar entre la MainActivity y las actividades secundarias
												// Los dos parámetros anteriores no serían necesarios pero simplifican y unifican
												// en un solo sitio la deficición para evitar posibles errores al usarse en varios 
												// ficheros
	private final int FROM_ACTIVITY_2 = 2; 		// Id de la actividad2
	private final int FROM_ACTIVITY_3 = 3;		// Id de la actividad3

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ej2_main);
		
		Button bt1 = (Button) this.findViewById(R.id.boton1);	// Asignamos el boton1 a bt1
		if (bt1 != null)										// ????
		{
			bt1.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
					// TODO Auto-generated method stub
					
					// Definimos donde estamos (this) y a donde vamos (Actividad1)
					Intent intent = new Intent(Ej2MainActivity.this, Ej2Actividad1.class);
					startActivity(intent);						// Arranca la actividad1
				}
			} );
		}
		
		Button bt2 = (Button) this.findViewById(R.id.boton2);	// Asignamos el boton2 a bt2
		if (bt2 != null)
		{
			bt2.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton2 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad2 al pulsar boton2
					// TODO Auto-generated method stub
					
					// Definimos donde estamos (this) y a donde vamos (Actividad2)
					Intent intent = new Intent(Ej2MainActivity.this, Ej2Actividad2.class);
					intent.putExtra(TIT, "Activity2");				// Pasa a la actividad2 la variable TITLE de valor Activity2 
					startActivityForResult(intent, FROM_ACTIVITY_2);	// Arranca la actividad2 en modo esperando respuesta. 
																		// Al volver atrás de la actividad2 recibiré la respuesta.
				}
			} );
		}
		
		Button bt3 = (Button) this.findViewById(R.id.boton3);	// Asignamos el boton3 a bt3
		if (bt3 != null)
		{
			bt3.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton3 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad3 al pulsar boton3
					// TODO Auto-generated method stub
					
					// Definimos donde estamos (this) y a donde vamos (Actividad3)
					Intent intent = new Intent(Ej2MainActivity.this, Ej2Actividad3.class);
					intent.putExtra(TIT, "Título Activity3"); 				// Paso la variable TITLE de valor Activity3 a la actividad3
					startActivityForResult(intent, FROM_ACTIVITY_3);	// Arranca la actividad3 en modo esperando respuesta
				}
			} );
		}
		
		Button bt4 = (Button) this.findViewById(R.id.boton4);	// Asignamos el boton4 a bt4
		if (bt4 != null)
		{
			bt4.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton4 	
				
				@Override
				public void onClick(View v) {					// Llama a ACTION_CALL (llamar por telefono) al pulsar boton4
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(Intent.ACTION_CALL);	// ACTION_DIAL abre el marcador para llamar, ACTION_CALL llama directamente.
					intent.setData(Uri.parse("tel:600-000-000")); 	// Paso el teléfono al que llamar
					startActivity(intent);						// Arranca la actividad y llama por teléfono al número indicado
				}
			} );
		}
		
		Button bt5 = (Button) this.findViewById(R.id.boton5);	// Asignamos el boton5 a bt5
		if (bt5 != null)
		{
			bt5.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton5 	
				
				@Override
				public void onClick(View v) {					// Llama a ACTION_VIEW al pulsar boton5
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(Intent.ACTION_VIEW);	
					intent.setData(Uri.parse("http://blog.urcera.com/wordpress")); 	// Paso la URI a la que conectarse
					startActivity(intent);						// Arranca un navegador web para conectarse a la URI indicada
				}
			} );
		}
		
		/*Button bt6 = (Button) this.findViewById(R.id.boton6);	// Asignamos el boton6 a bt6
		if (bt6 != null)
		{
			bt6.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton6 	
				
				@Override
				public void onClick(View v) {					// Llama a ACTION_VIEW al pulsar boton6
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(Intent.ACTION_SEND);	
					intent.setType("image/*");
					intent.putExtra(Intent.EXTRA_STREAM,Uri.parse(Environment.getExternalStorageDirectory().toString()+"/foto.jpg"));
					// Paso la URI a la que conectarse
					startActivity(Intent.createChooser(intent, "Send File"));	// Arranca la actividad y llama por teléfono al número indicado
				}
			} );
		}*/
	}

	
	@Override
	//Para recoger datos al volver de actividad2, actividad3, ..., al ser arrancadas con startActivityForResult
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK)
		{
			Integer value = data.getIntExtra(PARAM, -1);
			
			if (requestCode == FROM_ACTIVITY_2)
				Toast.makeText(this, "Come back from Activity 2:" + String.valueOf(value), Toast.LENGTH_SHORT).show();
			
			if (requestCode == FROM_ACTIVITY_3)
				Toast.makeText(this, "Come back from Activity 3:" + String.valueOf(value), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej2_main, menu);
		return true;
	}
	

}
