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
	
	static def getBaseUomDisplay(char baseUom) {
		if(baseUom == 'v') {
			return VolumnUtil.getBaseUomDisplay()
		} else if(baseUom == 'm') {
			return MassUtil.getBaseUomDisplay()
		} else if(baseUom == 'u') {
			""
		}
	}
	
	static def getDisplayAmount(char baseUom, String displayUom, double amount) {
		if(baseUom == 'v') {
			return VolumnUtil.getDisplayAmount(displayUom, amount)
		} else if(baseUom == 'm') {
			return MassUtil.getDisplayAmount(displayUom, amount)
		} else if(baseUom == 'u') {
			return amount
		} 
	}
	
}
