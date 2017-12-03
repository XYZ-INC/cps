package org.cps.framework.common;

import java.util.HashMap;
import java.util.Map;

public class SearchCriteria {
	
	private Map<String, SearchValue> searchCriteria = new HashMap<String, SearchValue>();
	private SEARCH_TYPE searchType;

	public void addSearchCriteria(String key, Object value, SEARCH_CONDITION condition){
		
		SearchValue searchValue = new SearchValue(key, value, condition);
		
		searchCriteria.put(key, searchValue);
	}
	
	public enum SEARCH_TYPE{
		AND, OR;
	}
	
	public enum SEARCH_CONDITION{
		EQUALS, NOT_EQUALS, GREATER_THAN, GREATER_THAN_EQUAL_TO, LESS_THAN, LESS_THAN_EQUAL_TO
	}

	public Map<String, SearchValue> getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(Map<String, SearchValue> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public SEARCH_TYPE getSearchType() {
		return searchType;
	}

	public void setSearchType(SEARCH_TYPE searchType) {
		this.searchType = searchType;
	}


}
