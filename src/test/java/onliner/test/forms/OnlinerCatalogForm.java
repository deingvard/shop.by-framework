package onliner.test.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Форма страницы каталога. Идентификация страницы по классу активной кнопки "Каталог" с классом b-main-navigation__item_current
 */

public class OnlinerCatalogForm extends BaseForm {

    private Button tvButton = new Button(By.xpath("//li[@class='catalog-bar__item']/a[contains(@href,'catalog.onliner.by/tv')]"), "TV button");
    private Button catalogButtonSelected = new Button(By.xpath("//li[@class='b-main-navigation__item b-main-navigation__item_current']/a[contains(@href,'catalog.onliner.by')]"), "Selected Catalog Button");

    public OnlinerCatalogForm(){
        super(By.xpath("//li[@class='b-main-navigation__item b-main-navigation__item_current']/a[contains(@href,'catalog.onliner.by')]"), "Onliner Catalog");
    }

    public void assertCatalogButtonIsSelected(){
        assert(catalogButtonSelected.isPresent());
    }

    public void clickTvButton(){
        tvButton.clickAndWait();
    }


}
