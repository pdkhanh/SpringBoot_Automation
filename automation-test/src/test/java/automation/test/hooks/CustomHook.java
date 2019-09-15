package automation.test.hooks;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class CustomHook {
  private static boolean initData;

  @Autowired
  private WebDriver webDriver;

  //To prevent the anotiation run twice, use isRun to skip is isRun - true
  boolean isRun = false;

  @Before
  public void beforeAllScenarios() {

  }

  @After
  public void afterScenario(Scenario scenario) {

    if(isRun == false) {
      Reporter.loadXMLConfig(new File("src\\test\\config\\report.xml"));
      Reporter.setSystemInfo("user", System.getProperty("user.name"));
      Reporter.setSystemInfo("os", "Window");

      if (scenario.isFailed()) {
          Reporter.addStepLog("Current Page URL is " + webDriver.getCurrentUrl());
          String screenshotFileName = "C:\\tmp\\" + scenario.getName().replace("?", "") + ".png";
          File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
          File destFile = new File(screenshotFileName);
          try {
            FileUtils.copyFile(scrFile, destFile);
            Reporter.addScreenCaptureFromPath(screenshotFileName, scenario.getName());
//            System.out.println(screenshotFileName);
          } catch (Exception ex) {
        }
      }
      isRun = true;
    }
    else{
      isRun = false;
    }
    webDriver.quit();
  }
}
