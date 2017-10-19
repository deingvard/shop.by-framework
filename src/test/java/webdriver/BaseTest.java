package webdriver;

import org.testng.annotations.Test;

/**
 * An abstract class that describes the basic test application contains
 * methods for logging and field test settings (options)
 */
public abstract class BaseTest extends BaseEntity{

	/**
	 * To override.
	 */
	public abstract void runTest() throws InterruptedException;

	/**
	 * Test
	 * @throws Throwable Throwable
	 */
	@Test
	public void xTest() throws Throwable {
		Class<? extends BaseTest> currentClass = this.getClass();
		
		try {
			logger.logTestName(currentClass.getName());
			browser.windowMaximise();
			browser.navigate(Browser.getBaseUrl());
			runTest();
			logger.logTestEnd(currentClass.getName());
		} catch (Throwable e) {
			
				logger.warn("");
				logger.warnRed(getLoc("loc.test.failed"));
				logger.warn("");
				logger.fatal(e.getMessage());
			}
		
	}

	/**
	 * Format logging
	 * @param message Message
	 * @return Message
	 */
	protected String formatLogMsg(final String message) {
		return message;
	}

}
