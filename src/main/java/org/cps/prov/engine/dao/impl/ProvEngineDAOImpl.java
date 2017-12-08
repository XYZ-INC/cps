package org.cps.prov.engine.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cps.identity.dao.UserManagerDAO;
import org.cps.identity.model.User;
import org.cps.prov.engine.dao.ApplicationDAO;
import org.cps.prov.engine.dao.ProvEngineDAO;
import org.cps.prov.engine.model.Application;
import org.cps.prov.engine.model.ApplicationDefinition;
import org.cps.prov.engine.model.ProvisioningPolicy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProvEngineDAOImpl implements ProvEngineDAO {

	@PersistenceContext
	private EntityManager manager;

	public void createProvisioningPolicy(ProvisioningPolicy provPolicy) {
		manager.persist(provPolicy);    	
	}

	public ProvisioningPolicy findProvPolicyByName(String policyName) {
		Query query = manager.createQuery("Select a from ProvisioningPolicy a where a.name= :policyName");
		query.setParameter("policyName", policyName);
		ProvisioningPolicy policy = (ProvisioningPolicy) query.getSingleResult();
		return policy;
	}

	public ProvisioningPolicy findProvPolicyByID(long id) {
		Query query = manager.createQuery("Select a from ProvisioningPolicy a where a.id= :id");
		query.setParameter("id", id);
		ProvisioningPolicy policy = (ProvisioningPolicy) query.getSingleResult();
		return policy;
	}

}
