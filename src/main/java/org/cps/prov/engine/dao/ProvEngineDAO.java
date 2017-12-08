package org.cps.prov.engine.dao;

 
import javax.persistence.Query;

import org.cps.prov.engine.model.Application;
import org.cps.prov.engine.model.ApplicationDefinition;
import org.cps.prov.engine.model.ProvisioningPolicy;

public interface ProvEngineDAO {
	
	public void createProvisioningPolicy(ProvisioningPolicy provPolicy);

	public ProvisioningPolicy findProvPolicyByName(String policyName);

	public ProvisioningPolicy findProvPolicyByID(long id);

}
