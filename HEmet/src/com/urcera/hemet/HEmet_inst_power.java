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

import java.text.DecimalFormat;
import java.util.Arrays;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HEmet_inst_power extends Activity {
	
	private XYPlot mySimpleXYPlot;
	private TextView tvPact = null;
	private TextView tvPrea = null;
	private TextView tvPapp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_inst_power);
		
		tvPact = (TextView) this.findViewById(R.id.tv_value_pact);
		tvPrea = (TextView) this.findViewById(R.id.tv_value_prea);
		tvPapp = (TextView) this.findViewById(R.id.tv_value_papp);
		
		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlotPower);
		
		Number[] pactValues = {12.123, 13.228, 14.000, 12.989, 12.500, 13.233, 14.233, 11.233, 12.329, 12.331, 12.500, 13.233};
		printGraph(" -- " + getString(R.string.label_act) + " -- ", pactValues, getString(R.string.label_time),getString(R.string.unit_kWat));
		
		Button bt1 = (Button) this.findViewById(R.id.btPact);	// Asignamos el boton1 a bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] pactValues = {12.123, 13.228, 14.000, 12.989, 12.500, 13.233, 14.233, 11.233, 12.329, 12.331, 12.000, 11.800};
					
					tvPact.setText(String.valueOf(pactValues[pactValues.length - 1]));
					printGraph(getString(R.string.label_act), pactValues, getString(R.string.label_time),getString(R.string.unit_kWat));
				}
			} );
		}
		
		Button bt2 = (Button) this.findViewById(R.id.btPrea);	// Asignamos el boton1 a bt1
		if (bt2 != null)										
		{
			bt2.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] preaValues = {0.456, 3.845, 3.034, 2.922, 2.589, 3.098, 3.456, 3.231, 2.967, 2.832, 2.889, 3.654};
					
					tvPrea.setText(String.valueOf(preaValues[preaValues.length - 1]));
					printGraph(getString(R.string.label_react), preaValues, getString(R.string.label_time),getString(R.string.unit_kVoltAmpReact));
					
					
				}
			} );
		}
		
		Button bt3 = (Button) this.findViewById(R.id.btPapp);	// Asignamos el boton1 a bt1
		if (bt3 != null)										
		{
			bt3.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] pappValues = {12.230, 13.528, 14.400, 13.389, 12.900, 13.533, 14.633, 11.433, 12.729, 12.831, 12.600, 12.400};
					
					tvPapp.setText(String.valueOf(pappValues[pappValues.length - 1]));
					printGraph(getString(R.string.label_app), pappValues, getString(R.string.label_time),getString(R.string.unit_kVoltAmp));
				}
			} );
		}
		
		
	}
	
	public void printGraph(String tit, Number[] series1Numbers, String xLabel, String yLabel) {

		mySimpleXYPlot.clear();

		// Turn the above arrays into XYSeries':
		XYSeries series1 = new SimpleXYSeries(
				Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
				tit);                             		// Set the display title of the series "Voltage"


		// Create a formatter to use for drawing a series using LineAndPointRenderer:
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Color.rgb(0, 200, 0),                   // line color
				Color.rgb(0, 100, 0),                   // point color
				null,
				new PointLabelFormatter(Color.WHITE));                                  // fill color (none)

		// add a new series' to the xyplot:
		mySimpleXYPlot.addSeries(series1, series1Format);

		
		// reduce the number of range labels
		mySimpleXYPlot.setTicksPerRangeLabel(2);
		mySimpleXYPlot.setTicksPerDomainLabel(1);
		mySimpleXYPlot.setTitle(tit);
		mySimpleXYPlot.setDomainLabel(xLabel);
		mySimpleXYPlot.setRangeLabel(yLabel);

		// draw a domain line for every element plotted on the domain:
		mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);

		// get rid of the decimal place on the display:
		mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("#"));
		
		
		mySimpleXYPlot.redraw();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hemet_inst_power, menu);
		return true;
	}

}
