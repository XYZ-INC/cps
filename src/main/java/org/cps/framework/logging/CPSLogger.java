package org.cps.framework.logging;

public class CPSLogger {

	private String loggerName;

	public CPSLogger(String loggerName) {
		this.loggerName=loggerName;
 	}

	public void error(String message) {
		System.out.println("ERROR: " + loggerName + " : " + message);
 		
	}

	public void debug(String message) {
		System.out.println("DEBUG: " + loggerName + " : " + message);
	}

	
	public void info(String message) {
		System.out.println("DEBUG: " + loggerName + " : " + message);
	}

	
	public void notification(String message) {
		System.out.println("DEBUG: " + loggerName + " : " + message);
	}


}
