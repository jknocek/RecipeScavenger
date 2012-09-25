package com.rec.plant

import com.rec.plant.Plant;

class Plant { 
	long id
	String description
	Boolean validated
	String hardiness
	Boolean evergreen
	String annual
	String genus
	String genusHybrid
	String species
	String speciesHybrid
	String variety
	String subSpecies
	String cultivar
	String forma
	
	String toString() { "${this.class.name} :  $id" }
	
	static mapping = {
		id generator: 'identity'
		version false
	}
	
	boolean equals(other) {
		if(other?.is(this))
			return true
		if(!(other instanceof Plant)) 
			return false
		if(!id || !other?.id || id!=other?.id) 
			return false
			
		return true
	}
	
	int hashCode() {
		int hashCode = 0
		hashCode = 29 * (hashCode + ( !id ? 0 : id ^ (id >>> 32) ) )
	}
}