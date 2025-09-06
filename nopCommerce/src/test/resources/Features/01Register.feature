Feature: Register user
@priority1
Scenario: Register with valid details
  Given User opens NopCommerce homepage for register
  When User registers with "John" "Doe" "john@test.com" "Password123" "Password123"
  Then Registration completed and close browser
