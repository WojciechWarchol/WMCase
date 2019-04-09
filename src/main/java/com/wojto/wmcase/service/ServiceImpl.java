package com.wojto.wmcase.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.wojto.wmcase.dao.CaseDAO;
import com.wojto.wmcase.dao.CaseDAOImpl;
import com.wojto.wmcase.dao.ClientDAO;
import com.wojto.wmcase.dao.OrderDAO;
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
	// do usuniecia
	@Autowired
	private CaseDAOImpl caseDAOImpl;

	@Override
	@Transactional
	public List<Client> getClients() {
		return clientDAO.getClients();
	}

	@Override
	@Transactional
	public List<Order> getOrders(int theId) {
		return clientDAO.getOrders(theId);
	}

	@Override
	@Transactional
	public List<Case> getCases(int theId) {
		return caseDAOImpl.getCases(theId);
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
