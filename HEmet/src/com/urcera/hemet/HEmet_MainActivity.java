package com.urcera.hemet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HEmet_MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_main);
		
		Button bt1 = (Button) this.findViewById(R.id.butConnect);	// Assign button butBusqueda to bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 			// Create a listener for bt1 	

				@Override
				public void onClick(View v) {						// When I push bt1 ...
					// TODO Auto-generated method stub


					Intent intent = new Intent(getBaseContext(), HEmet_inst_meter.class); 
					startActivity(intent);						// start ListActivity.

				}

			} );
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hemet__main, menu);
		return true;
	}

}
