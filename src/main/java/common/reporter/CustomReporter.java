package common.reporter;

import TestRailIntegration.TestRailIntegration;
import com.gurock.testrail.APIClient;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlSuite;
import slackIntegration.EyesSlack;

import java.util.*;

public class CustomReporter extends TestRailIntegration implements IReporter {
    private static final Logger LOG = LoggerFactory.getLogger(CustomReporter.class);

    private static final Map<Integer, String> idAndComment = new HashMap<>();

    static {
        idAndComment.put(1, "Current status is : Passed");
        idAndComment.put(5, "Current status is : Failed");
    }

    private final Map<Integer, Long> statusIdAndCaseId = new HashMap<>();
    private final Map<Integer, List<String>> passedTC = new HashMap<>();
    private final Map<Integer, List<String>> failedTC = new HashMap<>();
    private final Map<Integer, List<String>> skippedTC = new HashMap<>();
    private List<String> passedTests = new ArrayList<>();
    private List<String> failedTests = new ArrayList<>();
    private List<String> skippedTests = new ArrayList<>();
    private List<String> allTestCasesInSuite = new ArrayList<>();
    private Map<Long, List<String>> idAndTitles = new HashMap<>();


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
                allTestCasesInSuite =
                        Arrays.stream(tc.getAllTestMethods()).map(ITestNGMethod::getMethodName).toList();
                passedTests =
                        tc.getPassedTests().getAllResults().stream().map(ITestResult::getName).toList();
                passedTC.put(1, passedTests);
                failedTests =
                        tc.getFailedTests().getAllResults().stream().map(ITestResult::getName).toList();
                failedTC.put(5, failedTests);
                skippedTests =
                        tc.getSkippedTests().getAllResults().stream().map(ITestResult::getName).toList();
                skippedTC.put(6, skippedTests);
                LOG.info("Passed tests for suite '" + suiteName +
                        "' is:" + passedTests.size());
                LOG.info("Failed tests for suite '" + suiteName +
                        "' is:" + failedTests.size());
                LOG.info("Skipped tests for suite '" + suiteName +
                        "' is:" + skippedTests.size());
            }
        }
        sendResultToSlack();
        sendResultToTestRail();
    }

    @SneakyThrows
    public void sendResultToSlack() {
        EyesSlack.sendTestExecutionStatusToSlack(passedTests, failedTests, skippedTests);

    }

    @SneakyThrows
    public void sendResultToTestRail() {
        createTestRailInstance();
        setProjectSuiteId("TestProject", "Master");
        createRun();

        createJsonObject();

        collectStatusIdAndTcId(passedTC, idAndTitles);
        collectStatusIdAndTcId(failedTC, idAndTitles);
        collectStatusIdAndTcId(skippedTC, idAndTitles);


        statusIdAndCaseId.forEach((key, value) -> updateRun(value));
        statusIdAndCaseId.forEach((key, value) -> addResult(idAndComment.get(key),
                Integer.parseInt(value.toString())));
        statusIdAndCaseId.forEach((key, value) -> addStatusForCase(key, Integer.parseInt(value.toString())));
    }

    @SneakyThrows
    private void createJsonObject() {
        APIClient client = new APIClient(endPoint);
        client.setUser(username);
        client.setPassword(password);
        JSONObject allTestCases =
                (JSONObject) client.sendGet("get_cases/" + projectId + "&suite_id=" + suiteId);

        JSONArray c = (JSONArray) allTestCases.get("cases");

        idAndTitles = new HashMap<>();

        for (Object o : c) {
            List<String> titles = new ArrayList<>();
            JSONObject obj = (JSONObject) o;
            Long id = (Long) obj.get("id");
            String title = (String) obj.get("title");
            if (allTestCasesInSuite.contains(title)) {
                titles.add(title);
                idAndTitles.put(id, titles);
            }
        }
    }


    private void collectStatusIdAndTcId(Map<Integer, List<String>> tests, Map<Long, List<String>> idAndTitles) {
        for (Map.Entry<Integer, List<String>> eachTc : tests.entrySet()) {
            int key = eachTc.getKey();
            List<String> value = eachTc.getValue();
            idAndTitles.forEach((key1, value1) -> {
                boolean isTCPresent = value1.stream().filter(value::contains).toList().size() > 0;
                if (isTCPresent) {
                    statusIdAndCaseId.put(key, key1);
                }
            });
        }
    }
}



