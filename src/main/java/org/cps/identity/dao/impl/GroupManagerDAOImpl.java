package org.cps.identity.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cps.framework.common.SearchCriteria;
import org.cps.identity.dao.GroupManagerDAO;
import org.cps.identity.dao.UserManagerDAO;
import org.cps.identity.model.Group;
import org.cps.identity.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class GroupManagerDAOImpl implements GroupManagerDAO {

	@PersistenceContext
    private EntityManager manager;

	public Group createGroup(Group group) {
		manager.persist(group);    	
		Group grp = findGroupByName(group.getName());
		return grp;
	}

	public void updateGroup(Group group) {
		// TODO Auto-generated method stub

	}

	public void addUserToGroup(Group group, User user) {
		Group grp = manager.find(Group.class, group.getId());
		User usr = manager.find(User.class, user.getId());

		grp.addUsersToGroup(usr);
		
		
	}

	public Group findGroupByName(String grpName) {
		Query query = manager.createQuery("Select g from Group g where g.name= :grpName");
		query.setParameter("grpName", grpName);
				
		Group grp = (Group) query.getSingleResult();
		return grp;
	}

	public Group findGroupByID(long id) {
		Query query = manager.createQuery("Select g from Group g where g.id= :grpID");
		query.setParameter("grpID", id);
				
		Group grp = (Group) query.getSingleResult();
		return grp;
	}

	public List<Group> findGroup(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
