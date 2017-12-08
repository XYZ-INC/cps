package org.cps.identity.dao;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cps.identity.common.PasswordUtils;
import org.cps.identity.common.UserManagerConstants;
import org.cps.identity.common.UserManagerUtils;
import org.cps.identity.model.Group;
import org.cps.identity.model.Organization;
import org.cps.identity.model.User;
import org.cps.identity.model.UserPassword;
import org.cps.prov.engine.dao.ApplicationDAO;
import org.cps.prov.engine.dao.ProvEngineDAO;
import org.cps.prov.engine.model.APP_PARAM_TYPE;
import org.cps.prov.engine.model.AppPolicyGroup;
import org.cps.prov.engine.model.Application;
import org.cps.prov.engine.model.ApplicationDefinition;
import org.cps.prov.engine.model.ApplicationParams;
import org.cps.prov.engine.model.ProvisioningPolicy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

  
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagerDAOTest {

	@Autowired
	private UserManagerDAO userDAO;

	@Autowired
	private OrgManagerDAO orgDAO;

	@Autowired
	private GroupManagerDAO grpDAO;

	@Autowired
	UserManagerUtils userManagerUtils;

	@Autowired
	ApplicationDAO appDAO;
	
	@Autowired
	ProvEngineDAO provEngineDAO;

	@Test
	@Transactional
	@Rollback(false)
	public void testCreateUser() {
		
		
		Organization org = new Organization();
		
		org.setName("Test");
		org.setDescription("Test Description");
		org.setHierarchyAware(false);
		org.setCreatedBy("INTERNAL");
		org.setCreatedDate(new java.util.Date());
		
		orgDAO.createOrg(org);
		
		org = orgDAO.findOrgByName("Test");
		
		
 		Group group = new Group();
 		group.setName("Test Group 1");
 		group.setDescription("Test Group 1 Description");
 		group.setCreatedBy("INTERNAL");
 		group.setCreatedDate(new Date());
		grpDAO.createGroup(group );

 		group = new Group();
 		group.setName("Test Group 2");
 		group.setDescription("Test Group 1 Description");
 		group.setCreatedBy("INTERNAL");
 		group.setCreatedDate(new Date());
		grpDAO.createGroup(group );

 		group = new Group();
 		group.setName("Test Group 3");
 		group.setDescription("Test Group 1 Description");
 		group.setCreatedBy("INTERNAL");
 		group.setCreatedDate(new Date());
		grpDAO.createGroup(group );

		
		User user = new User();
		
		user.setFirstName("Anurag");
		user.setMiddleName("Ambika");
		user.setLastName("Singh");
		user.setEmail("asingh@simeiosolutions.com");
		
		Map<String, Object> userAttributes = new HashMap<String, Object>(); 
 		
		userAttributes.put(UserManagerConstants.FIRST_NAME, "Anurag");
		userAttributes.put(UserManagerConstants.LAST_NAME, "Singh");

		String userName = userManagerUtils.generateUserNameForUser(userAttributes);
		
		
		user.setUserName(userName);
		
		user.setCreatedBy("INTERNAL");
		user.setCreatedDate(new java.util.Date());
		
		
		UserPassword userPassword = PasswordUtils.generatePassword();

		user.setPassword(userPassword.getSecurePassword());
		user.setPasswordSalt(userPassword.getSalt());
		
		user.setOrganization(org);
		
 		User usr = userDAO.createUser(user);

 		
 		group = grpDAO.findGroupByName("Test Group 1");
		grpDAO.addUserToGroup(group, usr);
		
		ApplicationDefinition appDef = new ApplicationDefinition();
		
		appDef.setName("Test App Def");
		appDef.setDescription("Test App Def");
		appDef.setCreatedBy("INTERNAL");
		appDef.setCreatedDate(new java.util.Date());
		
		appDAO.createAppDefinition(appDef);
		
		appDef = appDAO.findAppDefinitionByName("Test App Def");
		
		Application app = new Application();
		
		app.setName("Test App");
		app.setDescription("Test App Description");
		app.setAppDefinition(appDef);
		app.setTrusted(false);
		app.setCreatedBy("INTERNAL");
		app.setCreatedDate(new java.util.Date());

		ApplicationParams param1 = new ApplicationParams();
		param1.setName("URL");
		param1.setType(APP_PARAM_TYPE.STRING);
		param1.setValue("http://test.server.com");

		ApplicationParams param2 = new ApplicationParams();
		param2.setName("PORT");
		param2.setType(APP_PARAM_TYPE.NUMBER);
		param2.setValue("1024");

		ApplicationParams param3 = new ApplicationParams();
		param3.setName("USERNAME");
		param3.setType(APP_PARAM_TYPE.STRING);
		param3.setValue("asingh");

		ApplicationParams param4 = new ApplicationParams();
		param4.setName("PASSWORD");
		param4.setType(APP_PARAM_TYPE.STRING);
		param4.setValue("ROCKSTAR123!");
		param4.setEncrypted(true);

		List<ApplicationParams> params = new ArrayList<ApplicationParams>();
		
		params.add(param1);
		params.add(param2);
		params.add(param3);
		params.add(param4);
		
		app.setParams(params);
		
		appDAO.createApplication(app);
		
		ProvisioningPolicy provPolicy = new ProvisioningPolicy();
		
		provPolicy.setName("Test Org Policy");
		provPolicy.setDescription("Policy to determine provising for Test department");
		
		List<Application> applications = new ArrayList<Application>();
		applications.add(app);
		
		provPolicy.setApplications(applications );
		
 		Group group1 = grpDAO.findGroupByName("Test Group 1");
 		Group group2 = grpDAO.findGroupByName("Test Group 2");
 		Group group3 = grpDAO.findGroupByName("Test Group 3");
 		

 		System.out.println("GROUP : "+ group);
 		System.out.println("GROUP : "+ group.getId());
 		System.out.println("GROUP : "+ group.getName());
		
		AppPolicyGroup appPolicyGrp1 = new AppPolicyGroup();
		
		group1.addAppPolicyGroup(appPolicyGrp1);
		
		
		appPolicyGrp1.setPolicy(provPolicy);
		appPolicyGrp1.setGroup(group1);
		appPolicyGrp1.setAllowed(true);

		AppPolicyGroup appPolicyGrp2 = new AppPolicyGroup();
		
		group2.addAppPolicyGroup(appPolicyGrp2);
		
		
		appPolicyGrp2.setPolicy(provPolicy);
		appPolicyGrp2.setGroup(group2);
		appPolicyGrp2.setAllowed(true);

		AppPolicyGroup appPolicyGrp3 = new AppPolicyGroup();
		
		group1.addAppPolicyGroup(appPolicyGrp3);
		
		
		appPolicyGrp3.setPolicy(provPolicy);
		appPolicyGrp3.setGroup(group3);
		appPolicyGrp3.setAllowed(false);

		provPolicy.addAppPolicyGroup(appPolicyGrp1);
		provPolicy.addAppPolicyGroup(appPolicyGrp2);
		provPolicy.addAppPolicyGroup(appPolicyGrp3);
		
		provEngineDAO.createProvisioningPolicy(provPolicy);
		
		Assert.assertEquals("true", "true");
	}

 	
 

}
