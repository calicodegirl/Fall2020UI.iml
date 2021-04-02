@loginflow
Feature: Login Flow
  Background:
    Given the User is on the meetup homepage

  Scenario: Sign Up Button Validation
    Then User should be able to see "Sign up" button

  Scenario: Log In Button Validation
    Then User should be able to see "Log in" button

  Scenario: Join Meetup Button Validation
    Then User should be able to see "Join Meetup" button