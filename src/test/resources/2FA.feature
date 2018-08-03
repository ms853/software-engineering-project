@must
Feature:
  As an authorised learner
  I want to enter a randomly generated code
  So I can sign in to the online learning website
  
  
  Scenario: Attempt to enter a valid two factor authentication code
    Given learner views the login-modal page
    And they enter an authorised email address
    And they enter a valid password matching the email address
    When when they submit the login form
    And they are on the 2FA page
    And they enter a valid 2FA code
    Then they will successfully login
    
  Scenario: Attempt to enter an invalid two factor authentication code
    Given learner views the login-modal page
    And they enter an authorised email address
    And they enter a valid password matching the email address
    When when they submit the login form
    And they are on the 2FA page
    And they enter an invalid 2FA code
    Then an error message explains that they need to enter a valid code