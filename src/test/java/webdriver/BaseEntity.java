package webdriver;

import static org.testng.AssertJUnit.assertTrue;

import java.io.BufferedReader;

import java.io.InputStreamReader;


import org.testng.ITestContext;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import webdriver.Browser.Browsers;



/**
 * BaseEntity
 */
public abstract class BaseEntity {

	protected static int stepNumber = 1;
	protected static Logger logger = Logger.getInstance();
	protected static Browser browser = Browser.getInstance();
	protected static boolean isLogged = false;
	protected static int screenIndex = 0;
	protected ITestContext context;

	/**
	 * Get locale
	 * 
	 * @param key
	 *            key
	 * @return value
	 */
	protected static String getLoc(final String key) {
		return Logger.getLoc(key);
	}

	// ==============================================================================================
	// Methods for logging

	/**
	 * Format message.
	 * 
	 * @param message
	 *            message
	 * @return null
	 */
	protected abstract String formatLogMsg(String message);

	

	/**
	 * Informative message.
	 * 
	 * @param message
	 *            Message
	 */
	protected void info(final String message) {
		logger.info(formatLogMsg(message));
	}

	/**
	 * Warning.
	 * 
	 * @param message
	 *            Message
	 */
	protected void warn(final String message) {
		logger.warn(formatLogMsg(message));
	}

	/**
	 * Error message without stopping the test.
	 * 
	 * @param message
	 *            Message
	 */
	protected void error(final String message) {
		logger.error(formatLogMsg(message));
	}

	/**
	 * Fatal error message.
	 * 
	 * @param message
	 *            Message
	 */
	protected void fatal(final String message) {
		logger.fatal(formatLogMsg(message));
		assertTrue(formatLogMsg(message), false);
	}

	/**
	 * Logging a step number.
	 * 
	 * @param step
	 *            - step number
	 */
	public static void logStep(final int step) {
		logger.step(step);
	}

	/**
	 * Logging a several steps in a one action
	 * 
	 * @param fromStep
	 *            - the first step number to be logged
	 * @param toStep
	 *            - the last step number to be logged
	 */
	public void logStep(final int fromStep, final int toStep) {
		logger.step(fromStep, toStep);
	}

	// ==============================================================================================
	// Asserts

	/**
	 * Universal method
	 * 
	 * @param isTrue
	 *            Condition
	 * @param passMsg
	 *            Positive message
	 * @param failMsg
	 *            Negative message
	 */
	public void doAssert(final Boolean isTrue, final String passMsg,
			final String failMsg) {
		if (isTrue) {
			info(passMsg);
		} else {
			fatal(failMsg);
		}
	}

	/**
	 * Assert Objects are Equal
	 * 
	 * @param expected
	 *            Expected Value
	 * @param actual
	 *            Actual Value
	 */
	public void assertEquals(final Object expected, final Object actual) {
		if (!expected.equals(actual)) {
			fatal("Expected value: '" + expected + "', but was: '" + actual
					+ "'");
		}
	}

	

	/**
	 * Assert Objects are Equal
	 * 
	 * @param message
	 *            Fail Message
	 * @param expected
	 *            Expected Value
	 * @param actual
	 *            Actual Value
	 */
	public void assertEquals(final String message, final Object expected,
			final Object actual) {
		if (!expected.equals(actual)) {
			fatal(message);
		}
	}

	

	

	/**
	 * killing process by Image name
	 */
	public void checkAndKill() {
		logger.info("killing processes");
		try {
			String line;
			Process p = Runtime.getRuntime().exec(
					String.format("taskkill /IM %1$s.exe /F",
							Browser.currentBrowser.toString()));
			BufferedReader input = new BufferedReader(new InputStreamReader(p
					.getInputStream()));
			while (((line = input.readLine()) != null)) {
				logger.info(line);
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Only for IE
	 */
	public void acceptUnsignedSertificate() {
		if (Browser.currentBrowser == Browsers.IEXPLORE) {
			browser.navigate("javascript:document.getElementById('overridelink').click()");
		}
	}

	/**
	 * Before Class method
	 */
	@BeforeClass
	public void before(ITestContext context) {
		this.context = context;
		browser = Browser.getInstance();
		browser.windowMaximise();
		stepNumber = 1;
		
	}

	/**
	 * Close browser after each test Class
	 */
	@AfterClass
	public void after() {
		
		if (browser.isBrowserAlive()) {
			browser.exit();
			checkAndKill();
		}
	}

	/**
	 * Logging steps
	 */
	protected void LogStep() {
		logStep(stepNumber++);
	}

	/**
	 * Logging steps with info
	 */
	protected void LogStep(final String info) {
		logStep(stepNumber++);
		logger.info(String.format("----==[ %1$s ]==----", info));
	}

	

	
	

}
