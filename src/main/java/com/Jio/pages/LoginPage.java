package com.Jio.pages;

import com.Jio.utilities.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil eleUtil;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        eleUtil = new ElementUtil(driver);
    }


    @FindBy(id = "closeBtn")
    private WebElement crossIcon;

    @FindBy(xpath = "//h1")
    private WebElement welcomeHeader;

    @FindBy(xpath = "//div[@class='social-login']/div[1]")
    private WebElement fbLoginBtn;

    @FindBy(xpath = "//div[@class='social-login']/div[2]")
    private WebElement googleLoginBtn;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement numberInputBox;

    @FindBy(id = "error-msg")
    private List<WebElement> numberError;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement continueBtn;

    @FindBy(xpath = "//div[@class='btn-action-txt']/span")
    private WebElement footerText;

    public void doGoogleLogin() {
        String parentWindow = eleUtil.getParentWindow();
        eleUtil.doClick(googleLoginBtn);

        eleUtil.switchToChildWindow();
    }

    public OtpPage doPhoneNumberLogin(String phoneNo) {
        eleUtil.doSendKeys(numberInputBox, phoneNo);
        eleUtil.doClick(continueBtn);
        return new OtpPage(driver);
    }


}