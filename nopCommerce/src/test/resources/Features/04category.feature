Feature: Shop by category
@priority4
  Scenario: Open category
    Given User opens NopCommerce homepage for category
    When User shops by category
    Then Category operation completed and close browser
