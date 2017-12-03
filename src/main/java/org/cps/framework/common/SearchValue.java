package org.cps.framework.common;

import org.cps.framework.common.SearchCriteria.SEARCH_CONDITION;

public class SearchValue {

	private String key;
	private Object value;
	private SEARCH_CONDITION condition;

	public SearchValue(String key, Object value, SEARCH_CONDITION condition) {
		this.key=key;
		this.value=value;
		this.condition=condition;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public SEARCH_CONDITION getCondition() {
		return condition;
	}

	public void setCondition(SEARCH_CONDITION condition) {
		this.condition = condition;
	}
	
	

}
