package org.cps.sync.engine.model;

public enum MATCHING_CONDITION {

	EQUALS (1),
	NOT_EQUALS (2);

	private final int  matchingCondition;
	
	MATCHING_CONDITION(int matchingCondition){
		this.matchingCondition=matchingCondition;
	}
	
	public int getMatchingCondition(){
		return this.matchingCondition;
	}

}
