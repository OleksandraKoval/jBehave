package listener;

import annotation.TestType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyListener implements ITestListener {
    private static final String UI_TYPES = "UI";
    private static final String TEST_UI_TYPES = "TEST_UI";

    public void onStart(ITestContext iTestContext) {
        List<String> uiTestTypes = getMethodsWithTestTypes(iTestContext, UI_TYPES);
        List<String> testUiTestTypes = getMethodsWithTestTypes(iTestContext, TEST_UI_TYPES);

        System.out.println("All methods size : " + getAllMethods(iTestContext).size());
        System.out.println("Methods with annotations @TestType size : " + getMethodsWithAnnotations(iTestContext).size());
        System.out.println("Methods with UI Test types : " + String.join(",", uiTestTypes) + " and count of this " +
                "tests : " + uiTestTypes.size());
        System.out.println("Methods with TEST_UI test types : " + String.join(",", testUiTestTypes) + " and count of " +
                "this tests : " + testUiTestTypes.size());
    }

    private List<String> getMethodsWithTestTypes(ITestContext iTestContext, String uiTypes) {
        return Arrays.stream(iTestContext.getAllTestMethods()).
                filter(ngMethods -> Objects.equals(ngMethods.getConstructorOrMethod().getMethod().getAnnotation(TestType.class).testType(), uiTypes))
                .map(ITestNGMethod::getMethodName).collect(Collectors.toList());
    }

    private List<String> getMethodsWithAnnotations(ITestContext iTestContext) {
        return Arrays.stream(iTestContext.getAllTestMethods()).map(ngMethods -> ngMethods.getConstructorOrMethod()
                .getMethod().getName()).collect(Collectors.toList());
    }

    private List<String> getAllMethods(ITestContext iTestContext) {
        return Arrays.stream(iTestContext.getAllTestMethods()).
                filter(ngMethods -> ngMethods.getConstructorOrMethod().getMethod().getAnnotation(TestType.class) != null)
                .map(ITestNGMethod::getMethodName).collect(Collectors.toList());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        //TODO
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        //TODO
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        //TODO
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        //TODO
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //TODO
    }
}
