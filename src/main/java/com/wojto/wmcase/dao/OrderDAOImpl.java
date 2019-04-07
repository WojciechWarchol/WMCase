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
				currentSession.createQuery("from Order order by id", Order.class);
		
		Order theOrder = theQuery.getResultList().get(0);
		
		return theOrder;
	}

	@Override
	public List<Case> getCases(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Case> theQuery =
				currentSession.createQuery("from Case where id=:orderId order by id", Case.class);
		theQuery.setParameter("orderId", theId);
		List<Case> cases = theQuery.getResultList();
		
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

}
