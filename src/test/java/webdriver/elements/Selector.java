package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class Selector extends BaseElement {

    public Selector(By locator) {
        super(locator);
    }

    public Selector(final By locator, final String name) {
        super(locator, name);
    }

    public Selector(String string, String name) {
        super(string, name);
    }

    protected String getElementType() {
        return getLoc("loc.select");
    }

    public void select(final String value){
        try {
            waitForIsElementPresent();
            info(String.format(getLoc("loc.select.selecting") + " '%1$s'", value));
            WebElement elementToSelect = browser.getDriver().findElement(this.getLocator());
            Select selectElement = new Select(elementToSelect);
            selectElement.selectByValue(value);
        } catch (UnexpectedTagNameException e){
            warn("Not found element to Select");
        }

    }

    public void selectAndWait(String value, By loc){
        select(value);
        browser.explicitWait(loc);
    }


}
