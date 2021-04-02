Feature: Basic Validation
  Background:
    Given the User is on the meetup homepage

  Scenario: Title Verification
    Then verify the title contains "Meetup"

    Scenario: URL Verification
      Then verify the URL contains "https://www.meetup.com/"