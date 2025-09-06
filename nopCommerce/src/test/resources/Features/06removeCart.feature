Feature: Remove product from cart
@priority6
  Scenario: Remove product from cart
    Given User opens NopCommerce homepage for remove
    And User already has "Asus Laptop" in the cart
    When User removes product from cart
    Then Removal operation completed and close browser
