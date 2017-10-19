package onliner.test.forms;


import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;
import webdriver.elements.Label;
import webdriver.elements.Selector;
import webdriver.elements.TextBox;

/**
 * Страница раздела "Телевизоры". Идентифицируем по содержанию заголовка первого уровня H1
 */

public class OnlinerTvForm extends BaseForm {

    private Label tvH1 = new Label(By.xpath("//h1[.='Телевизоры']"), "Onliner H1 contains \"Телевизоры\"");
    private CheckBox manufacturer = new CheckBox(By.xpath("//input[@value='samsung']/following-sibling::span"),"Samsung Checkbox");
    private TextBox maxPrice = new TextBox(By.xpath("//input[contains(@placeholder,'до')]"),"Price to");
    private TextBox minYear = new TextBox(By.xpath("//span[.=\"Дата выхода на рынок\"]/parent::div/following-sibling::div/div/div[1]/input"),"Year from");
    private Selector diagonalMin = new Selector(By.xpath("//span[.=\"Диагональ\"]/parent::div/following-sibling::div/div/div[1]/select"),"Diagonal from");
    private Selector diagonalMax = new Selector(By.xpath("//span[.=\"Диагональ\"]/parent::div/following-sibling::div/div/div[2]/select"),"Diagonal from");
    private By filterResult = By.xpath("//div[@title=\"Дата выхода на рынок\"]");


    public OnlinerTvForm() {
        super(By.xpath("//h1[.='Телевизоры']"),"Onliner TV Catalog");
    }

    public void assertTvH1(){
        assert(tvH1.isPresent());
    }

    public void selectManufacturer(String manufacturer){
        String locator = "//input[@value='"+manufacturer+"']/following-sibling::span";
        Label manufacturerCheckBox = new Label(By.xpath(locator), manufacturer+" Checkbox");
        manufacturerCheckBox.clickAndWait();
    }

    public void selectMaxPrice(String price){
        maxPrice.type(price);
    }

    public void selectMinYear(String year){
        minYear.tipeAndWait(year, filterResult);
    }

    public void selectDiagonalMin(String value){
        String valueModified = value.replace("\"","0");
        diagonalMin.select(valueModified);
    }

    public void selectDiagonalMax(String value){
        String valueModified = value.replace("\"","0");
        diagonalMax.select(valueModified);
    }

}
