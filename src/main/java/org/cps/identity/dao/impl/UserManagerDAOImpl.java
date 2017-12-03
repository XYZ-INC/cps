package org.cps.identity.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cps.framework.common.SearchCriteria;
import org.cps.framework.common.SearchCriteria.SEARCH_TYPE;
import org.cps.framework.common.SearchValue;
import org.cps.identity.dao.UserManagerDAO;
import org.cps.identity.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UserManagerDAOImpl implements UserManagerDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserManagerDAOImpl.class);
        
    @PersistenceContext
    private EntityManager manager;

	public List<User> findUser(SearchCriteria criteria) {
		
		List<User> list = new ArrayList<User>();
		
		Map<String, SearchValue> searchCriteria = criteria.getSearchCriteria();
		SEARCH_TYPE searchType = criteria.getSearchType();

		return list;
	}

	public User createUser(User user) {
		manager.persist(user);    	
		logger.debug("Person saved successfully, Person Details="+user);
		
		Query query = manager.createQuery("Select u from User u where u.userName= :userName");
		query.setParameter("userName", user.getUserName());
		
		User usr = (User) query.getSingleResult();
		return usr;
	}

	public boolean checkIfUserLoginExists(String userLogin) {
		return false;
	}

 	 
}
