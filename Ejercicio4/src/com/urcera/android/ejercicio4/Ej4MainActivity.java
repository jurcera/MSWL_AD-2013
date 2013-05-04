package com.urcera.android.ejercicio4;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ej4MainActivity extends ListActivity {
		
		private static int IDXPUBLI = 3;  	// Es el elemento en el que se pone la publicidad, por ejemplo
											// para 3, hay dos elementos normales y el tercero de publicidad.
		public static String TIT = "TITULO";
		public static String LAT = "LATITUD";
		public static String LON = "LONGITUD";
		public static String IMG = "IMAGEN";
		
		private MyAdapter mAdapter = null;  // Adaptador para la gestión de los datos
			
		public class Node   				// Estructura básica para almacenar la información
		{ 
			public String mTitle;
			public String mDescription;
			public Integer mImageResource;
			public Double mLatitude;
			public Double mLongitude;
		}
		
		// ArrayList de elementos tipo Node para almacenar todos los elementos a mostrar
		private static ArrayList<Node> mArray = new ArrayList<Node>();
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_ej4_main);
			
			setData(); 						// Carga los datos en el array 
	        
	        mAdapter = new MyAdapter(this);	// Creo un adaptador y le paso en contexto.
	        setListAdapter(mAdapter);	 	// Presenta la lista en pantalla    
		}
		
		
		private void setData ()  			// Carga los datos en el Array
	    {
	        mArray.clear();  				// Limpia el array

	        Node mynode = new Node();
	        Node mynode2 = new Node();
	        Node mynode3 = new Node();
	        Node mynode4 = new Node();
	        Node mynode5 = new Node();
	        Node mynode6 = new Node();
	        Node mynode7 = new Node();
	        
	        // Element 1 (Arduino)
	        mynode.mTitle = this.getResources().getString(R.string.title1);
	        mynode.mDescription = this.getResources().getString(R.string.description1);
	        mynode.mImageResource = R.drawable.img1;
	        mynode.mLatitude = 45.466667; 	// Ivrea. Italia.
	        mynode.mLongitude = 7.866667;

	        mArray.add(mynode);

	        // Element 2 (Raspberry Pi)
	        mynode2.mTitle = this.getResources().getString(R.string.title2);
	        mynode2.mDescription = this.getResources().getString(R.string.description2);
	        mynode2.mImageResource = R.drawable.img2;
	        mynode2.mLatitude = 52.2;		// Caldecote. UK.
	        mynode2.mLongitude = -0.026;

	        mArray.add(mynode2);

	        // Element 3 (chipKIT)
	        mynode3.mTitle = this.getResources().getString(R.string.title3);
	        mynode3.mDescription = this.getResources().getString(R.string.description3);
	        mynode3.mImageResource = R.drawable.img3;
	        mynode3.mLatitude = 46.733333;	// Pullman, Washington
	        mynode3.mLongitude = -117.166667;

	        mArray.add(mynode3);
	        
	        // Element 4 (mbed)
	        mynode4.mTitle = this.getResources().getString(R.string.title4);
	        mynode4.mDescription = this.getResources().getString(R.string.description4);
	        mynode4.mImageResource = R.drawable.img4;
	        mynode4.mLatitude = 52.205;		// Cambridge
	        mynode4.mLongitude = 0.119;

	        mArray.add(mynode4);
	        
	        // Element 5 (AndroidStamp)
	        mynode5.mTitle = this.getResources().getString(R.string.title5);
	        mynode5.mDescription = this.getResources().getString(R.string.description5);
	        mynode5.mImageResource = R.drawable.img5;
	        mynode5.mLatitude = 4.638219;	// Universidad Nacional de Colombia
	        mynode5.mLongitude = -74.084347;

	        mArray.add(mynode5);
	        
	        // Element 6 (BeagleBone)
	        mynode6.mTitle = this.getResources().getString(R.string.title6);
	        mynode6.mDescription = this.getResources().getString(R.string.description6);
	        mynode6.mImageResource = R.drawable.img6;
	        mynode6.mLatitude = 29.762778;	// Houston
	        mynode6.mLongitude = -95.383056;

	        mArray.add(mynode6);
	        
	        // Element 7 (UDOO)
	        mynode7.mTitle = this.getResources().getString(R.string.title7);
	        mynode7.mDescription = this.getResources().getString(R.string.description7);
	        mynode7.mImageResource = R.drawable.img7;
	        mynode7.mLatitude = 42.358056;	// Boston
	        mynode7.mLongitude = -71.063611;

	        mArray.add(mynode7);
	        
	        mArray.addAll(mArray);       // Duplica el array para tener más datos a mostrar.
	        // mArray.addAll(mArray);       // Vuelvo a duplicar el array para tener más datos a mostrar.
	   
	    }
		
		
		public static class MyAdapter extends BaseAdapter 
		{
	        private Context mContext;  	// Para pasarle el contexto a la clase
			
			public MyAdapter (Context context){		// Para recibir el contexto
				mContext = context;
			}

			@Override
			public int getCount() {					// Devuelve el número de elementos a visualizar
				return mArray.size() + (int) (mArray.size()/(IDXPUBLI-1));
			}

			
			@Override
			public Object getItem(int position) {	// Devuelve el elemen
				return mArray.get(position);
			}

			@Override
			public long getItemId(int position) {  // Para añadir un id a cada objeto Node
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				
				View view;
				
				if ((position+1)%IDXPUBLI == 0) {   // Si hemos llegado a IDXPUBLI ponemos una publicidad.
					
					// Indico el layout a usar para presentar la publicidad
					LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.publicidad, null);
					
					// Sobreescribo el textview
					TextView tvTitle = (TextView) view.findViewById(R.id.tvPubli);
					tvTitle.setText("¡Publicidad!");
											
				} else {
					
					// Calculo la posición del array a mostrar teniendo en cuenta donde está la publicidad
						
					position = position - (int) ((position+1)/IDXPUBLI);  

					// Indico el layout a usar para presentar los datos
					LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
					view = inflater.inflate(R.layout.lista, null);
					
					// Obtengo el título del array
					TextView tvTitle = (TextView) view.findViewById(R.id.title);
					tvTitle.setText(mArray.get(position).mTitle);
						
					// Obtengo la descripción del array
					TextView tvDescription = (TextView) view.findViewById(R.id.description);
					tvDescription.setText(mArray.get(position).mDescription);
					
					// Obtengo la imagen de res/drawable
					ImageView img = (ImageView) view.findViewById(R.id.image);
					img.setImageDrawable(mContext.getResources().getDrawable(mArray.get(position).mImageResource));

				}

				return view;			// Devuelve la vista del elemento correspondiente.
			}

		}
		
	    protected void onListItemClick(ListView l, View v, int position, long id) 
		{

	    	// Create a new intent to call other Activity. 
	    	// Using the methods "putExtra" we can
	    	// send data to the new activity
	    	
	    	// Al pulsar un elemento de la lista muestra un toast
	    	   	
	    	if ((position+1)%IDXPUBLI == 0) {							// Para publicidad
	    		Toast.makeText(this, "Posición: " + String.valueOf(position), Toast.LENGTH_SHORT).show();
	    		
	    	} else {
	    		
	    		position = position - (int) ((position+1)/IDXPUBLI);	// Para datos normales
	    		//Toast.makeText(this, mArray.get(position).mTitle, Toast.LENGTH_SHORT).show();
	    		Intent intent = new Intent(Ej4MainActivity.this, Ej4maps.class);
	    		intent.putExtra(TIT, mArray.get(position).mTitle);
				intent.putExtra(LAT, String.valueOf(mArray.get(position).mLatitude));
				intent.putExtra(LON, String.valueOf(mArray.get(position).mLongitude));
				intent.putExtra(IMG, String.valueOf(mArray.get(position).mImageResource));
				startActivity(intent);
	    	}
	    	
		}
			

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej4_main, menu);
		return true;
	}

}
