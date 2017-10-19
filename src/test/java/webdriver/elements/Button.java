package webdriver.elements;

import org.openqa.selenium.By;

/**
 * Class, Describing element button
 */
public class Button extends BaseElement {

    /**
     *
     * @param locator
     * @param name
     */
    public Button(final By locator, final String name) {
        super(locator, name);
    }

    public Button(String string, String name) {
    	 super(string, name);
	}


	protected String getElementType() {
        return getLoc("loc.button");
    }

    public boolean isEnabled(){
    	return this.getElement().isEnabled();
    }


	


	public Button(By locator) {
		super(locator);
	}


}
