package com.rec.uom

class VolumnUtil {
	static def getBaseUom() {
		return Volumn.LITERS.getName();
	}
	
	static def getBaseUomDisplay() {
		return Volumn.LITERS.getDisplay();
	}
	
	static def getPossibleUoms() {
		return Volumn.values()
	}
	
	static def getUomName(String origUom) {
		for(uom in Volumn.values()) {
			if(uom.toString() == origUom) {
				return uom.getName()
			}
		}
	}
	
	static def getUomDisplay(String origUom) {
		for(uom in Volumn.values()) {
			if(uom.toString() == origUom) {
				return uom.getDisplay()
			}
		}
	}
	
	static def getCurrentUom(String uomName) {
		for(uom in Volumn.values()) {
			if(uom.getName() == uomName) {
				return uom
			}
		}
	}
	
	static double getDisplayAmount(String origUom, String displayUom, double amount) {
		def orignalUom 
		for(uom in Volumn.values()) {
			if(uom.toString() == origUom) {
				orignalUom = uom
			}
		}
		
		def newUom
		for(uom in Volumn.values()) {
			if(uom.toString() == displayUom) {
				newUom = uom
			}
		}
		
		def baseAmount = amount / orignalUom.convertionRatio
		def newAmount = newUom.convertionRatio * baseAmount
		
		return newAmount
	}
}
