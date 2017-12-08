package org.cps.prov.engine.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.cps.identity.dao.UserManagerDAO;
import org.cps.identity.model.User;
import org.cps.prov.engine.dao.ApplicationDAO;
import org.cps.prov.engine.model.Application;
import org.cps.prov.engine.model.ApplicationDefinition;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ApplicationDAOImpl implements ApplicationDAO {

	@PersistenceContext
	private EntityManager manager;

	public void createAppDefinition(ApplicationDefinition appDef) {
		manager.persist(appDef);    	
	}

	public void createApplication(Application app) {
		manager.persist(app);    	
	}

	public ApplicationDefinition findAppDefinitionByName(String appDefName) {
		Query query = manager.createQuery("Select a from ApplicationDefinition a where a.name= :appDefName");
		query.setParameter("appDefName", appDefName);
		ApplicationDefinition appDef = (ApplicationDefinition) query.getSingleResult();
		return appDef;
	}

	public ApplicationDefinition findAppDefinitionByID(long appDefID) {
		Query query = manager.createQuery("Select a from ApplicationDefinition a where a.id= :appDefID");
		query.setParameter("appDefID", appDefID);
		ApplicationDefinition appDef = (ApplicationDefinition) query.getSingleResult();
		return appDef;
	}

	public Application findApplicationByName(String appName) {
		Query query = manager.createQuery("Select a from Application a where a.name= :appName");
		query.setParameter("appName", appName);
		Application app = (Application) query.getSingleResult();
		return app;
	}

	public Application findApplicationByID(long appID) {
		Query query = manager.createQuery("Select a from Application a where a.id= :appID");
		query.setParameter("appID", appID);
		Application app = (Application) query.getSingleResult();
		return app;
	}

}
