package org.cps.identity.dao;

import java.util.List;

import org.cps.framework.common.SearchCriteria;
import org.cps.identity.model.Organization;
import org.cps.identity.model.User;

public interface OrgManagerDAO {
	
	public Organization createOrg(Organization org);
	
	public Organization findOrgByName(String orgName);
	
	public Organization findOrgByID(long id);
	
	public List<Organization> findOrg(SearchCriteria criteria);



}
