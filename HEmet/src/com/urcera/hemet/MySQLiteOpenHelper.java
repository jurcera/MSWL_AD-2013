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

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "HEmetdB";
    private static final int DB_VERSION = 1;
    
    // Tables definition
    
    public static class TableInst{
        public static String TB_NAME = "instantdata";
        public static String COLUM_ID = "_id";
        public static String COLUM_DATE = "date";
        public static String COLUM_VOLT = "voltage";
        public static String COLUM_CURR = "current";
        public static String COLUM_FREQ = "frequency";
        public static String COLUM_PACT = "poweract";
        public static String COLUM_PREA = "powerreact";
        public static String COLUM_PAPP = "powerapp";
        public static String COLUM_EACT = "energyact";
        public static String COLUM_EREA = "energyreact";
        public static String COLUM_EAPP = "energyapp";
    }
    
    public static class TableHour{
        public static String TB_NAME = "hourdata";
        public static String COLUM_ID = "_id";
        public static String COLUM_DATE = "date";
        public static String COLUM_VOLT = "voltage";
        public static String COLUM_CURR = "current";
        public static String COLUM_FREQ = "frequency";
        public static String COLUM_PACT = "poweract";
        public static String COLUM_PREA = "powerreact";
        public static String COLUM_PAPP = "powerapp";
        public static String COLUM_EACT = "energyact";
        public static String COLUM_EREA = "energyreact";
        public static String COLUM_EAPP = "energyapp";
    }
    
    public static class TableDay{
        public static String TB_NAME = "daydata";
        public static String COLUM_ID = "_id";
        public static String COLUM_DATE = "date";
        public static String COLUM_VOLT = "voltage";
        public static String COLUM_CURR = "current";
        public static String COLUM_FREQ = "frequency";
        public static String COLUM_PACT = "poweract";
        public static String COLUM_PREA = "powerreact";
        public static String COLUM_PAPP = "powerapp";
        public static String COLUM_EACT = "energyact";
        public static String COLUM_EREA = "energyreact";
        public static String COLUM_EAPP = "energyapp";
    }
    
    public static class TableMonth{
        public static String TB_NAME = "monthdata";
        public static String COLUM_ID = "_id";
        public static String COLUM_DATE = "date";
        public static String COLUM_VOLT = "voltage";
        public static String COLUM_CURR = "current";
        public static String COLUM_FREQ = "frequency";
        public static String COLUM_PACT = "poweract";
        public static String COLUM_PREA = "powerreact";
        public static String COLUM_PAPP = "powerapp";
        public static String COLUM_EACT = "energyact";
        public static String COLUM_EREA = "energyreact";
        public static String COLUM_EAPP = "energyapp";
    }
    
    private static final String DB_CREATE1 = "CREATE TABLE" + TableInst.TB_NAME 
    		+ "(" + TableInst.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ TableInst.COLUM_DATE + " TEXT NOT NULL, "
    		+ TableInst.COLUM_VOLT + " REAL NOT NULL, "
    		+ TableInst.COLUM_CURR + " REAL NOT NULL, "
    		+ TableInst.COLUM_FREQ + " REAL NOT NULL, "
    		+ TableInst.COLUM_PACT + " REAL NOT NULL, "
    		+ TableInst.COLUM_PREA + " REAL NOT NULL, "
    		+ TableInst.COLUM_PAPP + " REAL NOT NULL, "
    		+ TableInst.COLUM_EACT + " REAL NOT NULL, "
    		+ TableInst.COLUM_EREA + " REAL NOT NULL, "
    		+ TableInst.COLUM_EAPP + " REAL NOT NULL);";
    
    private static final String DB_CREATE2 = "CREATE TABLE" + TableHour.TB_NAME 
    		+ "(" + TableHour.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ TableHour.COLUM_DATE + " TEXT NOT NULL, "
    		+ TableHour.COLUM_VOLT + " REAL NOT NULL, "
    		+ TableHour.COLUM_CURR + " REAL NOT NULL, "
    		+ TableHour.COLUM_FREQ + " REAL NOT NULL, "
    		+ TableHour.COLUM_PACT + " REAL NOT NULL, "
    		+ TableHour.COLUM_PREA + " REAL NOT NULL, "
    		+ TableHour.COLUM_PAPP + " REAL NOT NULL, "
    		+ TableHour.COLUM_EACT + " REAL NOT NULL, "
    		+ TableHour.COLUM_EREA + " REAL NOT NULL, "
    		+ TableHour.COLUM_EAPP + " REAL NOT NULL);";
    
    private static final String DB_CREATE3 = "CREATE TABLE" + TableDay.TB_NAME 
    		+ "(" + TableDay.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ TableDay.COLUM_DATE + " TEXT NOT NULL, "
    		+ TableDay.COLUM_VOLT + " REAL NOT NULL, "
    		+ TableDay.COLUM_CURR + " REAL NOT NULL, "
    		+ TableDay.COLUM_FREQ + " REAL NOT NULL, "
    		+ TableDay.COLUM_PACT + " REAL NOT NULL, "
    		+ TableDay.COLUM_PREA + " REAL NOT NULL, "
    		+ TableDay.COLUM_PAPP + " REAL NOT NULL, "
    		+ TableDay.COLUM_EACT + " REAL NOT NULL, "
    		+ TableDay.COLUM_EREA + " REAL NOT NULL, "
    		+ TableDay.COLUM_EAPP + " REAL NOT NULL);";
    		
    private static final String DB_CREATE4 = "CREATE TABLE" + TableMonth.TB_NAME 
    		+ "(" + TableMonth.COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ TableMonth.COLUM_DATE + " TEXT NOT NULL, "
    		+ TableMonth.COLUM_VOLT + " REAL NOT NULL, "
    		+ TableMonth.COLUM_CURR + " REAL NOT NULL, "
    		+ TableMonth.COLUM_FREQ + " REAL NOT NULL, "
    		+ TableMonth.COLUM_PACT + " REAL NOT NULL, "
    		+ TableMonth.COLUM_PREA + " REAL NOT NULL, "
    		+ TableMonth.COLUM_PAPP + " REAL NOT NULL, "
    		+ TableMonth.COLUM_EACT + " REAL NOT NULL, "
    		+ TableMonth.COLUM_EREA + " REAL NOT NULL, "
    		+ TableMonth.COLUM_EAPP + " REAL NOT NULL);";
  
    
	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DB_CREATE1);
		db.execSQL(DB_CREATE2);
		db.execSQL(DB_CREATE3);
		db.execSQL(DB_CREATE4);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DELETE TABLE IF EXISTS " + TableInst.TB_NAME);
		db.execSQL("DELETE TABLE IF EXISTS " + TableHour.TB_NAME);
		db.execSQL("DELETE TABLE IF EXISTS " + TableDay.TB_NAME);
		db.execSQL("DELETE TABLE IF EXISTS " + TableMonth.TB_NAME);
		onCreate(db);
	}
	
	// Adding new electric data to Tablehour
    void addHoureData(eData edata) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(TableHour.COLUM_DATE, edata.getDate()); // date
        values.put(TableHour.COLUM_VOLT, edata.getVoltage()); // volatage
        values.put(TableHour.COLUM_CURR, edata.getCurrent());
        values.put(TableHour.COLUM_FREQ, edata.getFrequency());
        values.put(TableHour.COLUM_PACT, edata.getPoweract());
        values.put(TableHour.COLUM_PREA, edata.getPowerreact());
        values.put(TableHour.COLUM_PAPP, edata.getPowerapp());
        values.put(TableHour.COLUM_EACT, edata.getEnergyact());
        values.put(TableHour.COLUM_EREA, edata.getEnergyreact());
        values.put(TableHour.COLUM_EAPP, edata.getEnergyapp());
 
        // Inserting Row
        db.insert(TableHour.TB_NAME, null, values);
        db.close(); // Closing database connection
    }
    
    

}
