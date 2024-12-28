@Login
Feature: Login

  As an existing customer of Ajio,
  I want to log in into the application.

  Scenario Outline: Log in with valid Phone number
    Given I navigate to the Ajio home page
    And I click on Sign In button
    When On Sign In modal I enter "<Phone_number>" and CONTINUE
    And On OTP modal I enter valid OTP and click START SHOPPING
    Then I should be logged in successfully with my "<User_name>" displayed
    Examples:
      |Phone_number| User_name|
      |9820654890| ankit      |

