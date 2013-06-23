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

public class HEmet_hist_erea extends Activity {
	
	private XYPlot mySimpleXYPlot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hemet_hist_erea);
		
		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlotHistErea);
		
		Number[] dayValues = {0.040, 0.160, 0.490, 0.720, 0.740, 0.900, 1.20, 1.26, 1.59, 1.68, 1.77, 1.83, 1.98, 2.02, 2.09, 2.98, 3.18, 3.22, 5.00, 6.08, 7.49, 7.60, 8.12, 9.69};
		printGraph(" -- " + getString(R.string.label_react) + " - " + getString(R.string.label_dayly) + " -- " , dayValues, getString(R.string.label_time),getString(R.string.unit_kVoltAmpReacth));
		
		Button bt1 = (Button) this.findViewById(R.id.btHistEreaDay);	// Asignamos el boton1 a bt1
		if (bt1 != null)										
		{
			bt1.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] dayValues = {0.040, 0.160, 0.490, 0.720, 0.740, 0.900, 1.20, 1.26, 1.59, 1.68, 1.77, 1.83, 1.98, 2.02, 2.09, 2.98, 3.18, 3.22, 5.00, 6.08, 7.49, 7.60, 8.12, 9.69};
					
					printGraph(getString(R.string.label_react) + " - " + getString(R.string.label_dayly) , dayValues, getString(R.string.label_time),getString(R.string.unit_kVoltAmpReacth));
				}
			} );
		}
		
		Button bt2 = (Button) this.findViewById(R.id.btHistEreaWeek);	// Asignamos el boton1 a bt1
		if (bt2 != null)										
		{
			bt2.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] weekValues = {7.02, 8.50, 7.89, 8.34, 7.06, 8.45, 9.69};
					
					printGraph(getString(R.string.label_react) + " - " + getString(R.string.label_weekly) , weekValues, getString(R.string.label_time),getString(R.string.unit_kVoltAmpReacth));
					
					
				}
			} );
		}
		
		Button bt3 = (Button) this.findViewById(R.id.btHistEreaMonth);	// Asignamos el boton1 a bt1
		if (bt3 != null)										
		{
			bt3.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] monthValues = { 9.02, 9.40, 9.89, 9.94, 9.16, 9.45, 9.09, 12.12, 12.50, 12.89, 9.94, 10.06, 10.45, 11.19, 12.02, 12.50, 11.39, 10.94, 11.26, 10.65, 11.09, 11.02, 11.50, 11.69, 10.94, 11.16, 10.45, 11.09, 11.06, 11.45, 11.79};
					
					printGraph(getString(R.string.label_react) + " - " + getString(R.string.label_monthly) , monthValues, getString(R.string.label_time),getString(R.string.unit_kVoltAmpReacth));
				}
			} );
		}
		
		Button bt4 = (Button) this.findViewById(R.id.btHistEreaYear);	// Asignamos el boton1 a bt1
		if (bt4 != null)										
		{
			bt4.setOnClickListener(new OnClickListener() { 		// Crea un listener para en boton1 	
				
				@Override
				public void onClick(View v) {					// Llama a actividad1 al pulsar boton1
										
					Number[] yearValues = {12.02, 11.03, 12.01, 11.0, 10.95, 11.02, 11.02, 11.01, 10.96, 11.99, 11.01, 11.00};
					
					printGraph(getString(R.string.label_react) + " - " + getString(R.string.label_yearly) , yearValues, getString(R.string.label_time),getString(R.string.unit_kVoltAmpReacth));
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
		getMenuInflater().inflate(R.menu.hemet_hist_erea, menu);
		return true;
	}

}
