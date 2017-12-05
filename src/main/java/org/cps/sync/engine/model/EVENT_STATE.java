package org.cps.sync.engine.model;

public enum EVENT_STATE {

	PENDING (1),
	SUCCESS (2),
	FAILED (3);

	private final int eventState;

	EVENT_STATE(int eventState){
		this.eventState=eventState;
	}

	public int getEventState(){
		return eventState;
	}

}
