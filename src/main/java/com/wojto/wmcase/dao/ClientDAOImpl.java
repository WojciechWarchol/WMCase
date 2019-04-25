package com.wojto.wmcase.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Client> getClients() {
		
		Session curretnSession = sessionFactory.getCurrentSession();
		
		Query<Client> theQuery =
				curretnSession.createQuery("from Client order by surname", Client.class);
		
		List<Client> clients = theQuery.getResultList();
		
		return clients;
	}

	@Override
	public Client getClient(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Client> theQuery = 
				currentSession.createQuery("from Client order by id", Client.class);
		
		Client theClient = theQuery.getResultList().get(0);
		
		return theClient;
	}

	@Override
	public List<Order> getOrders(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Order> theQuery =
				currentSession.createQuery("SELECT DISTINCT o FROM Order o JOIN FETCH o.client c JOIN FETCH o.cases WHERE c.id = :clientId", Order.class);
		theQuery.setParameter("clientId", theId);
		List<Order> orders = theQuery.getResultList();
		
		return orders;
	}

	@Override
	public void saveClient(Client theClient) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theClient);
		
	}

	@Override
	public void deleteClient(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery =
				currentSession.createQuery("delete from Client where id=:clientId");
		theQuery.setParameter("clientId", theId);
		theQuery.executeUpdate();

	}

	@Override
	public List<Client> searchClients(String seachText) {
		// TODO Auto-generated method stub
		return null;
	}

}
