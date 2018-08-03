@must
  Feature: 
    As an admin
    I want to be able to search user accounts
    So that I can enable and disable accounts
    
    
  Scenario: Search for a registered user by display name
    Given an admin user views the admin user-search page
    And searches for a registered user display name
    When they submit the search form
    Then the preview of the user's profile should be displayed
    
   Scenario: Search for a registered user by email
    Given an admin user views the admin user-search page
    And searches for a registered user email address
    When they submit the search form
    Then the preview of the user's profile should be displayed
    
  Scenario: Search for a user that does not exist
    Given an admin user views the admin user-search page
    And searches for a user name that does not exist
    When they submit the search form
    Then the page will display a message saying "none"
    
  Scenario: View a user's profile
    Given an admin user views the admin user-search page
    And searches for a registered user display name
    When they submit the search form
    Then the preview of the user's profile should be displayed
    When they click on the details link
    Then a user's full profile should be displayed
    
   Scenario: Disable a user's account
    Given an admin views a user's profile page
    And the user's account is not disabled
    When they click on the disable account button
    Then a pop window will appear
    And when they select the disable button
    Then the user's disabled status will change to True
    
   Scenario: Re-enable a user's account
    Given an admin views a user's profile page
    And the user's account is disabled
    When they click on the enable account button
    Then a pop window will appear
    And when they select the enable button
    Then the user's disabled status will change to False

   Scenario: View a teacher's user details from course page
    Given an admin views a course's details
    And a teacher is assigned to that course
    When they click on the user details button
    Then a user's full profile should be displayed

   Scenario: View a learner's user details from course page
    Given an admin views a course's details
    And learners are enrolled on that course
    When they click on the user details button
    Then a user's full profile should be displayed

   Scenario: View a learner's enrolled course
    Given an admin views a user's profile page
    And the user's role is learner
    When they click on the enrolled course button
    Then the course's details should be displayed

   Scenario: View a teacher's taught course
    Given an admin views a user's profile page
    And the user's role is teacher
    When they click on the taught course button
    Then the course's details should be displayed
