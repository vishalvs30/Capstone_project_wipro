Feature: Add product to cart
  @priority5
  Scenario: Add product to cart
    Given User opens NopCommerce homepage for cart
    When User searches "Asus Laptop" and adds to cart
    Then Product is added to cart and browser is closed

