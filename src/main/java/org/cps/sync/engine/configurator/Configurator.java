package org.cps.sync.engine.configurator;

import java.util.List;

import org.cps.framework.exception.CPSException;
import org.cps.sync.engine.model.SyncBase;
import org.cps.sync.engine.model.SyncEvent;

 
public interface Configurator {

	public List<SyncEvent> performReconciliation(SyncBase base) throws CPSException;

}
