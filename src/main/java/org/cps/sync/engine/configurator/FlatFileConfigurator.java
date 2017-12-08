package org.cps.sync.engine.configurator;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.cps.framework.exception.CPSException;
import org.cps.framework.exception.InvalidBaseConfigurationException;
import org.cps.framework.logging.CPSLogger;
import org.cps.identity.common.CPSMessages;
import org.cps.identity.common.MessageUtils;
import org.cps.sync.engine.model.FlatFileApplication;
import org.cps.sync.engine.model.SyncBase;
import org.cps.sync.engine.model.SyncEvent;
import org.cps.sync.engine.model.SyncProfile;
import org.cps.sync.engine.service.SyncEngineService;
import org.cps.sync.engine.utils.ConnectorUtils;
import org.identityconnectors.common.IOUtil;
import org.identityconnectors.flatfile.FlatFileConfiguration;
import org.identityconnectors.flatfile.FlatFileConnector;
import org.identityconnectors.framework.api.APIConfiguration;
import org.identityconnectors.framework.api.ConfigurationProperties;
import org.identityconnectors.framework.api.ConfigurationProperty;
import org.identityconnectors.framework.api.ConnectorFacade;
import org.identityconnectors.framework.api.ConnectorFacadeFactory;
import org.identityconnectors.framework.api.ConnectorInfo;
import org.identityconnectors.framework.api.ConnectorInfoManager;
import org.identityconnectors.framework.api.ConnectorInfoManagerFactory;
import org.identityconnectors.framework.api.ConnectorKey;
import org.identityconnectors.framework.common.objects.ConnectorObject;
import org.identityconnectors.framework.common.objects.ObjectClass;
import org.identityconnectors.framework.common.objects.OperationOptions;
import org.identityconnectors.framework.common.objects.ResultsHandler;
import org.identityconnectors.framework.common.objects.filter.Filter;
import org.identityconnectors.framework.common.objects.filter.FilterVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.cps.sync.engine.processor.SyncEventProcessor;

@Service("flatFileConfigurator")
public class FlatFileConfigurator implements Configurator, ResultsHandler {

	private static CPSLogger logger = new CPSLogger("FlatFileConfigurator");
	private List<SyncEvent> syncEvents = new ArrayList<SyncEvent>();
	private SyncProfile profile;

	@Autowired
	private SyncEngineService syncService;
	
    @Autowired
    SyncEventProcessor SyncEventProcessor;


	public List<SyncEvent> performReconciliation(SyncBase base)
			throws CPSException {

		if (!(base instanceof FlatFileApplication)) {
			logger.error(CPSMessages.CPS001_FFCONFIG_BAD_SYNCBASE);
			throw new InvalidBaseConfigurationException(
					MessageUtils
							.getExceptionMessage(CPSMessages.CPS001_FFCONFIG_BAD_SYNCBASE));
		}

		FlatFileApplication flatFileApp = (FlatFileApplication) base;

		String FILENAME = flatFileApp.getFileName();// "E:\\work\\flatfile\\test.csv";
		String ACCOUNTID = flatFileApp.getAccountIDColumn();// "accountid";
		String flatFileBundleDirectory = flatFileApp
				.getFlatFileBundleDirectory();
		String flatFileBundlePath = flatFileApp.getFlatFileBundlePath();
		String bundleName = flatFileApp.getBundleName();
		String bundleVersion = flatFileApp.getBundleVersion();
		String connectorName = flatFileApp.getConnectorName();
		String fileEncoding = flatFileApp.getFileEncoding();
		char fieldDelimiter = flatFileApp.getFieldDelimiter();
		String profileName = flatFileApp.getProfileName();

		profile = syncService.getSyncProfile(profileName);

		if (profile == null) {
			logger.error(CPSMessages.CPS001_INVALID_SYNC_PROFILE + " : " + profileName);

			throw new CPSException(CPSMessages.CPS001_INVALID_SYNC_PROFILE);
		}

		// create the connector configuration..
		FlatFileConfiguration config = new FlatFileConfiguration();
		config.setFile(new File(FILENAME));
		config.setUniqueAttributeName(ACCOUNTID);
		config.validate();
		// create a new connector..
		FlatFileConnector cnt = new FlatFileConnector();
		cnt.init(config);

		ConnectorInfoManagerFactory fact = ConnectorInfoManagerFactory.getInstance();

		File bundleDirectory = new File(flatFileBundleDirectory);

		URL url;
		try {
			url = IOUtil.makeURL(bundleDirectory, "/" + flatFileBundlePath);
			logger.debug("bundleName --> " + bundleName);
			logger.debug("bundleVersion --> " + bundleVersion);
			logger.debug("connectorName --> " + connectorName);

			ConnectorInfoManager manager = fact.getLocalManager(url);
			ConnectorKey key = new ConnectorKey(bundleName, bundleVersion,
					connectorName);

			logger.debug("url --> " + url);
			logger.debug("key --> " + key);
			logger.debug("manager --> " + manager);
			logger.debug("ConnectorInfos --> " + manager.getConnectorInfos());

			List<ConnectorInfo> l = manager.getConnectorInfos();

			for (ConnectorInfo ll : l) {
				logger.debug("BundleName $$$ "
						+ ll.getConnectorKey().getBundleName());
				logger.debug("BundleVersion $$$ "
						+ ll.getConnectorKey().getBundleVersion());
				logger.debug("ConnectorName $$$ "
						+ ll.getConnectorKey().getConnectorName());
			}
			ConnectorInfo info = manager.findConnectorInfo(key);

			logger.debug("ConnectorInfo $$$ " + info);

			// From the ConnectorInfo object, create the default
			// APIConfiguration.
			APIConfiguration apiConfig = info.createDefaultAPIConfiguration();

			// From the default APIConfiguration, retrieve the
			// ConfigurationProperties.
			ConfigurationProperties properties = apiConfig
					.getConfigurationProperties();

			ConnectorFacadeFactory factory = ConnectorFacadeFactory
					.getInstance();

			// Print out what the properties are (not necessary).
			List<String> propertyNames = properties.getPropertyNames();
			for (String propName : propertyNames) {
				ConfigurationProperty prop = properties.getProperty(propName);
				logger.debug("Property Name: " + prop.getName()
						+ "\tProperty Type: " + prop.getType());
			}

			properties.setPropertyValue("fieldDelimiter", fieldDelimiter);
			properties.setPropertyValue("file", new File(FILENAME));
			properties.setPropertyValue("uniqueAttributeName", ACCOUNTID);
			properties.setPropertyValue("encoding", fileEncoding);

			logger.debug("fieldDelimiter : " + fieldDelimiter);
			logger.debug("FILENAME : " + FILENAME);
			logger.debug("ACCOUNTID : " + ACCOUNTID);
			logger.debug("fileEncoding : " + fileEncoding);

			// Use the ConnectorFacadeFactory's newInstance() method to get a
			// new connector.
			ConnectorFacade conn = ConnectorFacadeFactory.getInstance()
					.newInstance(apiConfig);

			// Make sure you have set up the Configuration properly.
			conn.validate();

			logger.debug("Validated now");

			// TODO Need to think on Filter
			Filter filter = new NoFilter();
			OperationOptions options = null;
			// ResultsHandler handler = new MyResultsHandler();
			ResultsHandler handler = this;
			conn.search(ObjectClass.ACCOUNT, filter, handler, options);

			System.out.println("syncEvents : " + syncEvents);

			createSyncEvents(syncEvents);
			
			publishSyncEventCreation(profile);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CPSException(e.getMessage());
		}

		return null;
	}

	private void publishSyncEventCreation(SyncProfile syncProfile) {
		// TODO This will be done VIA JMS
		// Currently doing it synchronously
		
		String profileName = syncProfile.getName();
		SyncEventProcessor.eventsCreated(profileName);
		
	}

	private void createSyncEvents(List<SyncEvent> syncEvents) {
		syncService.createEvents(syncEvents);
	}

	static class NoFilter implements Filter {
		public boolean accept(ConnectorObject obj) {
			return true;
		}

		public <R, P> R accept(FilterVisitor<R, P> v, P p) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public boolean handle(ConnectorObject obj) {
		logger.debug("my connector object : " + obj);
		logger.debug("my connector object : " + obj.getUid());
		logger.debug("my connector object : " + obj.getAttributes());

		SyncEvent syncEvent = ConnectorUtils.getSyncEvent(obj, profile);
		syncEvents.add(syncEvent);
		return true;
	}

	public static void main(String args[]) {
		FlatFileConfigurator flatFileConfigurator = new FlatFileConfigurator();

		FlatFileApplication app = new FlatFileApplication();

		app.setFileName("E:\\work\\flatfile\\test.csv");
		app.setAccountIDColumn("accountid");

		try {
			flatFileConfigurator.performReconciliation(app);
		} catch (CPSException e) {
			e.printStackTrace();
		}

	}

}
