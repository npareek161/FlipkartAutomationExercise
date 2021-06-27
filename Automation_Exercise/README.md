## FlipkartAutomationExercise 

This detail everything required for rapid importing project, adding build path dependencies, setting up drivers, running UI tests & checking reports.

## Importing Project
1) Download Automation_Exercise from GitHub.

2) Open Eclipse/IDE

3) Go to File -> Import

4) Select General -> Exisitng Projects into Workspace

5) Browse the Project

6) Click Finish

## Build Path Dependencies

1) Right-click on Automation_Exercise & go to on Build Path -> Configure Build Path

2) Go to Libraries Tab -> Under Classpath -> Add External JAR: selenium-server-standalone-3.141.59.jar & Add Library: TestNG

3) Click Apply & Close

## Drivers

- Under driver folder, overwrite updated version of chromedriver & geckodriver

## Running UI Test

1) Expand Automation_Exercise Folder

2) Right-click on testng.xml & run on Run As > TestNG Suite

3) You'll be able to see the result in console after execution is complete.

## Reports
- Reports are available in Automation_Exercise -> test-output -> junitreports

- TestNG Report is available in Automation_Exercise -> test-output -> emailable-report.html

- For Chrome Report: Automation_Exercise -> test-output -> Suite -> ChromeTest.html

- For FireFox Report: Automation_Exercise -> test-output -> Suite -> FirefoxTest.html