package org.cps.sync.engine.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.cps.sync.engine.model.EVENT_STATE;
import org.cps.sync.engine.model.SyncAttrValue;
import org.cps.sync.engine.model.SyncAttribute;
import org.cps.sync.engine.model.SyncEvent;
import org.cps.sync.engine.model.SyncProfile;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.ConnectorObject;
import org.identityconnectors.framework.common.objects.Uid;

 
public class ConnectorUtils {

	public static SyncEvent getSyncEvent(ConnectorObject obj,SyncProfile syncProfile) {

		SyncEvent syncEvent = new SyncEvent();
		List<SyncAttribute> attributes = new ArrayList<SyncAttribute>();
		
		Set<Attribute> attrs = obj.getAttributes();
		
		Iterator<Attribute> itr = attrs.iterator();
		
		Uid uid = obj.getUid();
		
		String uidName = uid.getName();
		String uidValue = uid.getUidValue();
		
		syncEvent.setUid(uidName);
		syncEvent.setUidValue(uidValue);
		
		while(itr.hasNext()){
			
			Attribute attr = itr.next();
			
			String name = attr.getName();
			
			System.out.println("attr : name : "+ name);
			
			
			List<Object> values = attr.getValue();
			List<SyncAttrValue> strValues = new ArrayList<SyncAttrValue>();
			SyncAttribute syncAttr = new SyncAttribute();

			for(Object value: values){
				
				SyncAttrValue attrValue = new SyncAttrValue();
				
				String strValue = (String)value;
				
				attrValue.setValue(strValue);
				attrValue.setSyncAttr(syncAttr);
				
				strValues.add(attrValue);
			}
			
			syncAttr.setName(name);
			syncAttr.setValue(strValues);
			
			attributes.add(syncAttr);
			
		}
		
		syncEvent.setAttributes(attributes);
		syncEvent.setEventState(EVENT_STATE.PENDING);
		syncEvent.setCreationDate(new Date());
		syncEvent.setSyncProfile(syncProfile);
		
		
		return syncEvent;
	}

}
