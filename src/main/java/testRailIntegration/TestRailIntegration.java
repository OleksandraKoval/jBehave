package testRailIntegration;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Suite;
import config.ConfigurationManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestRailIntegration {

    private static TestRail testRail;
    private final static String endPoint = ConfigurationManager.getProperty("testRail.endPoint");
    private final static String username = ConfigurationManager.getProperty("testRail.username");
    private final static String password = ConfigurationManager.getProperty("testRail.password");
    public static int projectId;
    public static int suiteId;
    public static Run run;
    public static List<Integer> caseIds = new ArrayList<>();
    public static int currentCaseId;

    public static TestRail createTestRailInstance() {
        if (testRail == null) {
            testRail = TestRail.builder(endPoint, username, password).build();
        }
        return testRail;
    }

    public static int getProjectId() {
        return projectId;
    }

    public static void setProjectId(int projectId) {
        TestRailIntegration.projectId = projectId;
    }

    public static int getSuiteId() {
        return suiteId;
    }

    public static void setSuiteId(int suiteId) {
        TestRailIntegration.suiteId = suiteId;
    }


    // ********* Function to fetch Project id and suite Id ***********
    public static void setProjectSuiteId(String projectName, String suiteName) {
        try {
            List<Project> projectList = testRail.projects().list().execute();
            int pid = 0;
            int sid = 0;
            for (Project project : projectList) {
                if (project.getName().equals(projectName)) {
                    pid = project.getId();
                    setProjectId(pid);
                    System.out.println(pid);
                    break;
                }
            }
            if (pid != 0) {
                List<Suite> suiteList = testRail.suites().list(pid).execute();
                for (Suite s : suiteList) {
                    String sName = s.getName();
                    if (sName.equals(suiteName)) {
                        sid = s.getId();
                        setSuiteId(sid);
                        System.out.println(sid);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Run getRun() {
        return run;
    }

    public static void setRun(Run run) {
        TestRailIntegration.run = run;
    }

    // ***** Create Run Function *********
    public static void createRun() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyy kk mm s");
        Date date = new Date();
        String dateString = format.format(date);
        String runName = "Automation " + dateString;
        try {
            run = new Run();
            run = testRail.runs().add(getProjectId(),
                    run.setSuiteId(getSuiteId()).setName(runName).setIncludeAll(false)).execute();
            setRun(run);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // **** This will add case id into current run
    public static void updateRun(Object caseIdString) {
        try {
            if (null != caseIdString) {
                Integer caseId = Integer.parseInt(caseIdString.toString());
                caseIds.add(caseId);
                getRun().setCaseIds(caseIds);
                testRail.runs().update(getRun()).execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // **** below function help to add result for a test case
// with comment pass in function *********
    public static void addResult(String comment, int caseId) {
        try {
            if (null != testRail) {
                List customResultFields = testRail.resultFields().list().execute();
                testRail.results().addForCase(getRun().getId(), caseId, new Result().setComment(comment),
                        customResultFields).execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // **** Function use to add final status with comment for a test case ***
    public static void addStatusForCase(int statusId, int caseId) {
        try {
            List customResultFields = testRail.resultFields().list().execute();
            testRail.results().addForCase(getRun().getId(), caseId, new Result().setStatusId(statusId),
                    customResultFields).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCurrentCaseId() {
        return currentCaseId;
    }

    public static void setCurrentCaseId(int currentCaseId) {
        TestRailIntegration.currentCaseId = currentCaseId;
    }

    // ***** Close the current run ********
    public static void closeRun() {
        try {
            testRail.runs().close(getRun().getId()).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
