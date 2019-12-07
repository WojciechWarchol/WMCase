package com.wojto.wmcase.enums;

public enum OrderStatus {
	ZAPYTANIE ("Zapytanie"), 
	ODPISANO ("Odpisano"), 
	PRZYJETE ("Przyjęte do realizacji"),
	WYKONANE ("W trakcie wykonania"), 
	GOTOWE ("Gotowe do wydania"), 
	DOSTARCZONE ("Dostarczone"), 
	OPLACONE ("Zrealizowane i opłacone");
	
	public final String orderStatus;
	
	private OrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

}
