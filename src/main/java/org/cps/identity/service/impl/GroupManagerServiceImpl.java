package org.cps.identity.service.impl;

import java.util.List;

import org.cps.framework.common.SearchCriteria;
import org.cps.identity.dao.GroupManagerDAO;
import org.cps.identity.dao.UserManagerDAO;
import org.cps.identity.model.Group;
import org.cps.identity.model.User;
import org.cps.identity.service.GroupManagerService;
import org.cps.identity.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("groupManagerService")
public class GroupManagerServiceImpl implements GroupManagerService {

	

		@Autowired
		private GroupManagerDAO groupManagerDAO;

		
	public Group createGroup(Group group) {
		return groupManagerDAO.createGroup(group);
	}

	public void updateGroup(Group group) {
		groupManagerDAO.updateGroup(group);
	}

	public void addUserToGroup(Group group, User user) {
		groupManagerDAO.addUserToGroup(group, user);
	}

	public Group findGroupByName(String grpName) {
		return groupManagerDAO.findGroupByName(grpName);
	}

	public Group findGroupByID(long id) {
		return groupManagerDAO.findGroupByID(id);
	}

	public List<Group> findGroup(SearchCriteria criteria) {
		return groupManagerDAO.findGroup(criteria);
	}

}
