package com.urcera.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Ej2Actividad1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ej2_actividad1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej2_actividad1, menu);
		return true;
	}

}
