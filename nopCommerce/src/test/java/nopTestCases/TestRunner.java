package nopTestCases;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // runs all feature files in folder
    glue = {"nopTestCases"},          // all step definitions
    plugin = {
        "pretty",
        "html:target/cucumber_reports/htmlReport.html",
        "json:target/cucumber_reports/report.json",
        "junit:target/cucumber_reports/report.xml"
    },
    monochrome = true
)
public class TestRunner {}

