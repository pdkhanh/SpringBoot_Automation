package automation.test.pages;


import automation.core.BasePage;
import automation.core.annotation.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@PageObject
public class HomePage extends BasePage {

  @FindBy(xpath = "//a[@aria-controls='Travel']")
  private WebElement elTravel;

  @FindBy(xpath = "//a[@aria-controls='Insurance']")
  private WebElement elInsurance;

  @FindBy(xpath = "//button[text()='Show my results']")
  private WebElement elShowMyResultButton;

  @FindBy(xpath = "//div[@class='grid-row']")
  private WebElement elGridCard;

  public void clickTab(String tabName){
    String strXpath = "//a[@aria-controls='" + tabName + "']";
    WebElement element = driver.findElement(By.xpath(strXpath));
    element.click();
  }

  public void clickButton(String buttonName){
    String strXpath = "//button[text()='" + buttonName + "']";
    WebElement element = driver.findElement(By.xpath(strXpath));
    waitForElementClickAble(element);
    element.click();
  }

}
