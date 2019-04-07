package com.wojto.wmcase.enums;

public enum OrderStatus {
	ZAPYTANIE ("Zapytanie"), 
	ODPISANO ("Odpisano"), 
	PRZYJ�TE_DO_REALIZACJI ("Przyj�te do realizacji"), 
	W_TRAKCIE_WYKONANIA ("W trakcie wykonania"), 
	GOTOWE_DO_ODDANIA ("Gotowe do wydania"), 
	DOSTARCZONE ("Dostarczone"), 
	ZREALIZOWANE_I_OP�ACONE ("Zrealizowane i op�acone");
	
	public final String orderStatus;
	
	private OrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

}
