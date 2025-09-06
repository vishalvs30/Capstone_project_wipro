Feature: Add product to wishlist
@priority7
  Scenario: Add product to wishlist
    Given User opens NopCommerce homepage for wishlist
    When User adds "Laptop" to wishlist
    Then Wishlist operation completed and close browser
