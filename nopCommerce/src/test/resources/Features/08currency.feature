Feature: Change display currency
@priority8
  Scenario: Change currency to Euro
    Given User opens NopCommerce homepage for currency
    When User changes currency to "Euro"
    Then Currency operation completed and close browser
