package automation.core;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public abstract class BasePage {
  @Value("${webapp.baseuri}")
  private String baseUri;

  @Value("${webapp.pageload-timeout:10}")
  private int pageLoadTimeout;

  @Autowired
  protected WebDriver driver;

  public void open() {
    driver.get(baseUri);
  }

  protected void waitFor(WebElement element) {
    ExpectedCondition<Boolean> condition = webDriver -> !element.getText().isEmpty();
    System.out.println(element.getText());

    WebDriverWait wait = new WebDriverWait(driver, pageLoadTimeout);
    wait.until(condition);
  }

  protected void waitForPageReady(){
    WebDriverWait wait = new WebDriverWait(driver, 15);

    JavascriptExecutor js = (JavascriptExecutor)driver;

    wait.until((ExpectedCondition<Boolean>) wd -> js.executeScript("return document.readyState").toString().equals("complete"));
  }
  
  protected void waitForElementExist(WebElement element) { 
	    WebDriverWait wait = new WebDriverWait(driver, pageLoadTimeout);
	    wait.until(ExpectedConditions.visibilityOf(element));
  }

  protected void waitForElementClickAble(WebElement element){
    WebDriverWait wait = new WebDriverWait(driver, pageLoadTimeout);
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

}
