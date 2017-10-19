package webdriver;

import java.util.Date;
import org.openqa.selenium.By;
import org.testng.Assert;

import webdriver.elements.Label;

/**
 * Base form
 */
public abstract class BaseForm extends BaseEntity {

	
	/**
	 * @uml.property name="titleLocator"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	protected By titleLocator; // detect form opening locator
	/**
	 * @uml.property name="title"
	 */
	protected String title; // title of a form
	/**
	 * @uml.property name="name"
	 */
	protected String name; // full name of form that outputted to log, for example, "Form 'Login'"

	
	/**
	 * Contructor
	 * @param locator Locator
	 * @param formTitle Name
	 */
	protected BaseForm(final By locator, final String formTitle) {
		init(locator, formTitle);
		assertIsOpen();
	}

	/** Contructor
	 * @param formlocator formlocator
	 * @param formTitle formTitle
	 */
	public BaseForm(final String formlocator, final String formTitle) {
		long before = new Date().getTime();
		title = formTitle;
		Label titlePicture = (Label) new Label(formlocator,title);
		try{
			Assert.assertTrue(titlePicture.isPresent());
			
			long openTime = new Date().getTime() - before;
			
				info(String.format(getLoc("loc.form.appears"), title) + String.format(" in %smsec",openTime));
			
		} catch (Throwable e) {
			fatal(String.format(getLoc("loc.form.doesnt.appears"), title));
		}
	}

	/**
	 * For logs
	 * @param message Message
	 * @return Message
	 */
	protected String formatLogMsg(final String message) {
		return message;
	}

    /**
     * In report: If "true": when opening page screenshot is taken
     * @return boolean
     */
    
	/**
	 * Init
	 * @param locator Locator
	 * @param formTitle Name
	 */
	private void init(final By locator, final String formTitle) {
		titleLocator = locator;
		title = formTitle;
		name = String.format(getLoc("loc.form") + " '%1$s'", this.title);
	}

	/**
	 * Check the opening form If the form is not open, the test stops working
	 */
	public void assertIsOpen() {
		long before = new Date().getTime();
		Label elem = new Label(titleLocator, title);
		try {
			elem.waitForIsElementPresent();
			long openTime = new Date().getTime() - before;
			
				info(String.format(getLoc("loc.form.appears"), title) + String.format(" in %smsec",openTime));
			
		} catch (Throwable e) {
			fatal(String.format(getLoc("loc.form.doesnt.appears"), title));
		}
	}

    
	

	


	

}
