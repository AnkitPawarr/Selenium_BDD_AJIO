package com.Jio.stepDefinitions;

import com.Jio.pages.*;
import com.Jio.utilities.ReadJSON;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.Jio.factory.DriverFactory.log;
//import static com.Jio.hooks.Base.driver;
import static com.Jio.factory.DriverFactory.tlDriver;
import static com.Jio.utilities.Constants.*;

public class CheckoutStepDef {
    private HomePage homePage = new HomePage(tlDriver.get());
    private LoginPage loginPage;
    private OtpPage otpPage;
    private DashboardPage dashboardPage;
    private ProductsPage productsPage;
    private AddToCartPage addToCartPage;
    private CartPage cartPage;

    private ReadJSON jsonUtil;


    /*@Given("The user is logged in with Phone_number")
    public void theUserIsLoggedInWithPhone_number() throws InterruptedException {
        log.info("Logging in.");
        homePage.closeLocationPopup();
        loginPage = homePage.doClickLoginBtn();

        jsonUtil = new ReadJSON();
        PhoneNumbers phoneNumber = jsonUtil.deserialization(LOGIN_JSON_PATH, PhoneNumbers.class);
        System.out.println("PHONE NUMBER IS ------------------- " + phoneNumber.getNumbers().getFirst());
        otpPage = loginPage.doPhoneNumberLogin(phoneNumber.getNumbers().getFirst());

        dashboardPage = otpPage.enterOTP();
        log.info("User is successfully Logged in with: " + phoneNumber.getNumbers().getFirst());
    }*/

    @Given("The user gets navigated to {string} of {string}")
    public void the_user_gets_navigated_to_of(String childMenu, String parentMenu) {
        homePage.closeLocationPopup();

        productsPage = homePage.doClickOnChildMenu(parentMenu, childMenu);
        //Assert.assertEquals(productsPage.getPerfumePageURL(), perfumePageURL);

        Assert.assertEquals(productsPage.doGetProductPageHeader(), childMenu);
        log.info("User has been successfully navigated to: " + childMenu);
    }

    @When("The user opens Quick view for {string} : {string}")
    public void the_user_opens_quick_view_for(String Brand, String Product) throws InterruptedException {
        addToCartPage = productsPage.doOpenQuickView(Brand, Product);
        log.info("Quick view Modal is opened for: " + Brand + " - " + Product);
    }

    @When("The user clicks Add_To_Bag and navigates to Cart page")
    public void the_user_clicks_add_to_bag_and_navigates_to_cart_page() throws InterruptedException {
        productsPage = addToCartPage.doClickAddToBag();
        log.info("Product has been successfully added to cart.");
        cartPage = productsPage.doClickCartIcon();
        Assert.assertEquals(cartPage.getCartPageURL(), cartPageURL);
        log.info("User has been successfully navigated to Cart.");
    }

    @Then("The user should be able to see {string} : {string}")
    public void the_user_should_be_able_to_see(String Brand, String Product) {
        String productName = cartPage.getProductName();
        Assert.assertEquals(productName, Brand + " -" + Product);
        log.info("Product: '" + Brand + ": " + Product + "' has been successfully added in Cart.");
    }
}