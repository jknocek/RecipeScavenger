package com.rec.uom;

public enum Volumn {
	TEASPOONS("teaspoons","tsp", 202.884136),
	FLUID_OUNCES("fluid ounces","fl oz", 33.8140227),
	TABLESPOONS("tablespoons","tbsp", 67.6280454),
	MILLILITERS("milliliters","ml", 1000),
	LITERS("liters", "l", 1),
	KILOLITERS("kiloliters", "kl", 0.001);
	
	private String name;
	private String display;
	private double convertionRatio;
	
	private Volumn(String name, String val, double ratio) {
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
