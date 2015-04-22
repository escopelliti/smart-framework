package com.example.apptest;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.hardware.SensorManager;
import android.hardware.Sensor;

public class SensorsReading extends Activity{

	public SensorsReading(SensorManager sensorManager){
				
		this.sl = new ArrayList<SensorListener>(10);
		this.test = new Network(this.sl);
		this.test.start();		
		this.sensorManager = sensorManager;		
	}
	
	public void start(){
		
		int count;
		List<Sensor> sensors;
		
		count = 0;
		sensors = getSensor();		
		while(count < sensors.size() && !sensors.equals(null)){

			Sensor sensor = sensors.get(count);
			SensorListener asl = new SensorListener(sensor);
			this.sl.add(asl);			
			installSensorListener(sensor,asl);
			count++;
		}
	}
	
	public List<Sensor> getSensor(){
		
		List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
		if(sensorList.isEmpty())			
			return null;
		return sensorList;
	}
	
	public void installSensorListener(Sensor s,SensorListener sl) {
		sensorManager.registerListener(sl, s, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	private Network test;
	private ArrayList<SensorListener> sl;
	private SensorManager sensorManager;
}
