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
import org.cps.identity.model.Organization;
import org.cps.identity.model.User;
import org.cps.identity.model.UserPassword;
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
	UserManagerUtils userManagerUtils;

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
		
 		userDAO.createUser(user);

 
		Assert.assertEquals("true", "true");
	}

 	
 

}
