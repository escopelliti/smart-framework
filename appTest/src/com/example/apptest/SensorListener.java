package com.example.apptest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorListener implements SensorEventListener {
    
	public SensorListener(Sensor s){		
		this.sensing = new SensingProcess();
		this.sensing.setSensorName(s.getName());
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {					
	}
		
	public SensingProcess getSensing(){
		
		return this.sensing;
	}
	
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		
		this.sensing.setValues(arg0.values);
	}		
	
	private SensingProcess sensing;
}
