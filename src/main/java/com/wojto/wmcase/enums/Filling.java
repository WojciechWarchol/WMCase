package com.wojto.wmcase.enums;

public enum Filling {
	FOAM ("Pianka"),
	BLANKET ("Koc");

	public final String filling;
	
	private Filling(String filling) {
		this.filling = filling;
	}

	public String getFilling() {
		return filling;
	}


}
