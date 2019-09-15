Feature: Sort feature
  Scenario Outline: Sort filter
    Given I am at home page
    Then I click on Insurance tab
    Then I click on Travel tab
    And I click on Show my results button
    Then I select <Sortvalue> sort
    Then I can see the result list in <Sortvalue>
    Examples:
      | Sortvalue                         |
      | Price: Low to high                |
      | Coverage Score: High to low       |
      | Review score: High to low         |
