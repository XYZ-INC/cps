package org.cps.prov.engine.model;

public enum APP_PARAM_TYPE {

	STRING (1),
	NUMBER (2),
	BOOLEAN(3),
	DATE(4);
	
	private final int  appParamType;
	
	APP_PARAM_TYPE(int appParamType){
		this.appParamType=appParamType;
	}
	
	public int getAppParamType(){
		return this.appParamType;
	}

}
