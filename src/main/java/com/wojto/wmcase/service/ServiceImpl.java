package com.wojto.wmcase.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.wojto.wmcase.dao.CaseDAO;
import com.wojto.wmcase.dao.CaseDAOImpl;
import com.wojto.wmcase.dao.ClientDAO;
import com.wojto.wmcase.dao.OrderDAO;
import com.wojto.wmcase.dao.OrderDAOImpl;
import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Client;
import com.wojto.wmcase.entity.Order;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
	
	@Autowired
	private CaseDAO caseDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private ClientDAO clientDAO;
	// two below for testing
	@Autowired
	private CaseDAOImpl caseDAOImpl;
	@Autowired
	private OrderDAOImpl orderDAOImpl;

	@Override
	@Transactional
	public List<Client> getAllClients() {
		return clientDAO.getClients();
	}
	
	// two below for testing
	@Override
	@Transactional
	public List<Order> getAllOrders() {
		return orderDAOImpl.getOrders();
	}

	@Override
	@Transactional
	public List<Case> getAllCases() {
		return caseDAOImpl.getCases();
	}
	
	@Override
	@Transactional
	public Order getOrder(int theId) {
		return orderDAOImpl.getOrder(theId);
	}

	@Override
	@Transactional
	public List<Case> getCasesForOrder(int theId) {
		return orderDAO.getCases(theId);
	}

	@Override
	@Transactional
	public List<Order> getOrdersForClient(int theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Case getCase(int theId) {
		return caseDAOImpl.getCase(theId);
	}

	@Override
	@Transactional
	public void saveCase(Case theCase) {
		caseDAO.saveCase(theCase);
	}

	@Override
	@Transactional
	public void deleteCase(int theId) {
		caseDAO.deleteCase(theId);
	}

	@Override
	@Transactional
	public void saveOrder(Order theOrder) {
		orderDAO.saveOrder(theOrder);
	}

	@Override
	@Transactional
	public void deleteOrder(int theId) {
		orderDAO.deleteOrder(theId);
	}

	@Override
	@Transactional
	public void saveClient(Client theClient) {
		clientDAO.saveClient(theClient);
	}

	@Override
	@Transactional
	public void deleteClient(int theId) {
		clientDAO.deleteClient(theId);
	}
	
}
