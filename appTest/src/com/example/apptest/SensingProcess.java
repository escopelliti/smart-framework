package com.example.apptest;

public class SensingProcess {
	
	public SensingProcess(String SensorName, Float[] values){
		
		this.SensorName = SensorName;
		this.values = values;
	}
	
	public SensingProcess(){
		
		this.SensorName = null;
		this.values = new Float[3];
	}
	
	public void setSensorName(String SensorName){
		
		this.SensorName = SensorName;
	}

	public void setValues(float[] values){
		
		this.values[0] = values[0];
		this.values[1] = values[1];
		this.values[2] = values[2];
	}
	
	public String getValueX(){
		
		Float x = this.values[0];
		return x.toString();
	}
	
	public String getValueY(){
		
		Float y = this.values[1];
		return y.toString();
	}
	
	public String getValueZ(){
		
		Float z = this.values[2];
		return z.toString();
	}
	
	public String getSensorName(){
		
		return this.SensorName;
	}
	
	private String SensorName;
	private Float[] values;
}
