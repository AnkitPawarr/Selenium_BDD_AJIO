package com.Jio.pages;

import com.Jio.utilities.ElementUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.Jio.factory.DriverFactory.log;

import java.util.List;

import static com.Jio.utilities.Constants.*;

public class HomePage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        eleUtil = new ElementUtil(driver);
    }

    @FindBy(xpath = "//div[@class='modal-content ']")
    private List<WebElement> locationPopup;

    @FindBy(id = "closeBtn")
    private WebElement crossIcon;

    @FindBy(id = "loginAjio")
    private WebElement SignInOrLoginInBtn;

    @FindBy(xpath = "//div[@class=' guest-header']/ul/li[2]")
    private WebElement customerCare;

    @FindBy(xpath = "//div[@class=' guest-header']/ul/li[3]")
    private WebElement ajioLuxe;

    @FindBy(xpath = "//a[@title]")
    private List<WebElement> menuOptions;

    public void closeLocationPopup() {
        try {
            if (eleUtil.isElementDisplayed(locationPopup)) {
                log.info("Location Popup is present, hence closing the popup.");
                eleUtil.doClick(crossIcon);
            }
        } catch (NoSuchElementException e) {
            log.error("Location Popup is not present on Home page.");
            e.printStackTrace();
        }
    }

    public LoginPage doClickLoginBtn() {
        eleUtil.doClick(SignInOrLoginInBtn);
        return new LoginPage(driver);
    }

    public void doClickCustomerCareBtn() {
        eleUtil.doClick(customerCare);
    }

    public void doClickLuxeBtn() {
        eleUtil.doClick(ajioLuxe);
    }

    public String getHomePageTitle() {
        return eleUtil.waitForTitleToBe(driver.getTitle(), MEDDIUM_WAIT);
    }

    public String getHomePageURL() {
        return eleUtil.waitForURLToBe(driver.getCurrentUrl(), MEDDIUM_WAIT);
    }

    public ProductsPage doClickOnChildMenu(String parentMenu, String childMenu) {
        eleUtil.handleParentSubMenu(menuOptions, parentMenu, childMenu);

        return new ProductsPage(driver);
    }

    public void doClickOnParentMenu(String parentMenu) {
        WebElement e = eleUtil.getDynamicElementByTitle(menuOptions, parentMenu);

        eleUtil.doClick(e, SHORT_WAIT);
    }
}