package com.rec.uom;

public enum Mass {
	GRAMS("grams", "g", 1),
	KILOGRAMS("kilograms", "kg", 0.001);
	
	private String name;
	private String display;
	private double convertionRatio;
	
	private Mass(String name, String val, double ratio) {
		this.name = name;
		this.display = val;
		this.convertionRatio = ratio;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDisplay() {
		return this.display;
	}
	
	public double getConvertionRatio() {
		return convertionRatio;
	}
}
