package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        tags = "@Automated_Tests",
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber-reports.html"},
        monochrome = true
)
public class AutonomyTestRunner extends AbstractTestNGCucumberTests {
}
