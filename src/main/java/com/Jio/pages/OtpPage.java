package com.Jio.pages;

import com.Jio.utilities.ElementUtil;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.Jio.factory.DriverFactory.log;

import java.util.List;

public class OtpPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    public OtpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        eleUtil = new ElementUtil(driver);
    }

    @FindBy(xpath = "//h1")
    private WebElement otpHeader;

    @FindBy(xpath = "//input[@type='tel']")
    private WebElement otpInputBox;

    @FindBy(id = "error-msg")
    private List<WebElement> otpError;

    @FindBy(xpath = "//div[@class='otp-timer']/span")
    private WebElement resendOtpBtn;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement startShoppingBtn;


    public DashboardPage enterOTP() throws InterruptedException {
        eleUtil.doClick(otpInputBox);
        log.info("Entering OTP manually.");
        Thread.sleep(10000);

        eleUtil.doClick(startShoppingBtn);
        return new DashboardPage(driver);
    }
}