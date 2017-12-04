package org.cps.identity.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cps.framework.common.SearchCriteria;
import org.cps.identity.dao.OrgManagerDAO;
import org.cps.identity.model.Organization;
import org.cps.identity.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrgManagerDAOImpl implements OrgManagerDAO {
    @PersistenceContext
    private EntityManager manager;

	public Organization createOrg(Organization org) {
		manager.persist(org);    	
 		
		Query query = manager.createQuery("Select o from Organization o where o.name= :orgName");
		query.setParameter("orgName", org.getName());
		
 		
		org = (Organization) query.getSingleResult();
		return org;
	}

	public Organization findOrgByName(String orgName) {
		Query query = manager.createQuery("Select o from Organization o where o.name= :orgName");
		query.setParameter("orgName", orgName);
		
 		
		Organization org = (Organization) query.getSingleResult();
		return org;
	}

	public Organization findOrgByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Organization> findOrg(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
