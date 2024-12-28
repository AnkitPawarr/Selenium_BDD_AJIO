package com.Jio.pages;

import com.Jio.utilities.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.Jio.utilities.Constants.MEDDIUM_WAIT;

public class CartPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        eleUtil = new ElementUtil(driver);
    }

    @FindBy(xpath = "//div[@class='product-name']/div")
    private WebElement productName;

    public String getCartPageTitle() {
        return eleUtil.waitForTitleToBe(driver.getTitle(), MEDDIUM_WAIT);
    }

    public String getCartPageURL() {
        return eleUtil.waitForURLToBe(driver.getCurrentUrl(), MEDDIUM_WAIT);
    }

    public String getProductName() {
        String product = eleUtil.doGetText(productName);
        return product.split("\\|")[0].trim();
    }

}