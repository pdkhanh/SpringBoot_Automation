Feature: Filter feature
  Scenario: Basic filter
    Given I am at home page
    Then I click on Insurance tab
    Then I click on Travel tab
    And I click on Show my results button
    Then I can see more than 3 cards

  Scenario Outline: Insurer filter
    Given I am at home page
    Then I click on Insurance tab
    Then I click on Travel tab
    And I click on Show my results button
    Then I select <InsurerFilter> insurer
    Then I can see the list of <InsurerFilter> insurer
    Examples:
      | InsurerFilter                                                  |
      | Pacific Cross                                                  |
      | Prudential Guarantee                                           |
      | Pacific Cross, Prudential Guarantee                            |

  Scenario Outline: Detail filter
    Given I am at home page
    Then I click on Insurance tab
    Then I click on Travel tab
    And I click on Show my results button
    Then I can select <PolicyType> detail filter
    And I can select <WhoGoing> detail filter
    And I can select <Destination> destination
    And I can select <FromDate> in TRAVEL START DATE
    And I can select <ToDate> in TRAVEL END DATE
    Then I can see the filter data have <FilterData>
    Examples:
      | PolicyType | WhoGoing | Destination | FromDate        | ToDate          | FilterData                                                          |
      | single trip| 2 persons| Vietnam     |21-February-2020 | 19-April-2020   |single trip, 2 persons, Vietnam, 21-February-2020, 19-April-2020     |