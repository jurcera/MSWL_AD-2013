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

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;


public class HEmet_inst_meter extends Activity {
	
	private XYPlot mySimpleXYPlot;
	private TextView tvVolt = null;
	private TextView tvCurr = null;
	private TextView tvFreq = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_inst_meter);
		
		tvVolt = (TextView) this.findViewById(R.id.tv_value_volt1);
		tvCurr = (TextView) this.findViewById(R.id.tv_value_curr1);
		tvFreq = (TextView) this.findViewById(R.id.tv_value_freq1);
		
		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlotVolt);
		
		Number[] voltageValues = {232.4, 232.8, 233.0, 232.9, 232.5, 233.0, 233.0, 233.1, 232.9, 232.8, 233.5, 233.8};
		printGraph(" -- " + getString(R.string.label_volt) + " --", voltageValues, getString(R.string.label_time),getString(R.string.unit_volt));
		
		Button bt1 = (Button) this.findViewById(R.id.btVolt);	// Asignamos el boton1 a bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] voltageValues = {232.4, 232.8, 233.0, 232.9, 232.5, 233.0, 233.0, 233.1, 232.9, 233.1, 233.0, 232.9};
					
					tvVolt.setText(String.valueOf(voltageValues[voltageValues.length - 1]));
					printGraph(getString(R.string.label_volt), voltageValues, getString(R.string.label_time),getString(R.string.unit_volt));
				}
			} );
		}
		
		Button bt2 = (Button) this.findViewById(R.id.btCurr);	// Asignamos el boton1 a bt1
		if (bt2 != null)										
		{
			bt2.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] currentValues = {3.2, 3.8, 3.0, 2.9, 2.5, 3.0, 3.0, 3.1, 2.9, 2.8, 3.6, 4.0};
					
					tvCurr.setText(String.valueOf(currentValues[currentValues.length - 1]));
					printGraph(getString(R.string.label_curr), currentValues, getString(R.string.label_time),getString(R.string.unit_ampere));
					
					
				}
			} );
		}
		
		Button bt3 = (Button) this.findViewById(R.id.btFreq);	// Asignamos el boton1 a bt1
		if (bt3 != null)										
		{
			bt3.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] frequencyValues = {50.00, 49.83, 50.00, 50.20, 50.50, 51.00, 50.95, 50.10, 50.90, 50.80, 50.85, 49.95};
					
					tvFreq.setText(String.valueOf(frequencyValues[frequencyValues.length - 1]));
					printGraph(getString(R.string.label_freq), frequencyValues, getString(R.string.label_time),getString(R.string.unit_hertz));
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

		// same as above
		//XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Current");


		// Create a formatter to use for drawing a series using LineAndPointRenderer:
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Color.rgb(0, 200, 0),                   // line color
				Color.rgb(0, 100, 0),                   // point color
				null,
				new PointLabelFormatter(Color.WHITE));                                  // fill color (none)

		// add a new series' to the xyplot:
		mySimpleXYPlot.addSeries(series1, series1Format);

		// same as above:
		//mySimpleXYPlot.addSeries(series2,	new LineAndPointFormatter(Color.rgb(0, 0, 200), Color.rgb(0, 0, 100), null));

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
		getMenuInflater().inflate(R.menu.hemet_inst_meter, menu);
		return true;
	}

}
