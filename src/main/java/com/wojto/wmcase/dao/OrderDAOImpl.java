package com.wojto.wmcase.dao;

import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
		
		return cases;
	}

	@Override
	public void saveOrder(Order theOrder) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theOrder);
		List<Case> caseList = theOrder.getCaseList();
		for (Case theCase : caseList){
			currentSession.saveOrUpdate(theCase);
		}
		
	}

	@Override
	public void deleteOrder(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery =
				currentSession.createQuery("delete from Order where id=:orderId");
		theQuery.setParameter("orderId", theId);
		theQuery.executeUpdate();

	}
	
	// Do celów testowych
	public List<Order> getOrders() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Order> theQuery =
				currentSession.createQuery("SELECT DISTINCT o FROM Order o JOIN FETCH o.cases c", Order.class);

		System.out.println("+++++++++++++++++++++");
		System.out.println(theQuery);
		List<Order> orders = theQuery.getResultList();
		
		return orders;
	}

}
