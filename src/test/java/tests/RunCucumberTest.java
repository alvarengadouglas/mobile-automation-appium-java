package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        tags = "@mobileApp",
        glue = {
                "classpath:steps"
        },
        monochrome = true,
        dryRun = false,
        plugin = {
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "pretty",
                "html:target/cucumber-report/cucumber.html"
        })
public class RunCucumberTest {
}
