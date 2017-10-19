package webdriver;

import org.testng.Assert;
import org.testng.Reporter;

/**
 * This class is using for a creating extended log. It implements a Singleton pattern
 */
public final class Logger {

	/**
	 * Locale
	 */
	public enum Locale {
		/**
		 * @uml.property name="eN"
		 * @uml.associationEnd
		 */
		EN, /**
		 * @uml.property name="rU"
		 * @uml.associationEnd
		 */
		RU
	}

	private static final Locale DEF_LOCALE = Locale.EN;
	private static final String QA_LOCALE = "aqa.locale";
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
	private static Logger instance = null;
	public static final String LOG_DELIMITER = "::";
	// This flag allows/restricts logging step names
	private static boolean logSteps = true;
	private static PropertiesResourceManager localManager = new PropertiesResourceManager(String.format("localization/loc_%1$s.properties", System.getProperty(QA_LOCALE, DEF_LOCALE.toString()).toLowerCase()));

	/**
	 * Gets locale
	 * @param key Key
	 * @return Locale
	 */
	protected static String getLoc(final String key) {
		return localManager.getProperty(key, key);
	}

	/**
	 * Sets the new local
	 * @param newLoc new Local manager
	 */
	public static void setNewLocalManager(final PropertiesResourceManager newLoc){
		localManager = newLoc;
	}

	/**
	 * local info
	 * @param msg Message
	 */
	public void infoLoc(final String msg) {
		String message = localManager.getProperty(msg, msg);
		logger.info(message);
		Reporter.log(message + "<br>");
	}

	/**
	 * Constructor
	 */
	private Logger() {
	}

	/**
	 * Implementation of the Singleton pattern
	 * @return Logger instance
	 */
	public static synchronized Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

	/**
	 * Logging a step number
	 * @param step - step number
	 */
	public void step(final int step) {
		logDelimMsg(getLoc("loc.logger.step") + String.valueOf(step));
	}

	/**
	 * Logging a several steps in a one action
	 * @param fromStep - the first step number to be logged
	 * @param toStep - the last step number to be logged
	 */
	public void step(final int fromStep, final int toStep) {
		logDelimMsg(getLoc("loc.logger.steps") + String.valueOf(fromStep) + "-" + String.valueOf(toStep));
	}

	/**
	 * This method is using for formatting almost all log records
	 * @param msg Formatted message
	 */
	private void logDelimMsg(final String msg) {
		if (logSteps) {
			info(String.format("--------==[ %1$s ]==--------", msg));
		}
	}

	/**
	 * This method logs test's name
	 * @param testName test's name
	 */
	public void logTestName(final String testName) {
		if (logSteps) {
			String formattedName = String.format("=====================  %1$s: '%2$s' =====================", getLoc("loc.logger.test.case"), testName);

			String delims = "";
			int nChars = formattedName.length();
			for (int i = 0; i < nChars; i++) {
				delims += "-";
			}
			info(delims);
			info(formattedName);
			info(delims);
			logDelimMsg(getLoc("loc.logger.test.preconditions"));
		}
	}

	/**
	 * Prints the dots
	 * @param count Amount of dots
	 */
	public void printDots(final int count) {
		String delims = "";
		for (int i = 0; i < count; i++) {
			delims += ".";
		}
		info(delims);
	}

	/**
	 * log Test End
	 * @param testName test's name
	 */
	public void logTestEnd(final String testName) {
		if (logSteps) {
			info("");
			String formattedEnd = String.format("***** %1$s: '%2$s' %3$s! *****", getLoc("loc.logger.test.case"), testName, getLoc("loc.logger.test.passed"));
			String stars = "";
			int nChars = formattedEnd.length();
			for (int i = 0; i < nChars; i++) {
				stars += "*";
			}
			info(stars);
			info(formattedEnd);
			info(stars);
			info("");
		}
	}

	/**
	 * This method logs test's name
	 * @param testName test's name
	 */
	public void logPrereqName(final String testName) {
		if (logSteps) {
			info(String.format("=====================  %1$s: '%2$s' =====================", getLoc("loc.logger.test.prerequisite.case"), testName));
			info("----------------------------------------------------------------------------------------------------");
			logDelimMsg("Preconditions");
		}
	}

	/**
	 * Prerequisites
	 * @param testName test's name
	 */
	public void logPrereq(final String testName) {
		if (logSteps) {
			info("----------------------------------------------------------------------------------------------------");
			info(String.format("=====================  %1$s: '%2$s' =====================", getLoc("loc.logger.test.prerequisite.case.creating"), testName));
			info("----------------------------------------------------------------------------------------------------");
		}
	}

	/**
	 * End of Prerequisites
	 * @param testName test's name
	 */
	public void logPrereqEnd(final String testName) {
		if (logSteps) {
			info(String.format("===================== %1$s: '%2$s' =====================", getLoc("loc.logger.test.prerequisite.case.succes"), testName));
			info("----------------------------------------------------------------------------------------------------");
		}
	}

	// All methods below are using for creation messages with a different level of importance
	/**
	 * Debug log
	 * @param message Message
	 */
	public void debug(final String message) {
		logger.debug(message);
		//msg = "<div class=\"skipped\">" + message + "</div>"; // yellow color from reportng css
		//Reporter.log(msg + "<br>");
	}

	/**
	 * Info log
	 * @param message Message
	 */
	public void info(final String message) {
		logger.info(message);
		Reporter.log(message + "<br>");
	}

	/**
	 * Warning log
	 * @param message Message
	 */
	public void warn(final String message) {
		String msg = message;
		logger.warn(message);
		msg = "<div class=\"skipped\">" + msg + "</div>"; // yellow color from reportng css
		Reporter.log(msg + "<br>");
	}

	/**
	 * Red Warning log
	 * @param message Message
	 */
	public void warnRed(final String message) {
		String msg = message;
		logger.warn(msg);
		msg = "<div class=\"failedConfig\">" + msg + "</div>"; // red color from reportng css
		Reporter.log(msg + "<br>");
	}

	/**
	 * Error log
	 * @param message Message
	 */
	public void error(final String message) {
		String msg = message;
		logger.error(message);
		msg = "<div class=\"failedConfig\">" + msg + "</div>"; // red color from reportng css
		Reporter.log(msg + "<br>");
	}

	/**
	 * Fatal log
	 * @param message Message
	 */
	public void fatal(final String message) {
		String msg = message;
		logger.fatal(message);
		msg = "<div class=\"failedConfig\">" + msg + "</div>"; // red color from reportng css
		Reporter.log(msg + "<br>");
		Assert.assertTrue(false, message);
	}
}
