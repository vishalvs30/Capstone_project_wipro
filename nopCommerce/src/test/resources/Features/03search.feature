Feature: Search products on NopCommerce
  @priority3
  Scenario: Search for a product
    Given User opens NopCommerce homepage for search
    When User searches for "Laptop"
    Then Search completed and close browser
