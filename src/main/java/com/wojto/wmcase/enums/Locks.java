package com.wojto.wmcase.enums;

public enum Locks {
	MOTYLKOWE ("Motylkowe"), 
	KLAMROWE ("Klamrowe");
	
	public final String locks;
	
	private Locks(String locks) {
		this.locks = locks;
	}

	public String getLocks() {
		return locks;
	}


}
