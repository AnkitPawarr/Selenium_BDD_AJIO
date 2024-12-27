package com.Jio.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import static com.Jio.utilities.Constants.*;
import static com.Jio.factory.DriverFactory.log;

public class ReadConfigurations {

    private static Properties prop;


    public Properties ReadConfiguration(String env) {
        if (env == null || env.isEmpty()) {
            throw new IllegalArgumentException("Environment (env) is null or empty. Please set it correctly.");
        }
        prop = new Properties();
        FileInputStream fis = null;

        /*String env = System.getProperty("env");
        System.out.println("Running test suite on Env: " + env);
        log.info("Running test suite on ENV: " + env);
        mvn clean install -Denv="dev/sit/uat/prod"
        mvn clean install*/

        log.info("Running test suite on ENV: " + env);
        try {
            if (env.equalsIgnoreCase("dev")) {
                fis = new FileInputStream(DEV_CONFIG_PATH);
            } else if (env.equalsIgnoreCase("sit")) {
                fis = new FileInputStream(SIT_CONFIG_PATH);
            } else if (env.equalsIgnoreCase("uat")) {
                fis = new FileInputStream(UAT_CONFIG_PATH);
            } else if (env.equalsIgnoreCase("prod")) {
                fis = new FileInputStream(PROD_CONFIG_PATH);
            } else {
                log.error("Invalid Env is passed as: " + env);
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
            f.getCause();
            f.getMessage();
        }

        try {
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            e.getCause();
        }
        return prop;
    }

}
