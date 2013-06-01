/*
 *
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

package com.urcera.android.ttsearch;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity {
	
	private static final String OPT_SEARCH = "Search";
	private static final String OPT_SEARCH_DEF = "http://search.twitter.com/search.json?q=";
	private static final String OPT_RANGE = "Distancia";
	private static final String OPT_RANGE_DEF = "100";
	private static final String OPT_UNITS = "Unidades";
	private static final String OPT_UNITS_DEF = "km";
	private static final String OPT_RPP = "ResultadosPorPag";
	private static final String OPT_RPP_DEF = "25";
	private static final String OPT_LOCMAN = "LocManual";
	private static final boolean OPT_LOCMAN_DEF = true;
	private static final String OPT_LAT = "Latitud";
	private static final String OPT_LAT_DEF = "40.417";
	private static final String OPT_LON = "Longitud";
	private static final String OPT_LON_DEF = "-3.703";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settings);
	}
	
	public static String getUrlSearch(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_SEARCH, OPT_SEARCH_DEF);
	}
	
	public static String getDistancia(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_RANGE, OPT_RANGE_DEF);
	}
	
	public static String getUnidades(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_UNITS, OPT_UNITS_DEF);
	}
	
	public static String getResultadosPorPag(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_RPP, OPT_RPP_DEF);
	}
	
	public static boolean getlocManual(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_LOCMAN, OPT_LOCMAN_DEF);
	}
		
	public static String getLatitud(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_LAT, OPT_LAT_DEF);
	}
	
	public static String getLongitud(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).getString(OPT_LON, OPT_LON_DEF);
	}
}
