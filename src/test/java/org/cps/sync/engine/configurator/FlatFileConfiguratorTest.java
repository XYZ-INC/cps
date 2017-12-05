package org.cps.sync.engine.configurator;

import java.util.ArrayList;
import java.util.List;

import org.cps.framework.exception.CPSException;
import org.cps.identity.common.UserManagerConstants;
import org.cps.sync.engine.model.FlatFileApplication;
import org.cps.sync.engine.model.MATCHING_ACTION;
import org.cps.sync.engine.model.MATCHING_CONDITION;
import org.cps.sync.engine.model.MatchingRule;
import org.cps.sync.engine.model.ProfileAttribute;
import org.cps.sync.engine.model.ProfileAttribute.PR_ATTR_TYPE;
import org.cps.sync.engine.model.SYNC_TYPE;
import org.cps.sync.engine.model.SyncProfile;
import org.cps.sync.engine.service.SyncEngineService;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlatFileConfiguratorTest {
	
	
//    private static final Logger logger = LoggerFactory.getLogger(FlatFileConfiguratorTest.class);
    
    @Autowired
    FlatFileConfigurator flatFileConfigurator;
    
    @Autowired
    SyncEngineService syncService;

    
	@Test
	@Transactional
	@Rollback(false)
	public void testCreateSyncProfile() {
		
		SyncProfile profile = new SyncProfile();
		
		profile.setName("FlatFileTest");
		profile.setSyncType(SYNC_TYPE.USER);
		profile.setTableName("SY_FFTest");
		
		MatchingRule rule = new MatchingRule();
		
		rule.setCpsAttibute("accountid");
		rule.setTgtAttribute(UserManagerConstants.EMPLOYEE_ID);
		rule.setMatchingAction(MATCHING_ACTION.CREATE);
		rule.setMatchingCondition(MATCHING_CONDITION.EQUALS);
		
		profile.setMatchingRule(rule);
		
		ProfileAttribute attr1 = new ProfileAttribute();
		
		attr1.setName("accountid");
		attr1.setTargetAttrName(UserManagerConstants.EMPLOYEE_ID);  
		attr1.setType(PR_ATTR_TYPE.STRING);
		attr1.setKey(true);
 		
		ProfileAttribute attr2 = new ProfileAttribute();
		
		attr2.setName("firstname");
		attr2.setTargetAttrName(UserManagerConstants.FIRST_NAME);
		attr2.setType(PR_ATTR_TYPE.STRING);

		
		ProfileAttribute attr3 = new ProfileAttribute();
		attr3.setName("lastname");
		attr3.setTargetAttrName(UserManagerConstants.LAST_NAME);
		attr3.setType(PR_ATTR_TYPE.STRING);

		ProfileAttribute attr4 = new ProfileAttribute();
		attr4.setName(UserManagerConstants.EMAIL);
		attr4.setTargetAttrName("email");
		attr4.setType(PR_ATTR_TYPE.STRING);

		ProfileAttribute attr5 = new ProfileAttribute();
		attr5.setName("changeNumber");
		attr5.setTargetAttrName("changeNumber");
		attr5.setType(PR_ATTR_TYPE.STRING);
		
		ProfileAttribute attr6 = new ProfileAttribute();
		attr6.setName("__UID__");
		attr6.setTargetAttrName("__UID__");
		attr6.setType(PR_ATTR_TYPE.STRING);
		
		ProfileAttribute attr7 = new ProfileAttribute();
		attr7.setName("__NAME__");
		attr7.setTargetAttrName("__NAME__");
		attr7.setType(PR_ATTR_TYPE.STRING);
		
		
		
		
		
		List<ProfileAttribute> profileAttrs = new ArrayList<ProfileAttribute>();
		profileAttrs.add(attr1);
		profileAttrs.add(attr2);
		profileAttrs.add(attr3);
		profileAttrs.add(attr4);
		profileAttrs.add(attr5);
		profileAttrs.add(attr6);
		profileAttrs.add(attr7);
		
		profile.setProfileAttr(profileAttrs);
		
		syncService.createSyncProfile(profile );
	}
    
	@Test
	@Transactional
	@Rollback(false)
	public void testPerformReconciliation() {		
		FlatFileApplication app = new FlatFileApplication();
		app.setFileName("E:\\work\\flatfile\\test.csv");
		app.setAccountIDColumn("accountid");
		app.setProfileName("FlatFileTest");

		try {
			flatFileConfigurator.performReconciliation(app);
		} catch (CPSException e) {
			e.printStackTrace();
		}
		
//		logger.error("@@@@@@@@@@@@@@@@@ TEST @@@@@@@@@@@@@@@@@@@@@");
	}

}
