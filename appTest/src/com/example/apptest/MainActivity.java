package com.example.apptest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);			
		SensorsReading reader = new SensorsReading((SensorManager) getSystemService(Context.SENSOR_SERVICE));
		reader.start();		
	}	
}
