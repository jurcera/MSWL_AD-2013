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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

public class HEmet_hist_cost extends Activity {
	
	private XYPlot mySimpleXYPlot;
	private double kWhPrice = 0.150938;  // €/kWh -- 15/05/2013
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_hist_cost);
		
		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlotHistCost);

		Number[] monthValues = {0.20, 0.50, 0.90, 1.02, 1.50, 1.69, 1.90, 2.26, 2.59, 3.98, 5.87, 9.89, 11.98, 13.02, 16.09, 17.98, 18.98, 22.00, 24.00, 27.08, 30.09, 33.00, 35.12, 37.09};
		printGraph(" -- " + getString(R.string.label_cost) + " - " + getString(R.string.label_dayly) + " -- " , monthValues, getString(R.string.label_time),getString(R.string.unit_euro));

		
		Button bt1 = (Button) this.findViewById(R.id.btHistCostDay);	// Asignamos el boton1 a bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	

				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1

					Number[] dayValues = {0.20, 0.50, 0.90, 1.02, 1.50, 1.69, 1.90, 2.26, 2.59, 3.98, 5.87, 9.89, 11.98, 13.02, 16.09, 17.98, 18.98, 22.00, 24.00, 27.08, 29.09, 33.00, 34.12, 34.69};
					//Number[] dayValues = {0.00, 0.50, 1.00, 1.50, 2.34};
					
					//Double[] suso34 = new Double[dayValues.length];
					//suso34[1] = dayValues[1].doubleValue();
					//Math.floor(x * 100) / 100
					
					// Obtain cost
					for ( int i=0; i < dayValues.length; i++) {
						dayValues[i] = Math.floor((dayValues[i].doubleValue() * kWhPrice) * 100) / 100;   // max 2 decimals
						Log.d("CalculateCost", dayValues[i].toString());
					}
					
							
					printGraph(getString(R.string.label_cost) + " - " + getString(R.string.label_dayly) , dayValues, getString(R.string.label_time),getString(R.string.unit_euro));
				}
			} );
		}

		Button bt2 = (Button) this.findViewById(R.id.btHistCostWeek);	// Asignamos el boton1 a bt1
		if (bt2 != null)										
		{
			bt2.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	

				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1

					Number[] weekValues = {45.02, 44.50, 43.89, 49.94, 50.06, 41.45, 37.09};
					
					// Obtain cost
					for ( int i=0; i < weekValues.length; i++) {
						weekValues[i] = Math.floor((weekValues[i].doubleValue() * kWhPrice) * 100) / 100;   // max 2 decimals
						Log.d("CalculateCost", weekValues[i].toString());
					}

					printGraph(getString(R.string.label_cost) + " - " + getString(R.string.label_weekly) , weekValues, getString(R.string.label_time),getString(R.string.unit_euro));


				}
			} );
		}

		Button bt3 = (Button) this.findViewById(R.id.btHistCostMonth);	// Asignamos el boton1 a bt1
		if (bt3 != null)										
		{
			bt3.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	

				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1

					Number[] monthValues = { 50.02, 50.50, 45.02, 44.50, 43.89, 49.94, 50.06, 41.45, 37.09, 50.08, 45.02, 44.50, 43.89, 49.94, 50.06, 50.09, 50.98, 50.98, 50.00, 50.00, 50.50, 50.09, 50.08, 50.06, 45.02, 44.50, 43.89, 49.94, 50.06, 41.45, 37.09};

					// Obtain cost
					for ( int i=0; i < monthValues.length; i++) {
						monthValues[i] = Math.floor((monthValues[i].doubleValue() * kWhPrice) * 100) / 100;   // max 2 decimals
						Log.d("CalculateCost", monthValues[i].toString());
					}
					
					printGraph(getString(R.string.label_cost) + " - " + getString(R.string.label_monthly) , monthValues, getString(R.string.label_time),getString(R.string.unit_euro));
				}
			} );
		}

		Button bt4 = (Button) this.findViewById(R.id.btHistCostYear);	// Asignamos el boton1 a bt1
		if (bt4 != null)										
		{
			bt4.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	

				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1

					Number[] yearValues = {250.02, 240.03, 230.01, 233.0, 199.95, 180.02, 250.02, 260.01, 249.96, 219.99, 225.01, 250.0};

					// Obtain cost
					for ( int i=0; i < yearValues.length; i++) {
						yearValues[i] = Math.floor((yearValues[i].doubleValue() * kWhPrice) * 100) / 100;   // max 2 decimals
						Log.d("CalculateCost", yearValues[i].toString());
					}
					
					printGraph(getString(R.string.label_cost) + " - " + getString(R.string.label_yearly) , yearValues, getString(R.string.label_time),getString(R.string.unit_euro));
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
		getMenuInflater().inflate(R.menu.hemet_hist_cost, menu);
		return true;
	}

}
