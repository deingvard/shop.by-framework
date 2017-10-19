package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseEntity;

import java.util.List;


public class ElementCollection extends BaseElement {


    public ElementCollection(By loc) {
        super(loc);
    }

    public ElementCollection(By loc, String nameOf) {
        super(loc, nameOf);
    }

    public ElementCollection(String stringLocator, String nameOfElement) {
        super(stringLocator, nameOfElement);
    }

    protected String getElementType() {
        return getLoc("loc.collection");
    }

    public List<WebElement> getCollection(){
        List <WebElement> collection = findElements(this.getLocator());
        return collection;
    }

    public WebElement getElement(int index){
        List<WebElement> collection = getCollection();
        WebElement element = collection.get(index);
        return element;
    }

    public int getCollectionSize(){
        List<WebElement> collection = getCollection();
        int size = collection.size();
        return size;
    }
}
