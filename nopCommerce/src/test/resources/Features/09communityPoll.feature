Feature: Community Poll voting
@priority9
  Scenario: Registered user votes in community poll
    Given User opens NopCommerce homepage for poll
    When User logs in with email "john@test.com" and password "password123"
    And User votes "Good" in community poll
    Then Poll operation completed and close browser

