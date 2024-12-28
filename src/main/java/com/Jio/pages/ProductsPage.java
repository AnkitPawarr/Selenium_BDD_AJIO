package com.Jio.pages;

import com.Jio.utilities.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.Jio.utilities.Constants.*;
import static com.Jio.utilities.Constants.MEDDIUM_WAIT;

public class ProductsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        eleUtil = new ElementUtil(driver);
    }

    @FindBy(xpath = "//*[@id='products']//h1/div[2]")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@aria-label='Quick View']")
    private List<WebElement> quickView;

    @FindBy(xpath = "//div[@class='brand']")
    private List<WebElement> brand;

    @FindBy(xpath = "//div[@class='nameCls']")
    private List<WebElement> item;

    @FindBy(xpath = "//div[@class='ic-cart ']")
    private WebElement cartIcon;

    public String doGetProductPageHeader() {
        return eleUtil.doGetText(pageHeader);
    }

    public AddToCartPage doOpenQuickView(String brandName, String itemName) throws InterruptedException {
        eleUtil.doClickOnOtherElement(brand, brandName, item, itemName, quickView);
        Thread.sleep(1000);
        return new AddToCartPage(driver);
    }

    public CartPage doClickCartIcon() {
        eleUtil.doClick(cartIcon, SHORT_WAIT);
        return new CartPage(driver);
    }

    public String getPerfumePageTitle() {
        return eleUtil.waitForTitleToBe(driver.getTitle(), MEDDIUM_WAIT);
    }

    public String getPerfumePageURL() {
        return eleUtil.waitForURLToBe(driver.getCurrentUrl(), MEDDIUM_WAIT);
    }

}