package com.wojto.wmcase.dao;

import java.util.List;

import com.wojto.wmcase.entity.Case;
import com.wojto.wmcase.entity.Order;

public interface OrderDAO {
	
	public Order getOrder(int theId);

	public List<Case> getCases(int theId);
	
	public void saveOrder(Order theOrder);
	
	public void deleteOrder(int theId);
	
	
	
}
