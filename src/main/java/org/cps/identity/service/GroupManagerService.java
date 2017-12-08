package org.cps.identity.service;

import java.util.List;

import org.cps.framework.common.SearchCriteria;
import org.cps.identity.model.Group;
import org.cps.identity.model.User;

public interface GroupManagerService {

	public Group createGroup(Group group);
	
	public void updateGroup(Group group);
	
	public void addUserToGroup(Group group, User user);
	
	public Group findGroupByName(String grpName);
	
	public Group findGroupByID(long id);
	
	public List<Group> findGroup(SearchCriteria criteria);

}
