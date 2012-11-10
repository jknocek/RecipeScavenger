package com.rec.uom

class MassUtil {
	
	static def getBaseUom() {
		return Mass.GRAMS.getName();
	}
	
	static def getBaseUomDisplay() {
		return Mass.GRAMS.getDisplay();
	}
	
	static def getPossibleUoms() {
		return Mass.values()
	}
	
	static def getUomName(String origUom) {
		for(uom in Mass.values()) {
			if(uom.toString() == origUom) {
				return uom.getName()
			}
		}
	}
	
	static def getUomDisplay(String origUom) {
		for(uom in Mass.values()) {
			if(uom.toString() == origUom) {
				return uom.getDisplay()
			}
		}
	}
	
	static def getCurrentUom(String uomName) {
		for(uom in Mass.values()) {
			if(uom.getName() == uomName) {
				return uom
			}
		}
	}
	
	static double getDisplayAmount(String origUom, String displayUom, double amount) {
		def orignalUom 
		for(uom in Mass.values()) {
			if(uom.toString() == origUom) {
				orignalUom = uom
			}
		}
		
		def newUom
		for(uom in Mass.values()) {
			if(uom.toString() == displayUom) {
				newUom = uom
			}
		}
		
		double baseAmount = orignalUom.convertionRatio * amount
		double newAmount = newUom.convertionRatio * baseAmount
		
		return newAmount
	}

}
