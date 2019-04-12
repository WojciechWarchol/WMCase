package com.wojto.wmcase.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wojto.wmcase.entity.Case;

@Repository
public class CaseDAOImpl implements CaseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Case getCase(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
//		Query<Case> theQuery = 
//				currentSession.createQuery("from Case order by id", Case.class);
//		
//		Case theCase = theQuery.getResultList().get(0);
		
		Case theCase = currentSession.get(Case.class, theId);
		
		return theCase;
	}

	@Override
	public void saveCase(Case theCase) {
		
		theCase.surfaceEvaluation();
		theCase.evaluation();
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCase);
		
	}

	@Override
	public void deleteCase(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery =
				currentSession.createQuery("delete from Case where id=:caseId");
		theQuery.setParameter("caseId", theId);
		theQuery.executeUpdate();

	}
	
	// Do celów testowych
	public List<Case> getCases() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Case> theQuery =
				currentSession.createQuery("from Case", Case.class);
//		theQuery.setParameter("orderId", theId);
		System.out.println("////////////////////////////////////");
		System.out.println(theQuery);
		List<Case> cases = theQuery.getResultList();
		
		return cases;
	}

}
