package org.cps.identity.model;

public class UserPassword {
	
	private String securePassword;
	private String salt;
	public String getSecurePassword() {
		return securePassword;
	}
	public void setSecurePassword(String securePassword) {
		this.securePassword = securePassword;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	
}
