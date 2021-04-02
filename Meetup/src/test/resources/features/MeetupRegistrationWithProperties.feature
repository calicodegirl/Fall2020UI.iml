Feature: MeetupRegistration

  Background:
    Given the user is on the meetup home page
    When user clicks on "Join Meetup"

  Scenario: Verify Facebook option is displayed
    Then user should be able to see "Facebook" option

  Scenario: Verify Google option is displayed
    Then user should be able to see "Google" option

  Scenario: Verify Apple option is displayed
    Then user should be able to see "Apple" option


  Scenario: Verify with email
    Then user should be able to see email