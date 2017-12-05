package org.cps.sync.engine.service.impl;

import java.util.List;

import org.cps.sync.engine.dao.SyncEngineDAO;
import org.cps.sync.engine.model.EVENT_STATE;
import org.cps.sync.engine.model.SyncEvent;
import org.cps.sync.engine.model.SyncProfile;
import org.cps.sync.engine.service.SyncEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("syncEngineService")
public class SyncEngineServiceImpl implements SyncEngineService{
	
	@Autowired
	private SyncEngineDAO syncDAO;

	public void createSyncProfile(SyncProfile profile) {
		syncDAO.createSyncProfile(profile);
	}

	public SyncProfile getSyncProfile(String profileName) {
 		return syncDAO.getSyncProfile(profileName);
	}

	public void createEvents(List<SyncEvent> events) {
		
		for(SyncEvent event:events)
			syncDAO.createSyncEvent(event);
 		
	}

	public List<SyncEvent> getOpenEventsForProfile(String profileName) {
		
		System.out.println("###############getOpenEventsForProfile###################");

		return syncDAO.getOpenEventsForProfile(profileName);
	}

	public void updateEventStatus(SyncEvent event, EVENT_STATE eventState) {

		syncDAO.updateEventStatus(event, eventState);		
	}

}
