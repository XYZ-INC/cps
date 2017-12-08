package org.cps.prov.engine.dao;

 
import org.cps.prov.engine.model.Application;
import org.cps.prov.engine.model.ApplicationDefinition;

public interface ApplicationDAO {
	
	public void createAppDefinition(ApplicationDefinition appDef);
	
	public void createApplication(Application app);
	
	public ApplicationDefinition findAppDefinitionByName(String appDefName);
	
	public ApplicationDefinition findAppDefinitionByID(long appDefID);

	public Application findApplicationByName(String appDefName);
	
	public Application findApplicationByID(long appDefID);

}
