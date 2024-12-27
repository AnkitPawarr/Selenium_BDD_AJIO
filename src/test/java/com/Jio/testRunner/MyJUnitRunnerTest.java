package com.Jio.testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.Jio.uiStepDefinitions", "com.Jio.hooks"},
        features = {"src/test/resources/featureFiles"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        }
)
public class MyJUnitRunnerTest {

}