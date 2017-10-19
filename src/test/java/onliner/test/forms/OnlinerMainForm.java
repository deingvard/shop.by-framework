package onliner.test.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

/**
 * Форма для главной страницы. Уникальный объект на странице - плитка топ-новостей с классом b-main-page-grid
 */


public class OnlinerMainForm extends BaseForm {

    private Button catalogButton = new Button(By.xpath("//li[@class='b-main-navigation__item']/a[contains(@href,'catalog.onliner.by')]"),"Navigation link to catalog");
    private Label onlinerNewsGrid = new Label(By.xpath("//div[contains(@class,'b-main-page-grid')]"),"Onliner News Grid");

    public OnlinerMainForm(){
        super(By.xpath("//div[contains(@class,'b-main-page-grid')]"), "Onliner Main");
    }

    public void assertNewsGrid(){
        assert(onlinerNewsGrid.isPresent());

    }

    public void clickCatalogButton(){
        catalogButton.clickAndWait();
    }

}
