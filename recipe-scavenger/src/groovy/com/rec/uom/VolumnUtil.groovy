package com.rec.uom

class VolumnUtil {
	static def getBaseUom() {
		return Volumn.LITERS.getName();
	}
	
	static def getBaseUomDisplay() {
		return Mass.GRAMS.getDisplay();
	}
	
	static def getDisplayAmount(String displayUom, double amount) {
		if(displayUom == Volumn.KILOLITERS.getDisplay()) {
			return (amount * Volumn.KILOLITERS.getConvertionRatio())
		}
	}
}
