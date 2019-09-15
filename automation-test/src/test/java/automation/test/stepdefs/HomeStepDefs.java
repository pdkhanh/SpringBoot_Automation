package automation.test.stepdefs;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class HomeStepDefs extends BaseStepDefs {

  @Given("I am at home page")
  public void iAmAt(){
    homePage.open();
  }

  @Then("I click on (.*) tab")
  public void iClickOnTab(String tabName){
    homePage.clickTab(tabName);
  }

  @Then("I click on (.*) button")
  public void iClickOnButton(String buttonName){
    homePage.clickButton(buttonName);
  }
}
