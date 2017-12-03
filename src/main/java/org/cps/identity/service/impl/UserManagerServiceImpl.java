package org.cps.identity.service.impl;

import java.util.List;

import org.cps.framework.common.SearchCriteria;
import org.cps.identity.model.User;
import org.cps.identity.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.cps.identity.dao.UserManagerDAO;
 
@Service("userManagerService")
public class UserManagerServiceImpl implements UserManagerService{

	@Autowired
	private UserManagerDAO userManagerDAO;

	public List<User> findUser(SearchCriteria criteria) {

		List<User> users = userManagerDAO.findUser(criteria);
		
		return users;
	}

	public User createUser(User user) {
		
		User usr = userManagerDAO.createUser(user);
		
		performUserCreatePostProcessing(usr);
		
		return null;
	}

	private void performUserCreatePostProcessing(User user) {

		//Assign user to group
		assignUserToGroup(user);
		
		// Evaluate User Policy
		evaluateUserPolicy(user);
		
		// Do custom Post processing
		performCustomPostProcessingonCreate(user);
	}

	private void assignUserToGroup(User user) {
		// Add to CPS Group	
		
		// Get Users Org, based on org policy decide on people group
		
		// Get User Type, based on user type decide on users group
		
		 
	}

	private void evaluateUserPolicy(User user) {
		// Get Users group and provision application
		
	}

	private void performCustomPostProcessingonCreate(User user) {
		// TODO Auto-generated method stub
		
	}

	public boolean checkIfUserLoginExists(String userLogin) {
		return userManagerDAO.checkIfUserLoginExists(userLogin);

	}
 
}
