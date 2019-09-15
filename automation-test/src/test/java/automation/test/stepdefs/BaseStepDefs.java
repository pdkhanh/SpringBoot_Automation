package automation.test.stepdefs;

import automation.test.pages.ResultPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import automation.test.AppConfig;
import automation.test.pages.HomePage;

@ContextConfiguration(classes = AppConfig.class)
@SpringBootTest
public class BaseStepDefs {

  @Autowired
  protected HomePage homePage;

  @Autowired
  protected ResultPage resultPage;

  @Autowired
  protected WebDriver webDriver;
}
