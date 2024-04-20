package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", 
glue = "StepDefinations", 
tags = "@smoke", 
dryRun = false, 
plugin = {
		"pretty", "html:target/cucumber-reports/cucumber.html",
		"json:target/cucumber.json"
		})
public class TestRunner {
	
}