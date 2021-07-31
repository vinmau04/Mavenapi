package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//below CMD is to Run Cucumber with junit
@RunWith(Cucumber.class)

//Assigning Features & stepDefination path
@CucumberOptions(
		features="src/test/java/features"
		,glue= {"stepDefinations"}
		// Below code is for Generating HTML Report as mentioned in 'pom.xml'
		,plugin ="json:target/jsonReports/cucumber-report.json"
		,tags=("@AddPlace")
				)
public class TestRunner {

}
