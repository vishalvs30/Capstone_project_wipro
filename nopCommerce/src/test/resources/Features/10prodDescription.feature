Feature: Verify product description on product details page

  @priority10
  Scenario: Verify description of a selected product
    Given User opens NopCommerce homepage
    When User searches the site for "Apple MacBook Pro"
    And User clicks on the product link "Apple MacBook Pro"
    Then Product description should contain "Retina display"
    And Close the browser
