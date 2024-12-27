package com.Jio.stepDefinitions;


import static com.Jio.factory.DriverFactory.log;
import static com.Jio.hooks.Base.driver;
import static com.Jio.utilities.Constants.homePageURL;

import com.Jio.pages.DashboardPage;
import com.Jio.pages.HomePage;
import com.Jio.pages.LoginPage;
import com.Jio.pages.OtpPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDef {

    private HomePage homePage = new HomePage(driver);
    private LoginPage loginPage;
    private OtpPage otpPage;
    private DashboardPage dashboardPage;

    @Given("I navigate to the Ajio home page")
    public void i_navigate_to_the_ajio_home_page() {
        homePage.closeLocationPopup();
        Assert.assertEquals(homePage.getHomePageURL(), homePageURL);
        log.info("User has been successfully navigated to Home Page.");
    }

    @And("I click on Sign In button")
    public void i_click_on_sign_in_button() {
        loginPage = homePage.doClickLoginBtn();
    }

    @When("On Sign In modal I enter {string} and CONTINUE")
    public void onSignInModalIEnterAndCONTINUE(String number) {
        otpPage = loginPage.doPhoneNumberLogin(number);
    }

    @And("On OTP modal I enter valid OTP and click START SHOPPING")
    public void onOTPModalIEnterValidOTPAndClickSTARTSHOPPING() throws InterruptedException {
        dashboardPage = otpPage.enterOTP();
    }

    @Then("I should be logged in successfully with my {string} displayed")
    public void iShouldBeLoggedInSuccessfullyWithMyDisplayed(String userName) {
        String user = dashboardPage.getUserNameText(userName);
        log.info("Fetched User name text as: " + user);
        if (user.equalsIgnoreCase(userName)) {
            Assert.assertTrue(true);
            log.info("User: '" + userName + "', has been successfully logged in.");
        } else {
            log.error("Failed to log in Userame: " + userName);
            Assert.assertEquals(user, userName);
        }
    }
}