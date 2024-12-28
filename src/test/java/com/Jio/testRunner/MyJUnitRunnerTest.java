package com.Jio.testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.Jio.stepDefinitions", "com.Jio.hooks"},
        features = {"src/test/resources/featureFiles"},
        monochrome = true,
        tags = "not @Login",
        plugin = {
                "pretty",
                "html:target/cucumber/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        }
)
public class MyJUnitRunnerTest {
}