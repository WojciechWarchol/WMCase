package com.wojto.wmcase.dao;

import java.util.List;

import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;

public interface ClientDAO {
	
	public List<Client> getClients();

	public Client getClient(int theId);
	
	public List<Order> getOrders(int theId);
	
	public void saveClient(Client theClient);
	
	public void deleteClient(int theId);
	
	public List<Client> searchClients(String seachText);
	
}
