package org.cps.identity.service;

import java.util.List;

import org.cps.framework.common.SearchCriteria;
import org.cps.identity.model.User;
 
 
public interface UserManagerService {
	public List<User> findUser(SearchCriteria criteria);
	public User createUser(User user);
	public boolean checkIfUserLoginExists(String userLogin);
}
