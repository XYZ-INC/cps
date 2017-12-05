package org.cps.sync.engine.model;

public enum SYNC_TYPE {
	
	USER (1),
	ACCOUNT (2),
	ROLE (3),
	ENTITLEMENT (4);
	
	private final int syncType;
	
	SYNC_TYPE(int syncType){
		this.syncType=syncType;
	}
	
	public int getSyncType(){
		return syncType;
	}

}
