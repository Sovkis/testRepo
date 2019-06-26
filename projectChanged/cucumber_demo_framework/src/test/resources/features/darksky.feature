@web @regression @darksky
Feature: Signup feature

  @scenario-1
  Scenario: Verify invalid signup error message
    Given I am on the darksky Register page
    When I click on Register button
    Then I verify error message "please fill out this field"

  @scenario-2
  Scenario: Verify Current Temperature should not be greater or less than the Temperature from Daily Timeline
    Given I am on Darksky Home Page
    Then I verify current temperature is not greater or less then temps from daily timeline


  @scenario-3
  Scenario: Verify individual day temp timeline
    Given I am on Darksky Home Page
    When I expand todays timeline
    Then I verify lowest and highest temp is displayed correctly

  @scenario-4
  Scenario: Verify timline is displayed in correct format
    Given I am on Darksky Home Page
    Then I verify timeline is displayed with two hours incremented