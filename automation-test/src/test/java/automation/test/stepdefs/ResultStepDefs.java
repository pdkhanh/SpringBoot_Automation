package automation.test.stepdefs;

import automation.test.data.Card;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class ResultStepDefs extends BaseStepDefs{

    @And("I can see more than (.*) cards")
    public void iCanSeeMoreThanCard(int numberOfExpectedCard){
        int actualCard = resultPage.getTotalNumberOfCard();
        Assert.assertTrue(actualCard > numberOfExpectedCard);
    }

    @And("I select (.*) insurer")
    public void iSelectInsurer(String insurer){
        resultPage.selectInsurers(insurer);
    }

    @And("I select (.*) sort")
    public void iSelectSort(String sortValue){
        resultPage.selectSort(sortValue);
    }

    @Then("I can see the list of (.*) insurer")
    public void iCanseeTheListOfInsurer(String insurer){
        List<Card> listCard = resultPage.convertWebElementToCardObject();
        resultPage.verifyInsurerFilterResult(listCard, insurer);
    }

    @Then("I can see the result list in (.*)")
    public void iCanSeeTheResultListIn(String sortValue){
        List<Card> listCard = resultPage.convertWebElementToCardObject();
        List<Card> sortListCard = new ArrayList<>(listCard);
        resultPage.sortList(sortListCard, sortValue);
        Assert.assertEquals(listCard, sortListCard);
    }

    @Then("I can select (.*) detail filter")
    public void iCannSelectDetailFilter(String detailFilterValue){
        resultPage.selectDetailFilter(detailFilterValue);
    }

    @Then("I can select (.*) destination")
    public void iCanSelectDestination(String destination){
        resultPage.selectDestination(destination);
    }

    @Then("I can select (.*) in (.*)")
    public void iCanSelectDateTime(String fromDate, String dateType){
        resultPage.selectDateTime(fromDate, dateType);
    }

    @Then("I can see the filter data have (.*)")
    public void iCanSeeTheFilterData(String filterData){
        resultPage.verifySelectedFilter(filterData);
    }
}
