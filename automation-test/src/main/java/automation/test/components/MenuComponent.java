package automation.test.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import automation.core.BaseComponent;
import automation.core.annotation.ComponentObject;

import java.util.List;
import java.util.stream.Collectors;

@ComponentObject
public class MenuComponent extends BaseComponent {
  @FindBy(css = "ul > li > a")
  private List<WebElement> listItems;

  public List<String> getMenuItemNames() {
    return listItems.stream()
        .map(WebElement::getText)
        .filter(text -> text.trim().length() > 0)
        .collect(Collectors.toList());
  }
}
