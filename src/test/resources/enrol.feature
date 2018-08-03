@must
Feature:
    As a authorised leaner
    I want to enrol on a course
    So that I can view course material
    
 
  Scenario: Attempt to enrol on one course
    Given a learner is on a course page
    And they are not enrolled on that course
    And they are not enrolled on any other courses 
    When they click on the enrol button
    Then they are enrolled on the course
    And the course page they have enrolled on is displayed
    
   Scenario: Attempt to enrol on two courses
    Given a learner is on a course page
    And they are enrolled on one course
    And they are not enrolled on a course page they have not enrolled on 
    When they click on the enrol button
    Then they an error page is displayed telling them they cannot enrol on the course
  