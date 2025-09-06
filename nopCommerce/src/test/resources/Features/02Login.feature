Feature: Login user
@priority2
Scenario: Login with valid credentials
  Given User opens NopCommerce homepage for login
  When User logs in with "john@test.com" and "Password123"
  Then Login completed and close browser
