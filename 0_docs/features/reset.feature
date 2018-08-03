@must
Feature: Reset Password System
  As a user
  I want to reset my password
  So that I can change the password when I forget it.
  
  
  Scenario: Attempt to reset with a valid password
    Given a user views the reset page 
    And they enter a valid password
    And they enter a matched re-enter password
    When they submit the reset form
    Then the password should be reset successfully
    And the user is on the login form
    And an email has sent to the user to tell them that they have changed the password 
  
  Scenario: Attempt to reset with an invalid password
    Given a user views the reset page
    And they enter an invalid password
    When they submit the reset form
    Then an error message explains that the password is not valid
    And the user is on the reset form
    And the values they entered still remain
  
  Scenario: Attempt to reset with an empty password field
    Given a user views the reset page
    And they leave the password field empty
    When they submit the reset form 
    Then an error message explains that the password field is empty
    And the user is on the reset form
 
  Scenario: Attempt to reset with an empty re-enter password field
    Given a user views the reset page
    And they enter a valid password
    And they leave the re-enter password field empty
    When they submit the reset form
    Then an error message explains that the re-enter password field is empty
    And the user is on the reset form
    And the values they entered still remain
    
  Scenario: Attempt to reset with an unmatched password
    Given a user views the reset page
    And they enter a valid password
    And the re-enter password field does not match
    When they submit the reset form 
    Then an error message explains that the password fields don't match
    And the user is on the reset form
    And the values they entered still remain
    
