/*
 *  Copyright (C)2013, Jesus Urcera Lopez
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/. 
 *
 *  Author : Jesus Urcera Lopez <jurcera at gmail dot com>
 *
*/

package com.urcera.hemet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HEmet_menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_menu);
		
		// Date format
		Calendar c = Calendar.getInstance();
		SimpleDateFormat now = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateNow = now.format(c.getTime());
		
		//Log.d("FECHA", dateNow);
		
		
		Button bt1 = (Button) this.findViewById(R.id.btInstBasic);	// Assign button butBusqueda to bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 			// Create a listener for bt1 	

				@Override
				public void onClick(View v) {						// When I push bt1 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_inst_meter.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt2 = (Button) this.findViewById(R.id.btInstPower);	// Assign button butBusqueda to bt2
		if (bt2 != null)										
		{
			bt2.setOnClickListener(new OnClickListener() { 			// Create a listener for bt2	

				@Override
				public void onClick(View v) {						// When I push bt2 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_inst_power.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt3 = (Button) this.findViewById(R.id.btHistVolt);	// Assign button butBusqueda to bt3
		if (bt3 != null)										
		{
			bt3.setOnClickListener(new OnClickListener() { 			// Create a listener for bt3 	

				@Override
				public void onClick(View v) {						// When I push bt3 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_voltage.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt4 = (Button) this.findViewById(R.id.btHistCurr);	// Assign button butBusqueda to bt4
		if (bt4 != null)										
		{
			bt4.setOnClickListener(new OnClickListener() { 			// Create a listener for bt4 	

				@Override
				public void onClick(View v) {						// When I push bt4 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_current.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt5 = (Button) this.findViewById(R.id.btHistFreq);	// Assign button butBusqueda to bt5
		if (bt5 != null)										
		{
			bt5.setOnClickListener(new OnClickListener() { 			// Create a listener for bt5 	

				@Override
				public void onClick(View v) {						// When I push bt5 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_frequency.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt6 = (Button) this.findViewById(R.id.btHistPact);	// Assign button butBusqueda to bt6
		if (bt6 != null)										
		{
			bt6.setOnClickListener(new OnClickListener() { 			// Create a listener for bt6	

				@Override
				public void onClick(View v) {						// When I push bt6 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_pact.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt7 = (Button) this.findViewById(R.id.btHistPrea);	// Assign button butBusqueda to bt7
		if (bt7 != null)										
		{
			bt7.setOnClickListener(new OnClickListener() { 			// Create a listener for bt7	

				@Override
				public void onClick(View v) {						// When I push bt7 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_prea.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt8 = (Button) this.findViewById(R.id.btHistPapp);	// Assign button butBusqueda to bt8
		if (bt8 != null)										
		{
			bt8.setOnClickListener(new OnClickListener() { 			// Create a listener for bt8 	

				@Override
				public void onClick(View v) {						// When I push bt8 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_papp.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt9 = (Button) this.findViewById(R.id.btHistEact);	// Assign button butBusqueda to bt9
		if (bt9 != null)										
		{
			bt9.setOnClickListener(new OnClickListener() { 			// Create a listener for bt9 	

				@Override
				public void onClick(View v) {						// When I push bt9 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_eact.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt10 = (Button) this.findViewById(R.id.btHistErea);	// Assign button butBusqueda to bt10
		if (bt10 != null)										
		{
			bt10.setOnClickListener(new OnClickListener() { 			// Create a listener for bt10 	

				@Override
				public void onClick(View v) {						// When I push bt10 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_erea.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt11 = (Button) this.findViewById(R.id.btHistEapp);	// Assign button butBusqueda to bt11
		if (bt11 != null)										
		{
			bt11.setOnClickListener(new OnClickListener() { 			// Create a listener for bt11	

				@Override
				public void onClick(View v) {						// When I push bt11 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_eapp.class); 
					startActivity(intent);
				
				}

			} );
		}
		
		Button bt12 = (Button) this.findViewById(R.id.btHistCost);	// Assign button butBusqueda to bt12
		if (bt12 != null)										
		{
			bt12.setOnClickListener(new OnClickListener() { 			// Create a listener for bt12	

				@Override
				public void onClick(View v) {						// When I push bt12 ...
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(getBaseContext(), HEmet_hist_cost.class); 
					startActivity(intent);
				
				}

			} );
		}
		
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hemet_menu, menu);
		return true;
	}

}
