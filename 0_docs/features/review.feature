@must
Feature:
  As a learner enrolled in a course
  I want to leave a review of the course
  So that other learners and teachers can see my review
  
  
  Scenario: Select a star rating for the course  structure field
    Given a learner is enrolled on a course
    And they view the review page
    When they select a star rating for the course structure field
    And they submit the review form
    Then they will have their review stored in the database
    
  Scenario: Select a star rating for the course difficulty field
    Given a learner is enrolled on a course
    And they view the review page
    When they select a star rating for the course difficulty field
    And they submit the review form
    Then they will have their review stored in the database
    
  Scenario: Select a star rating for the course support field
    Given a learner is enrolled on a course
    And they view the review page
    When they select a star rating for the course support field
    And they submit the review form
    Then they will have their review stored in the database
    
  Scenario: Select a star rating for the course overall field
    Given a learner is enrolled on a course
    And they view the review page
    When they select a star rating for the course overall field
    And they submit the review form
    Then they will have their review stored in the database
    
  Scenario: Enter a comment in the course field
    Given a learner is enrolled on a course
    And they view the review page
    When they enter a comment in the comment field
    And they submit the review form
    Then they will have their review stored in the database
    
   Scenario: All input fields are empty
    Given a learner is enrolled on a course
    And they view the review page
    And they leave all field blank
    When they submit the review form
    Then an error page is displayed telling them they have not review the page
    
   Scenario: Learner already reviewed course
    Given a learner is enrolled on a course
    And they have already reviewed the course
    And they are on the course page
    When they when they click on the review page link
    Then an error page will be displayed telling them they have already reviewed the course
    
    
    