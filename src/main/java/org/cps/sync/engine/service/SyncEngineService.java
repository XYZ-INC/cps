package org.cps.sync.engine.service;

import java.util.List;

import org.cps.sync.engine.model.EVENT_STATE;
import org.cps.sync.engine.model.SyncEvent;
import org.cps.sync.engine.model.SyncProfile;

 
public interface SyncEngineService {

	public void createSyncProfile(SyncProfile profile);

	public SyncProfile getSyncProfile(String profileName);

	public void createEvents(List<SyncEvent> events);

	public List<SyncEvent> getOpenEventsForProfile(String profileName);

	public void updateEventStatus(SyncEvent event, EVENT_STATE eventState);

}
