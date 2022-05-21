# jBehaveMentoring

## How to run
Use the following command:<br>
mvn clean test -**Dtest**=testClassName -**Denv**=env -**DdriverManager**=driver<br>

**env** - available environments can be found by path src\main\resources\environments<br>
**driver** - available drivers can be found by path common.DriverBinariesSetter.<br>

You can use EDGE/FIREFOX/CHROME - for local execution<br>
SAUCE_LAB_CHROME/SAUCE_LAB_FIREFOX/SAUCE_LAB_EDGE - for remote execution<br>

Examples:<br>
mvn clean test -Dtest=CheckElementPresent -Denv=dev -DdriverManager=SAUCE_LAB_FIREFOX - remoteWebDriver<br>
mvn clean test -Dtest=CheckElementPresent -Denv=qa -DdriverManager=EDGE - localDriver

To run the whole suite skip **Dtest** parameter

Examples:<br>
mvn clean test -Denv=dev -DdriverManager=CHROME<br>
mvn clean test -Denv=dev -DdriverManager=SAUCE_LAB_CHROME

## How to generate report
Report generation is available only after local execution.<br>
Use the following command:<br>
**mvn allure:serve** 
