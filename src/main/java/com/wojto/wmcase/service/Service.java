package com.wojto.wmcase.service;

import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;

import java.util.List;

public interface Service {

	public List<Client> getAllClients ();
	public List<Order> getAllOrders ();
	public List<Case> getAllCases ();
	
	public List<Case> getCasesForOrder(int theId);
	public List<Order> getOrdersForClient(int theId);
	
	public Order getOrder(int theId);
	public Case getCase(int theId);
	
	public void saveCase(Case theCase);
	public void deleteCase(int theId);
	
	public void saveOrder(Order theOrder);
	public void deleteOrder(int theId);
	
	public void saveClient(Client theClient);
	public void deleteClient(int theId);

	public Case findCaseInOrder(Order theOrder, String tempCaseString);
	
		
}
