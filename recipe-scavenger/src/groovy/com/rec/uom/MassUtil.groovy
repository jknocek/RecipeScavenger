package com.rec.uom

class MassUtil {
	
	static def getBaseUom() {
		return Mass.GRAMS.getName();
	}
	
	static def getBaseUomDisplay() {
		return Mass.GRAMS.getDisplay();
	}
	
	static def getDisplayAmount(String displayUom, double amount) {
		if(displayUom == Mass.KILOGRAMS.getDisplay()) {
			return (amount * Mass.KILOGRAMS.getConvertionRatio())
		}
	}

}
