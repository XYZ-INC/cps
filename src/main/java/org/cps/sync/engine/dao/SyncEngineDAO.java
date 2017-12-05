package org.cps.sync.engine.dao;

import java.util.List;

import org.cps.sync.engine.model.EVENT_STATE;
import org.cps.sync.engine.model.SyncEvent;
import org.cps.sync.engine.model.SyncProfile;

public interface SyncEngineDAO {
	
	public void createSyncProfile(SyncProfile profile) ;

	public SyncProfile getSyncProfile(String profileName) ;

	public void createSyncEvent(SyncEvent event) ;

	public List<SyncEvent> getOpenEventsForProfile(String profileName);

	public void updateEventStatus(SyncEvent event, EVENT_STATE eventState);

}
