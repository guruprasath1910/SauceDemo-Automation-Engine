Feature: Login functionality

  Scenario: Valid user login
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to the inventory page

  Scenario: Invalid login with wrong password
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "wrong_password"
    Then I should see an error message "Username and password do not match"