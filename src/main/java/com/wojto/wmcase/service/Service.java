package com.wojto.wmcase.service;

import java.util.List;

import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;

public interface Service {

	public List<Client> getClients ();
	public List<Order> getOrders (int theId);
	public List<Case> getCases (int theId);
	
	public void saveCase(Case theCase);
	public void deleteCase(int theId);
	
	public void saveOrder(Order theOrder);
	public void deleteOrder(int theId);
	
	public void saveClient(Client theClient);
	public void deleteClient(int theId);
	
		
}
