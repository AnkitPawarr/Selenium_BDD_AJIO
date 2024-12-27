package com.Jio.pages;

import com.Jio.utilities.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.Jio.utilities.Constants.SHORT_WAIT;


public class DashboardPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        eleUtil = new ElementUtil(driver);
    }

    @FindBy(xpath = "//span[@class='ignore-sentence-case']")
    private List<WebElement> userName;

    @FindBy(xpath = "//div[@class=' guest-header']/ul/li[2]")
    private WebElement myAccount;

    @FindBy(xpath = "//div[@class=' guest-header']/ul/li[3]")
    private WebElement signOut;

    @FindBy(xpath = "//a[@title]")
    private List<WebElement> menuOptions;

    /*@FindBy(xpath = "//a[@title='WOMEN']")
    private WebElement womenMenu;

    @FindBy(xpath = "//a[@title='KIDS']")
    private WebElement kidsMenu;

    @FindBy(xpath = "//a[@title='BEAUTY']")
    private WebElement beautyMenu;

    @FindBy(xpath = "//a[@title='Home & Kitchen']")
    private WebElement homeAndKitchenMenu;*/


    public String getUserNameText(String name) {
        if (eleUtil.isElementDisplayed(userName)) {
            return eleUtil.doGetText(userName.getFirst());
        }
        throw new RuntimeException("User: '" + name + "', is Unable to Log in.");
    }

    public void doClickMyAccount() {
        eleUtil.doClick(myAccount);
    }

    public void doSignOut() {
        eleUtil.doClick(signOut);
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