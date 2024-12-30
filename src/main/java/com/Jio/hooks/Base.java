package com.Jio.hooks;

import com.Jio.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Base extends DriverFactory {

    public WebDriver driver;


    private String fetchParameter(String parameterName, String defaultValue) {
        String value = System.getProperty(parameterName);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    @Before(order = 0)
    public void setUp(Scenario sc) {
        System.out.println("THREAD ID --- " + Thread.currentThread().threadId());
        String browser = fetchParameter("browser", "chrome");
        String env = fetchParameter("env", "sit");

        driver = initTlDriver(env, browser);
        log.info("*********** Running SCENARIO: '" + sc.getName() + "' ***********");
        System.out.println("********************** Running SCENARIO: '" + sc.getName() + "' **********************");
    }

    @After(order = 100)
    public void tearDown(Scenario sc) {
        if (sc.isFailed()) {
            // take screenshot:
            String screenshotName = sc.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            sc.attach(sourcePath, "image/png", screenshotName);
        }
        quitTlDriver();
        log.info("SCENARIO: '" + sc.getName() + "' is '" + sc.getStatus() + "'.");
        System.out.println("********************** SCENARIO: '" + sc.getName() +
                "' is '" + sc.getStatus() + "' **********************");
    }
}