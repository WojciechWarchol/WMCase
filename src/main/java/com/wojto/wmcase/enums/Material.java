package com.wojto.wmcase.enums;

public enum Material {
	SKLEJKA ("Sklejka"), 
	CON_PEARL ("Con Pearl");

	public final String material;
	
	private Material(String material) {
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

}
