@sprintmvps
Feature: Verifying Passing the Text to Input Field
  Background:
    Given user is navigated to phptravels home page
    When user enters "admin@phptravels.com" and "demoadmin"
    And user clicks on Submit button

    Scenario: Blog button is displayed and enabled
      Then user should see Blog button
      And user clicks on Blog button

  Scenario: Add button is displayed and enabled
    Then user should see Add Button
    And user clicks on Add Button

    Scenario: Post title is displayed
      Then user is navigated to "https://www.phptravels.net/admin/blog/add"
      And user input in Post Tile field