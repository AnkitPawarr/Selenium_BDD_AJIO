package com.Jio.hooks;

import com.Jio.factory.DriverFactory;
import com.Jio.pages.HomePage;
import com.Jio.pages.LoginPage;
import com.Jio.pages.OtpPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Base extends DriverFactory {

    public static WebDriver driver;


    private String fetchParameter(String parameterName, String defaultValue) {
        String value = System.getProperty(parameterName);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    @Before(order = 0)
    public void setUp() {
        String browser = fetchParameter("browser", "chrome");
        String env = fetchParameter("env", "sit");

        driver = initTlDriver(env, browser);
    }

    @After
    public void tearDown() {
        quitTlDriver();
    }
}