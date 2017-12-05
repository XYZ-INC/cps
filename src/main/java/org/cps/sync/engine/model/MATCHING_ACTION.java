package org.cps.sync.engine.model;

public enum MATCHING_ACTION {

	CREATE (1),
	IGNORE (2),
	UPDATE(3),
	DELETE(4);
	
	private final int  matchingAction;
	
	MATCHING_ACTION(int matchingAction){
		this.matchingAction=matchingAction;
	}
	
	public int getMatchingAction(){
		return this.matchingAction;
	}

}
