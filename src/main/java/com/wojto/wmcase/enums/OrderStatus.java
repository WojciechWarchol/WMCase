package com.wojto.wmcase.enums;

public enum OrderStatus {
	ZAPYTANIE ("Zapytanie"), 
	ODPISANO ("Odpisano"), 
	PRZYJ�TE ("Przyj�te do realizacji"), 
	WYKONANE ("W trakcie wykonania"), 
	GOTOWE ("Gotowe do wydania"), 
	DOSTARCZONE ("Dostarczone"), 
	OP�ACONE ("Zrealizowane i op�acone");
	
	public final String orderStatus;
	
	private OrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

}
