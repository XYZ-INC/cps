package org.cps.sync.engine.model;

public enum ATTR_TYPE {
	
	STRING (1),
	DATE (2),
	INT(3);
	
	private final int  attrType;
	
	ATTR_TYPE(int attrType){
		this.attrType=attrType;
	}
	
	public int getAttrType(){
		return this.attrType;
	}

}
