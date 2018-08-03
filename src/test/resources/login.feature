@must
Feature: Login System
  As a User
  I want to login to the system with valid credentials (as either admin, lecturer, or learner)
  So that I can view the respective relevant information to my role

  
  Scenario: Attempt to login with an empty email field
    Given an un-authorised user views the login page
    And they leave the email field empty
    And they enter their password
    When they submit the login form
    Then an error message explains that one or more fields are empty
    And the user is on the login page
    And the email and password field are empty

  Scenario: Attempt to login with an empty password field
    Given an un-authorised user views the login page
    And they enter their email
    And they leave the password field empty
    When they submit the login form
    Then an error message explains that one or more fields are empty
    And the user is on the login page
    And the values they entered remain in the fields

  Scenario: Attempt to login with empty email and password field
    Given an un-authorised user views the login page
    And they leave the email field empty
    And they leave the password field empty
    When they submit the login form
    Then an error message explains that one or more fields are empty
    And the user is on the login page

  Scenario: Attempt to login with valid email and invalid password
    Given an un-authorised user views the login page
    And they enter their valid email
    And they enter an invalid password
    When they submit the login form
    Then an error message explains that the password they entered was incorrect
    And the user is on the login page
    And the value they entered for the email field remains
    And the password field is empty

  Scenario: Attempt to login with invalid email 
    Given an un-authorised user views the login page
    And they enter an invalid email
    And they enter their password
    When they submit the login form
    Then an error message explains that there is no record of that email in the system
    And the user is on the login page
    And the email and password fields are empty

