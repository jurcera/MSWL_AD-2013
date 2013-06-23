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

public class HEmet_hist_frequency extends Activity {
	
	private XYPlot mySimpleXYPlot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_hist_frequency);
		
		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlotHistFreq);
		
		Number[] dayValues = {49.40, 49.50, 49.90, 50.02, 50.50, 50.09, 50.08, 50.06, 50.09, 49.98, 49.87, 49.89, 49.98, 50.02, 50.09, 50.98, 50.98, 50.00, 50.00, 50.08, 50.09, 50.00, 50.12, 50.09};
		printGraph(" -- " + getString(R.string.label_freq) + " - " + getString(R.string.label_dayly) + " -- " , dayValues, getString(R.string.label_time),getString(R.string.unit_hertz));
		
		Button bt1 = (Button) this.findViewById(R.id.btHistFreqDay);	// Asignamos el boton1 a bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] dayValues = {49.40, 49.50, 49.90, 50.02, 50.50, 50.09, 50.08, 50.06, 50.09, 49.98, 49.98, 50.02, 50.09, 50.98, 50.98, 50.00, 50.00, 50.98, 50.98, 50.00, 50.00, 50.08, 50.09, 50.00};
					
					printGraph(getString(R.string.label_freq) + " - " + getString(R.string.label_dayly) , dayValues, getString(R.string.label_time),getString(R.string.unit_hertz));
				}
			} );
		}
		
		Button bt2 = (Button) this.findViewById(R.id.btHistFreqWeek);	// Asignamos el boton1 a bt1
		if (bt2 != null)										
		{
			bt2.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] weekValues = { 50.02, 50.50, 50.09, 50.08, 50.06, 50.09, 49.98};
					
					printGraph(getString(R.string.label_freq) + " - " + getString(R.string.label_weekly) , weekValues, getString(R.string.label_time),getString(R.string.unit_hertz));
					
					
				}
			} );
		}
		
		Button bt3 = (Button) this.findViewById(R.id.btHistFreqMonth);	// Asignamos el boton1 a bt1
		if (bt3 != null)										
		{
			bt3.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] monthValues = { 50.02, 50.50, 50.09, 50.08, 50.06, 50.09, 49.98, 50.50, 50.09, 50.08, 50.06, 50.09, 49.98, 49.98, 50.02, 50.09, 50.98, 50.98, 50.00, 50.00, 50.50, 50.09, 50.08, 50.06, 50.09, 49.98, 49.98, 50.02, 50.09, 50.18, 50.00};
					
					printGraph(getString(R.string.label_freq) + " - " + getString(R.string.label_monthly) , monthValues, getString(R.string.label_time),getString(R.string.unit_hertz));
				}
			} );
		}
		
		Button bt4 = (Button) this.findViewById(R.id.btHistFreqYear);	// Asignamos el boton1 a bt1
		if (bt4 != null)										
		{
			bt4.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] yearValues = {50.02, 50.03, 50.01, 50.0, 49.95, 50.02, 50.02, 50.01, 49.96, 49.99, 50.01, 50.0};
					
					printGraph(getString(R.string.label_freq) + " - " + getString(R.string.label_yearly) , yearValues, getString(R.string.label_time),getString(R.string.unit_hertz));
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
		getMenuInflater().inflate(R.menu.hemet_hist_frequency, menu);
		return true;
	}

	
}
