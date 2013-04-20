package com.urcera.android.ejercicio3;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
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

public class Ej3MainActivity extends ListActivity {
	
	private static int IDXPUBLI = 3;  	// Es el elemento en el que se pone la publicidad, por ejemplo
										// para 3, hay dos elementos normales y el tercero de publicidad.
	
	private MyAdapter mAdapter = null;  // Adaptador para la gestión de los datos
		
	public class Node   				// Estructura básica para almacenar la información
	{ 
		public String mTitle;
		public String mDescription;
		public Integer mImageResource;
	}
	
	// ArrayList de elementos tipo Node para almacenar todos los elementos a mostrar
	private static ArrayList<Node> mArray = new ArrayList<Node>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ej3_main);
		
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
        
        // Element 1 
        mynode.mTitle = this.getResources().getString(R.string.title1);
        mynode.mDescription = this.getResources().getString(R.string.description1);
        mynode.mImageResource = R.drawable.img1;

        mArray.add(mynode);

        // Element 2
        mynode2.mTitle = this.getResources().getString(R.string.title2);
        mynode2.mDescription = this.getResources().getString(R.string.description2);
        mynode2.mImageResource = R.drawable.img2;

        mArray.add(mynode2);

        // Element 3
        mynode3.mTitle = this.getResources().getString(R.string.title3);
        mynode3.mDescription = this.getResources().getString(R.string.description3);
        mynode3.mImageResource = R.drawable.img3;

        mArray.add(mynode3);
        
        // Element 4
        mynode4.mTitle = this.getResources().getString(R.string.title4);
        mynode4.mDescription = this.getResources().getString(R.string.description4);
        mynode4.mImageResource = R.drawable.img4;

        mArray.add(mynode4);
        
        mArray.addAll(mArray);       // Duplica el array para tener más datos a mostrar.
   
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
    		Toast.makeText(this, mArray.get(position).mTitle, Toast.LENGTH_SHORT).show();
    	}
    	
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej3_main, menu);
		return true;
	}

}
