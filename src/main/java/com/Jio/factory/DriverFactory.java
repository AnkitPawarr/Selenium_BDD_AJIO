package com.Jio.factory;

import com.Jio.utilities.ReadConfigurations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Properties;

import static com.Jio.utilities.Constants.LONG_WAIT;
import static com.Jio.utilities.Constants.MEDDIUM_WAIT;

public class DriverFactory {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public static final Logger log = LogManager.getLogger(DriverFactory.class);


    private ReadConfigurations readConfig;
    private Properties prop;

    private OptionsManager options;

    public WebDriver getTlDriver() {
        return tlDriver.get();
    }

    public WebDriver initTlDriver(String env, String browser) {
        log.info("Initializing WebDriver");
        readConfig = new ReadConfigurations();
        prop = readConfig.ReadConfiguration(env);
        options = new OptionsManager(prop);


        log.info("Running test suite on Browser: " + browser);
        if (browser != null) {
            if (browser.equalsIgnoreCase("chrome")) {
                tlDriver.set(new ChromeDriver(options.setChromeOptions()));
            } else if (browser.equalsIgnoreCase("firefox")) {
                tlDriver.set(new FirefoxDriver(options.setFirefoxOptions()));
            } else if (browser.equalsIgnoreCase("Edge")) {
                tlDriver.set(new EdgeDriver(options.setEdgeOptions()));
            } else if (browser.equalsIgnoreCase("Safari")) {
                tlDriver.set(new SafariDriver(options.setSafariOptions()));
            } else {
                log.warn("Invalid Browser name is passed. Please provide browser names from Chrome/Firefox/Edge/Safari.");
                log.info("setting the default browser as CHROME.");
                tlDriver.set(new ChromeDriver(options.setChromeOptions()));
            }
        } else {
            log.error("Browser name is not passed or kept empty.");
        }
        getTlDriver().manage().window().maximize();
        getTlDriver().manage().deleteAllCookies();

        getTlDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(LONG_WAIT));
        getTlDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(LONG_WAIT));

        getTlDriver().get(prop.getProperty("url"));
        log.info("Navigated to URL: " + prop.getProperty("url"));

        return getTlDriver();
    }

    public void quitTlDriver() {
        log.info("Closing the Browser and Removing driver thread.");
        getTlDriver().quit();
        tlDriver.remove(); // Ensure the thread-local reference is cleared
    }
}