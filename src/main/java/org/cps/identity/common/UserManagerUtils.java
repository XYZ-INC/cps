package org.cps.identity.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.cps.identity.service.UserManagerService;
import org.cps.identity.common.PasswordUtils;

@Service("userManagerUtils")
public class UserManagerUtils {

	@Autowired
	static UserManagerService userService;
	
	public String generateUserNameForUser(
			Map<String, Object> userAttributes) {

		String firstName = (String)userAttributes.get(UserManagerConstants.FIRST_NAME);
		String lastName = (String)userAttributes.get(UserManagerConstants.LAST_NAME);

		System.out.println(firstName + " : " + lastName);
		
		String userLogin = firstName.substring(0,1) + lastName;
		
//		boolean exists = userService.checkIfUserLoginExists(userLogin);
//		
//		if(!exists)
//			return userLogin;
		
		return userLogin;
	}


}
