package automation.test.pages;

import automation.core.BasePage;
import automation.core.annotation.PageObject;
import automation.test.data.Card;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@PageObject
public class ResultPage extends BasePage {
    @FindBy(xpath = "//div[@class='grid-row']")
    private WebElement elGridCard;

    @FindBy(xpath = "//div[@class='checkbox-wrap checkbox-group']")
    private WebElement elInsurersCheckboxGroup;

    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement elDestinationDropdown;

    @FindBy(xpath = "//div[@class='datepicker-days']")
    private WebElement elDatePicker;

    @FindBy(xpath = "//div[@class='date-from']")
    private WebElement elDateFrom;

    @FindBy(xpath = "//div[@class='date-to']")
    private WebElement elDateTo;

    @FindBy(xpath = "//div[@data-gb-name='travel-nav-data']//small")
    private WebElement elTravelDataFilter;

    public int getTotalNumberOfCard(){
        waitForElementExist(elGridCard);
        List<WebElement> el = elGridCard.findElements(By.xpath("//div[@class='col-sm-4 card-full']"));
        return el.size();
    }

    public void selectInsurers(String insurer){
        String[] listInsurers = insurer.split(", ");
        for (String Insurer : listInsurers) {
            String strXpath = "//div[@data-filter-name='" + Insurer + "']";
            WebElement element = driver.findElement(By.xpath(strXpath));
            element.click();
        }
    }

    public void selectSort(String sortValue){
        String strXpath = "//label[contains(text(), '"+ sortValue + "')]/..";
        WebElement element = driver.findElement(By.xpath(strXpath));
        element.click();
    }

    public void selectDetailFilter(String detailFilterValue){
        String strXpath = "//label[contains(text(), '" + detailFilterValue +"')]/..";
        WebElement element = driver.findElement(By.xpath(strXpath));
        waitForElementClickAble(element);
        element.click();
    }

    public void selectDestination(String destination){
        waitForElementClickAble(elDestinationDropdown);
        elDestinationDropdown.click();
        String strXpath = "//span[text()='" + destination + "']/../..";
        WebElement element = driver.findElement(By.xpath(strXpath));
        waitForElementClickAble(element);
        element.click();
    }

    public void verifySelectedFilter(String selectedFilter){
        waitForElementExist(elTravelDataFilter);
        String strTravelDataFilter = elTravelDataFilter.getText();
        String[] splitStr = strTravelDataFilter.split(" | ");
        String[] splitSelectedFilter = selectedFilter.split(",");

        Assert.assertTrue(splitStr[0].contains(splitSelectedFilter[0].trim()));
        Assert.assertTrue(splitStr[1].contains(splitSelectedFilter[1]));
        Assert.assertTrue(splitStr[2].contains(splitSelectedFilter[2]));
    }

    public void selectDateTime(String dateTime, String dateType){

        if(dateType.equals("TRAVEL START DATE")) {
            waitForElementClickAble(elDateFrom);
            elDateFrom.click();
        }else{
            waitForElementClickAble(elDateTo);
            elDateTo.click();
        }

        String[] splitDateTime = dateTime.split("-");
        String day = splitDateTime[0];
        String month  = splitDateTime[1] + " " + splitDateTime[2];

        waitForElementExist(elDatePicker);

        while(true){
            String pickerMonth = driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText();
            if(pickerMonth.equals(month)){
                break;
            }else {
                WebElement elDateTimeNext = driver.findElement(By.xpath("(//th[@class='next'])[1]"));
                waitForElementClickAble(elDateTimeNext);
                elDateTimeNext.click();
            }
        }

        WebElement elDateTimePicker = driver.findElement(By.xpath("(//table[@class='table-condensed'])[1]"));

        WebElement elSelectDate = elDateTimePicker.findElement(By.xpath(".//td[text()='" + day +"']"));
        waitForElementClickAble(elSelectDate);
        elSelectDate.click();
    }

    public List<Card> convertWebElementToCardObject(){
        waitForPageReady();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Card> listCard = new ArrayList<Card>();
        waitForElementExist(elGridCard);
        List<WebElement> listCardElement = elGridCard.findElements(By.xpath("//div[@class='card-wrapper']"));
        System.out.println(listCardElement.size());
        for (WebElement element : listCardElement) {
            Card card = new Card();
            card.setInsurer(element.getAttribute("data-insuer-name"));
            card.setPlanname(element.findElement(By.xpath(".//span[@class='plan-name']")).getText());
            card.setPolicyPrice(Integer.parseInt(element.findElement(By.xpath(".//div[@class='policy-price']")).getAttribute("premium")));
            card.setScoreResult(Float.parseFloat(element.findElement(By.xpath(".//div[@data-gb-name='score-result']")).getAttribute("data-gb-score-result")));
            card.setReviewScore(Integer.parseInt(element.findElement(By.xpath(".//div[@class='rating']")).getAttribute("data-review-score")));

            String tempStr = element.findElement(By.xpath(".//p[text()='Personal Accident']/following-sibling::p//span")).getText();
            card.setPersonalAcident(parseRowValueToInt(tempStr));

            tempStr = element.findElement(By.xpath(".//p[text()='Medical expenses while traveling']/following-sibling::p//span")).getText();
            card.setMedicalExpenses(parseRowValueToInt(tempStr));

            tempStr = element.findElement(By.xpath(".//p[text()='Trip cancellation']/following-sibling::p//span")).getText();
            card.setTripCancellation(parseRowValueToInt(tempStr));

            tempStr = element.findElement(By.xpath(".//p[text()='Trip termination']/following-sibling::p//span")).getText();
            card.setTripTerminaltion(parseRowValueToInt(tempStr));

            tempStr = element.findElement(By.xpath(".//p[text()='Loss of baggage & personal belongings']/following-sibling::p//span")).getText();
            card.setLossOfBaggageAndPersonal(parseRowValueToInt(tempStr));

            listCard.add(card);
        }
        return listCard;
    }

    public void verifyInsurerFilterResult(List<Card> listCard, String strInsurer){
        String[] listInsurers = strInsurer.split(", ");
        for (String insurer : listInsurers) {
            for (Card card : listCard) {
                if (!insurer.equals(card.getInsurer())){
                    Assert.fail("The card " + card.getInsurer() + " is not in the filter list");
                    return;
                }
                listCard.remove(card);
            }
        }
    }

    public List<Card> sortList(List<Card> originalListCard, String sortValue){
        String[] splitStr = sortValue.split(":");
        switch (splitStr[0]){
            case "Insurer":
                if(splitStr[1].trim().equals("A to Z")){
                    originalListCard.sort(Comparator.comparing(Card::getInsurer));
                }else{
                    originalListCard.sort(Comparator.comparing(Card::getInsurer).reversed());
                }
                break;
            case "Price":
                if(splitStr[1].trim().equals("Low to high")){
                    originalListCard.sort(Comparator.comparing(Card::getPolicyPrice));
                }else{
                    originalListCard.sort(Comparator.comparing(Card::getPolicyPrice).reversed());
                }
                break;
            case "Coverage Score":
                if(splitStr[1].trim().equals("Low to high")){
                    originalListCard.sort(Comparator.comparing(Card::getScoreResult));
                }else{
                    originalListCard.sort(Comparator.comparing(Card::getScoreResult).reversed());
                }
                break;
            case "Review score":
                if(splitStr[1].trim().equals("Low to high")){
                    originalListCard.sort(Comparator.comparing(Card::getReviewScore));
                }else{
                    originalListCard.sort(Comparator.comparing(Card::getReviewScore).reversed());
                }
                break;
        }
        originalListCard.forEach(e -> System.out.println(e));
        return originalListCard;
    }

    private int parseRowValueToInt(String tempStr){
        int value;
        try{
            if(tempStr.contains("deductible")){
                value = Integer.parseInt(tempStr.substring(tempStr.indexOf("₱") + 1, tempStr.indexOf("(")).replaceAll(",", "").trim());
            }else {
                value = Integer.parseInt(tempStr.substring(tempStr.lastIndexOf("₱") + 1).replaceAll(",", "").trim());
            }
        }catch (Exception ex){
            System.out.println(ex.toString());
            return 0;
        }
        return value;
    }
}
