package com.Jio.pages;

import com.Jio.utilities.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.Jio.utilities.Constants.MEDDIUM_WAIT;
import static com.Jio.utilities.Constants.SHORT_WAIT;

public class AddToCartPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        eleUtil = new ElementUtil(driver);
    }

    @FindBy(xpath = "//div[@class='btn-gold']")
    private WebElement addToBagBtn;

    public ProductsPage doClickAddToBag() throws InterruptedException {
        eleUtil.doClick(addToBagBtn, MEDDIUM_WAIT);
        Thread.sleep(2000);
        return new ProductsPage(driver);
    }
}
