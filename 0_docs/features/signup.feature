@must
Feature: Signup
  As an un-authorized user
  I want to register an account as a learner or a teacher
  So that I have access to the right Platform
  
  Scenario: Register an account with all empty fields
    Given an un-authorised user views the sign up page
    And they leave all fields empty
    When they submit the register form
    Then an error message explains that the fields are empty
    And the user is on the sign up page
  
  Scenario: Register a learner account with valid credentials
    Given an un-authorised user views the sign up page
    And they choose learner option
    And they enter a valid full name
    And the email and confirm email fields match
    And the password and confirm password fields match
    When they submit the register form
    Then the account should be created
    And the user should be show registration successful message
    And a verfication email should be sent to the user
    
  Scenario: Register a teacher account with valid credentials
    Given an un-authorised user views the sign up page
    And they choose teacher option
    And they enter a valid full name
    And the email and confirm email fields match
    And the password and confirm password fields match
    When they submit the register form
    Then the account should be created
    And the user should be shown registration successful message
    And a verfication email should be sent to the user

  Scenario: Register an account with unmatched emails
    Given an un-authorised user views the sign up page
    And they enter a valid full name
    And the email and confirm email do not match
    And the password and confirm password fields match
    When they submit the register form
    Then an error message explains that the email fields do not match
    And the user is on the sign up page

  Scenario: Register an account with unmatched password
    Given an un-authorised user views the sign up page
    And they enter a valid full name
    And the email and confirm email fields match
    And the password and confirm password fields do not match
    When they submit the register form
    Then an error message explains that the password fields do not match
    And the user is on the sign up page

  Scenario: Register an account with missing password fields
    Given an un-authorised user views the sign up page
    And they enter a valid full name
    And the email and confirm email fields match
    And one or more of the password fields are empty
    When they submit the register form
    Then an error message explains that the password fields are empty
    And the user is on the sign up page
    
  Scenario: Register an account with missing email fields
    Given an un-authorised user views the sign up page
    And they enter a valid full name
    And one or more of the email fields are empty
    And the password and confirm password fields match
    When they submit the register form
    Then an error message explains that the email fields are empty
    And the user is on the sign up page
    
  Scenario: Register an account with name field empty
    Given an un-authorised user views the sign up page
    And the name field is empty
    And the email and confirm email fields match
    And the password and confirm password fields match
    When they submit the register form
    Then an error message explains that name field is empty
    And the user is on the sign up page

  Scenario: Register an account with an already registered email
    Given an un-authorised user views the sign up page
    And they enter an email already in the database
    And all values in other fields are valid
    When they submit the register form
    Then an error message explains that the email address is already registered
    And the user is on the sign up page
