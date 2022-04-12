package reporter;

import lombok.SneakyThrows;
import org.testng.*;
import org.testng.xml.XmlSuite;
import slackIntegration.EyesSlack;

import java.util.List;
import java.util.Map;

public class CustomReporter implements IReporter {

    @SneakyThrows
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
                               String outputDirectory) {

        //Iterating over each suite included in the test
        for (ISuite suite : suites) {

            //Following code gets the suite name
            String suiteName = suite.getName();

            //Getting the results for the said suite
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                List<String> passedTests =
                        tc.getPassedTests().getAllResults().stream().map(ITestResult::getName).toList();
                List<String> failedTests =
                        tc.getFailedTests().getAllResults().stream().map(ITestResult::getName).toList();
                List<String> skippedTests =
                        tc.getSkippedTests().getAllResults().stream().map(ITestResult::getName).toList();
                System.out.println("Passed tests for suite '" + suiteName +
                        "' is:" + passedTests.size());
                System.out.println("Failed tests for suite '" + suiteName +
                        "' is:" + failedTests.size());
                System.out.println("Skipped tests for suite '" + suiteName +
                        "' is:" + skippedTests.size());

                EyesSlack.sendTestExecutionStatusToSlack(passedTests, failedTests, skippedTests);
            }
        }
    }
}



