package onliner.test;

import onliner.test.forms.OnlinerCatalogForm;
import onliner.test.forms.OnlinerMainForm;
import onliner.test.forms.OnlinerTvForm;
import webdriver.BaseTest;
import webdriver.PropertiesResourceManager;

public class OnlinerTest extends BaseTest {

    PropertiesResourceManager propertiesResourceManager = new PropertiesResourceManager("onliner.properties");
    private String brand = propertiesResourceManager.getProperty("brand");
    private String price = propertiesResourceManager.getProperty("price");
    private String year = propertiesResourceManager.getProperty("year");
    private String minDiagonal = propertiesResourceManager.getProperty("minDiagonal");
    private String maxDiagonal = propertiesResourceManager.getProperty("maxDiagonal");

    public void runTest() throws InterruptedException {


        logger.step(1);
        OnlinerMainForm onliner = new OnlinerMainForm();
        onliner.assertNewsGrid();

        logger.step(2);
        onliner.clickCatalogButton();

        logger.step(3);
        OnlinerCatalogForm catalog = new OnlinerCatalogForm();
        catalog.assertCatalogButtonIsSelected();

        logger.step(4);
        catalog.clickTvButton();

        logger.step(5);
        OnlinerTvForm tvCatalog = new OnlinerTvForm();
        tvCatalog.assertTvH1();

        logger.step(6);
        tvCatalog.selectManufacturer(brand);
        tvCatalog.selectMaxPrice(price);
        tvCatalog.selectDiagonalMin(minDiagonal);
        tvCatalog.selectDiagonalMax(maxDiagonal);
        tvCatalog.selectMinYear(year);

    }
}
