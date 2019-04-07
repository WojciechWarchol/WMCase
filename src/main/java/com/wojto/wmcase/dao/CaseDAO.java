package com.wojto.wmcase.dao;

import com.wojto.wmcase.entity.Case;

public interface CaseDAO {

	public Case getCase(int theId);
	
	public void saveCase(Case theCase);
	
	public void deleteCase(int theId);
	
	
	
}
