package com.wojto.wmcase.enums;

public enum Type {
	KUFER ("Kufer"), 
	NAKLADKA ("Nakładka"), 
	WALIZKA ("Walizka"), 
	KABLARKA ("Kablarka");
	
	public final String type;
	
	private Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
