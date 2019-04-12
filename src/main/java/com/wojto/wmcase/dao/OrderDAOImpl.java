package com.wojto.wmcase.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Order getOrder(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Order> theQuery = 
				currentSession.createQuery("from Order o where o.id = :orderId", Order.class)
				.setParameter("orderId", theId);
		
		Order theOrder = theQuery.getResultList().get(0);
		
		return theOrder;
	}

	@Override
	public List<Case> getCases(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Case> theQuery =
				currentSession.createQuery("FROM Case c JOIN FETCH c.order o where o.id = :orderId", Case.class);
		theQuery.setParameter("orderId", theId);
		List<Case> cases = theQuery.getResultList();
		
		//"from Case where id=:orderId order by id"
		//"select c FROM Order o JOIN o.cases c WHERE :orderId = cId"
		
		return cases;
	}

	@Override
	public void saveOrder(Order theOrder) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theOrder);
		
	}

	@Override
	public void deleteOrder(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery =
				currentSession.createQuery("delete from Case where id=:orderId");
		theQuery.setParameter("orderId", theId);
		theQuery.executeUpdate();

	}
	
	// Do celów testowych
	public List<Order> getOrders() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Order> theQuery =
				currentSession.createQuery("SELECT DISTINCT o FROM Order o JOIN FETCH o.cases c", Order.class);
//						+ "where o.cases = :cases", Order.class)
//				.setParameter("orderId", );

		System.out.println("+++++++++++++++++++++");
		System.out.println(theQuery);
		List<Order> orders = theQuery.getResultList();
		
		return orders;
	}

}
