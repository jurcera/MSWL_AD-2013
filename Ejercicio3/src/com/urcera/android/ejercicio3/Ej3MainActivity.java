package com.urcera.android.ejercicio3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Ej3MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ej3_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ej3_main, menu);
		return true;
	}

}
