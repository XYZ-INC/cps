package org.cps.sync.engine.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cps.framework.common.SearchCriteria;
import org.cps.framework.common.SearchCriteria.SEARCH_CONDITION;
import org.cps.framework.common.SearchCriteria.SEARCH_TYPE;
import org.cps.framework.exception.CPSException;
import org.cps.identity.common.CPSMessages;
import org.cps.identity.common.PasswordUtils;
import org.cps.identity.common.UserManagerConstants;
import org.cps.identity.common.UserManagerUtils;
import org.cps.identity.model.User;
import org.cps.identity.model.UserPassword;
import org.cps.identity.service.UserManagerService;
import org.cps.sync.engine.model.ATTR_TYPE;
import org.cps.sync.engine.model.EVENT_STATE;
import org.cps.sync.engine.model.MATCHING_ACTION;
import org.cps.sync.engine.model.MATCHING_CONDITION;
import org.cps.sync.engine.model.MatchingRule;
import org.cps.sync.engine.model.ProfileAttribute;
import org.cps.sync.engine.model.SYNC_TYPE;
import org.cps.sync.engine.model.SyncAttrValue;
import org.cps.sync.engine.model.SyncAttribute;
import org.cps.sync.engine.model.SyncEvent;
import org.cps.sync.engine.model.SyncProfile;
import org.cps.sync.engine.service.SyncEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("syncEventProcessor")
public class SyncEventProcessor {

	@Autowired
	private SyncEngineService syncService;

	@Autowired
	UserManagerService userService;
	
	@Autowired
	UserManagerUtils userManagerUtils;
	
	// TODO This will change with JMS processing
	// Basically this class will be JMS message consumer
	public void eventsCreated(String profileName) {
		
		
		System.out.println("######################################## " + syncService);
		List<SyncEvent> events = syncService.getOpenEventsForProfile(profileName);

		
		for(SyncEvent event: events){
			try {
				processSyncEvent(event);
			} catch (CPSException e) {
				updateEventStatus(event, EVENT_STATE.FAILED);
				e.printStackTrace();
			}
		}

		
	}

	private void updateEventStatus(SyncEvent event, EVENT_STATE eventState) {
		//TODO : Undate Status of Event
		syncService.updateEventStatus(event, eventState);
	}

	private void processSyncEvent(SyncEvent event) throws CPSException {
		
		SyncProfile profile = event.getSyncProfile();
		
		SYNC_TYPE syncType = profile.getSyncType();
		
		if(syncType.equals(SYNC_TYPE.USER))
			processUserEvent(event);

 	}

	private void processUserEvent(SyncEvent event) throws CPSException {

		SyncProfile profile = event.getSyncProfile();
		List<SyncAttribute> attrs = event.getAttributes();
		EVENT_STATE state = event.getEventState();
		
		Map<String, Object> userAttributes = new HashMap<String, Object>();
		Map<String, Object> syncMap = new HashMap<String, Object>();
		Map<String, Object> profileMap = new HashMap<String, Object>();
		
		if(!state.equals(EVENT_STATE.PENDING))
			throw new CPSException(CPSMessages.CPS001_EVNT_NOT_PENDING);
		
		for(SyncAttribute attr:attrs){
			String attrName = attr.getName();
			ATTR_TYPE attrType = attr.getType();
			List<SyncAttrValue> valueList = attr.getValue();
			SyncAttrValue value = valueList.get(0);
			String attrValue = value.getValue();
			
			System.out.println(" ---> "+ attrName);
			
			String targetAttrName = getTargetAttribute(attrName, profile);
			
			
			
			if(targetAttrName == null)
				throw new CPSException(CPSMessages.CPS001_INVALID_TGT_ATTR_PRFL);
			
			userAttributes.put(targetAttrName, attrValue);

		}
		
		
		evaluateRuleAndProcessUser(event, userAttributes);
	 
		
	}

	private void evaluateRuleAndProcessUser(SyncEvent event, Map<String, Object> userAttributes) throws CPSException {

		
		SyncProfile profile = event.getSyncProfile();
		MatchingRule rule = profile.getMatchingRule();
		
		if(rule == null)
			throw new CPSException(CPSMessages.CPS001_MATCHING_RULE_MISSING_IN_PROFILE);

		String cpsAttr = rule.getCpsAttibute();
		String tgtAttr = rule.getTgtAttribute();
		MATCHING_CONDITION condition = rule.getMatchingCondition();
		MATCHING_ACTION action = rule.getMatchingAction();

		SearchCriteria criteria = new SearchCriteria();
		
		String uidValue = event.getUidValue();
		
		criteria.addSearchCriteria(tgtAttr, uidValue, SEARCH_CONDITION.EQUALS);
		criteria.setSearchType(SEARCH_TYPE.AND);
		
		List<User> list = userService.findUser(criteria);
		
		if(list.size()==1) {
			//return MATCHING_CONDITION.SINGLE_MATCH_FOUND;
		} else if(list.size() > 1){
			//return MATCHING_CONDITION.MULTIPLE_MATCH_FOUND;
		}
		if(list.size()==0){
			//return MATCHING_CONDITION.NO_MATCH_FOUND;
			if(action.equals(MATCHING_ACTION.CREATE)){
				createUser(event, userAttributes);
				
				updateEventStatus(event, EVENT_STATE.SUCCESS);
			}
			 
		}

 
	}

	private void createUser(SyncEvent event,
			Map<String, Object> userAttributes) {
		System.out.println("### USER ATTRIBUTES : " + userAttributes);
		
		String firstName = (String)userAttributes.get(UserManagerConstants.FIRST_NAME);
		String middleName = (String)userAttributes.get(UserManagerConstants.MIDDLE_NAME);
		String lastName = (String)userAttributes.get(UserManagerConstants.LAST_NAME);
		String userLogin = (String)userAttributes.get(UserManagerConstants.USER_LOGIN);
		
		if(userLogin ==null)
			userLogin=userManagerUtils.generateUserNameForUser(userAttributes);
		
		UserPassword password = PasswordUtils.generatePassword();
		String email = (String)userAttributes.get(UserManagerConstants.EMAIL);

		User user = new User();
		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastName);
		user.setUserName(userLogin);
		user.setPassword(password.getSecurePassword());
		user.setPasswordSalt(password.getSalt());
		user.setEmail(email);
		user.setCreatedBy("CPSInternal");
		user.setCreatedDate(new Date());
		
		userService.createUser(user);
		
	}


	private String getTargetAttribute(String attrName, SyncProfile profile) {

		List<ProfileAttribute> profileAttrs = profile.getProfileAttr();
		
		for(ProfileAttribute profileAttr:profileAttrs){
			
			String name = profileAttr.getName();
			String targetName = profileAttr.getTargetAttrName();
			
			System.out.println(attrName + " : " + name + " ############# " + targetName);
			
			if(name.equalsIgnoreCase(attrName))
				return targetName;
		
		}
		
		return null;
	}

	private void createORUpdateUser(Map<String, Object> userAttributes) {

	}

}
