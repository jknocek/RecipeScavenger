package com.rec.uom

class UOM {
	
	static def getBaseUom(char baseUom) {
		if(baseUom == 'v') {
			return VolumnUtil.getBaseUom()
		} else if(baseUom == 'm') {
			return MassUtil.getBaseUom()
		} else if(baseUom == 'u') {
			""
		}
	}
	
	static def getPossibleUoms(char baseUom) {
		if(baseUom == 'v') {
			return VolumnUtil.getPossibleUoms()
		} else if(baseUom == 'm') {
			return MassUtil.getPossibleUoms()
		} else if(baseUom == 'u') {
			return null
		}
	}
	
	static def getUomName(char baseUom, String uom) {
		if(baseUom == 'v') {
			return VolumnUtil.getUomName(uom)
		} else if(baseUom == 'm') {
			return MassUtil.getUomName(uom)
		} else if(baseUom == 'u') {
			return ""
		}
	}
	
	static def getUomDisplay(char baseUom, String uom) {
		if(baseUom == 'v') {
			return VolumnUtil.getUomDisplay(uom)
		} else if(baseUom == 'm') {
			return MassUtil.getUomDisplay(uom)
		} else if(baseUom == 'u') {
			return ""
		}
	}
	
	static def getBaseUomDescription(char baseUom) {
		if(baseUom == 'v') {
			return "Volume (e.g. gallons, liters)"
		} else if(baseUom == 'm') {
			return "Mass (e.g. pounds, ounces, grams)"
		} else {
			return "Units (e.g. an apple, a carrot)"
		}
	}
	
	static def getBaseUomDisplay(char baseUom) {
		if(baseUom == 'v') {
			return VolumnUtil.getBaseUomDisplay()
		} else if(baseUom == 'm') {
			return MassUtil.getBaseUomDisplay()
		} else if(baseUom == 'u') {
			""
		}
	}
	
	static def getCurrentUom(char baseUom, String currentUomName) {
		if(baseUom == 'v') {
			return VolumnUtil.getCurrentUom(currentUomName)
		} else if(baseUom == 'm') {
			return MassUtil.getCurrentUom(currentUomName)
		} else if(baseUom == 'u') {
			""
		}
	}
	
	static double getDisplayAmount(char baseUom, String origUom, String displayUom, double amount) {
		if(baseUom == 'v') {
			return VolumnUtil.getDisplayAmount(origUom, displayUom, amount)
		} else if(baseUom == 'm') {
			return MassUtil.getDisplayAmount(origUom, displayUom, amount)
		} else if(baseUom == 'u') {
			return amount
		} 
	}
	
}
