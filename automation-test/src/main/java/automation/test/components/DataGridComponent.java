package automation.test.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import automation.core.annotation.ComponentObject;

import java.util.List;
import java.util.stream.Collectors;

@ComponentObject
public class DataGridComponent {
  @FindBy(css = "table > thead > tr > th")
  private List<WebElement> columnElements;

  @FindBy(css = "table > tbody > tr")
  private List<WebElement> rowElements;

  public List<String> getColumnNames() {
    return columnElements.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public List<String[]> getRows() {
    return columnElements.stream()
        .map(tr -> tr.findElements(By.className("td")))
        .map(tds -> tds.stream().map(WebElement::getText).toArray(String[]::new))
        .collect(Collectors.toList());
  }
}
