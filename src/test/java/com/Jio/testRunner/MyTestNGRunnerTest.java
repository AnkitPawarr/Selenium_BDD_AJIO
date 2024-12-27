package com.Jio.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = {"com.Jio.uiStepDefinitions", "com.Jio.hooks"},
        features = {"src/test/resources/featureFiles"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports-json/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        }
)
public class MyTestNGRunnerTest extends AbstractTestNGCucumberTests {
}