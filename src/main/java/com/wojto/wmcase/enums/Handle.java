package com.wojto.wmcase.enums;

public enum Handle {
	KASETOWY ("Kasetowy w miseczce"), 
	SPREZYNOWY ("Spr�ynowy nawierzchniowy"), 
	WALIZKOWY ("Walizkowy");
	
	public final String handle;
	
	private Handle(String handle) {
		this.handle = handle;
	}

	public String getHandle() {
		return handle;
	}


}
