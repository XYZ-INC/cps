package org.cps.audit.service;

import org.cps.framework.exception.CPSAuditException;
import org.cps.identity.model.User;

public interface AuditorService{
	
	
	public void createUser(User user) throws CPSAuditException;
	
	public void updateUser(User user) throws CPSAuditException;
	
	public void deleteUser(User user) throws CPSAuditException;
	
	public void lockUser(User user) throws CPSAuditException;


}
