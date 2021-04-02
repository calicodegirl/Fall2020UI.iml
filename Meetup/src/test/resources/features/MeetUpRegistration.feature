@register
Feature: MeetupRegistration

  Background:
    Given the User is on the meetup homepage
    When User clicks on Join Meetup button

  Scenario: Verify Continue with Facebook option is displayed
    Then User should be able to see "Continue with Facebook" option

  Scenario: Verify Continue with Google option is displayed
    Then User should be able to see "Continue with Google" option

  Scenario: Verify Continue with Apple option is displayed
    Then User should be able to see "Continue with Apple" option


  Scenario: Verify Sign Up with email is displayed
    Then user should be able to see sign up with email