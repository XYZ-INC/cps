package org.cps.sync.engine.model;

public class FlatFileApplication extends SyncBase{
	
	private String fileName;
	private String accountIDColumn;
	private String profileName;
	
	
	public String getFileName() {

		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAccountIDColumn() {
		return accountIDColumn;
	}

	public void setAccountIDColumn(String accountIDColumn) {
		this.accountIDColumn = accountIDColumn;
	}

	public String getFlatFileBundleDirectory() {
		// TODO Get From DB
		return "E:/work/flatfile";
	}

	public String getFlatFileBundlePath() {
		// TODO Get From DB
		return "org.connid.bundles.flatfile-1.2.jar";
	}

	public String getBundleVersion() {
		// TODO Get From DB
		return "1.2";
	}

	public String getConnectorName() {
		// TODO Get From DB
		return "org.identityconnectors.flatfile.FlatFileConnector";
	}

	public String getBundleName() {
		// TODO Get From DB
		return "org.connid.bundles.flatfile";
	}

	public String getFileEncoding() {
		// TODO Get From DB OR Need to Think
		return "UTF-8";
	}

	public char getFieldDelimiter() {
		// TODO Get From DB OR Need to Think
		return ',';
	}

 
	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	
	
}
