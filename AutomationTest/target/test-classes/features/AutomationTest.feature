@test
Feature: Automation Test

  Scenario: Add highest price item  to cart
    Given I open the test app
    And I login to test app
    Then I assert the page title to be "Products"
    When I add costly dress to cart
    And I click on cart icon on the products page
    Then I assert the page title to be "Your Cart"
    And I validate the price in the cart page
