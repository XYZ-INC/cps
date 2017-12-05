package org.cps.sync.engine.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.cps.sync.engine.dao.SyncEngineDAO;
import org.cps.sync.engine.model.EVENT_STATE;
import org.cps.sync.engine.model.SyncEvent;
import org.cps.sync.engine.model.SyncProfile;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class SyncEngineDAOImpl implements SyncEngineDAO {

	@PersistenceContext
	private EntityManager manager;	 
	
	public void createSyncProfile(SyncProfile profile) {
		if (profile.getId() <= 0)
			manager.persist(profile);
		else
			manager.merge(profile);
	}

	public void createSyncEvent(SyncEvent event) {
		
		System.out.println(event.getId());
		
		if (event.getId() <= 0)
			manager.persist(event);
		else
			manager.merge(event);
	}



	public SyncProfile getSyncProfile(String profileName) {
		
		Query query = manager.createQuery("from SyncProfile p where p.name = ?", SyncProfile.class);
		query.setParameter(1, profileName);
		
		SyncProfile profile = (SyncProfile)query.getSingleResult();


		  return profile;
		
 
 	}

	public List<SyncEvent> getOpenEventsForProfile(String profileName) {
		
		System.out.println("########################################## "+ profileName);

		SyncProfile profile = getSyncProfile(profileName);

		System.out.println("########################################## "+ profile);

		
		TypedQuery<SyncEvent> query = manager.createQuery("from SyncEvent p where p.eventState = ? and p.syncProfile.id=?", SyncEvent.class);
		query.setParameter(1, EVENT_STATE.PENDING);
		query.setParameter(2, profile.getId());
		
		List<SyncEvent> events = (List<SyncEvent>)query.getResultList();
		
		System.out.println("########################################## "+ events);

		return events;
	}

	public void updateEventStatus(SyncEvent event, EVENT_STATE eventState) {

		//Object query = manager.createQuery("update SyncEvent e set e.eventState=?");
		
		event.setEventState(eventState);
		manager.merge(event);

	}

}
