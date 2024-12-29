package com.Jio.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Properties;

import static com.Jio.factory.DriverFactory.log;

public class OptionsManager {
    private Properties prop;

    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;
    private SafariOptions so;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions setChromeOptions() {
        co = new ChromeOptions();

        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            log.info("Running test on Chrome in Incognito mode.");
            co.addArguments("--disable-extensions");
            co.addArguments("--incognito");
        } else if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            log.info("Running test on Chrome in Headless mode.");
            co.addArguments("--disable-extensions");
            co.addArguments("--headless");
        } else {
            log.warn("Running test on Chrome in Default mode.");
        }
        /*co.addArguments("--disable-dev-shm-usage"); // Use shared memory more efficiently
        co.addArguments("--no-sandbox");           // Bypass OS security model (use cautiously)
        co.addArguments("--disable-popup-blocking");
        co.addArguments("--disable-infobars");
        co.addArguments("--remote-allow-origins=*"); // All cross-origin requests*/

        return co;
    }

    public FirefoxOptions setFirefoxOptions() {
        fo = new FirefoxOptions();

        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            log.info("Running test on Firefox in Incognito mode.");
            fo.addArguments("--disable-extensions");
            fo.addArguments("--incognito");
        } else if (Boolean.parseBoolean(prop.getProperty("--headless"))) {
            log.info("Running test on Firefox in Headless mode.");
            fo.addArguments("--disable-extensions");
            fo.addArguments("--headless");
        } else {
            log.warn("Running test on Firefox in Default mode.");
        }

        return fo;
    }

    public EdgeOptions setEdgeOptions() {
        eo = new EdgeOptions();

        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            log.info("Running test on Edge in Incognito mode.");
            eo.addArguments("--incognito");
        } else if (Boolean.parseBoolean(prop.getProperty("--headless"))) {
            log.info("Running test on Edge in Headless mode.");
            eo.addArguments("--headless");
        } else {
            log.warn("Running test on Edge in Default mode.");
        }

        return eo;
    }

    public SafariOptions setSafariOptions() {
        so = new SafariOptions();

        log.info("Running test on Safari in Default mode.");
        return so;
    }
}