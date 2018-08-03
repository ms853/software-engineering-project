@must
Feature: 
  As a Teacher 
  I want to create a new course
  So that I can start adding content to a course I am teaching

            
  Scenario: Set a valid name for a course
    Given a teacher views the course creation page
    And they enter a valid name for a course
    And all other input fields are valid
    When they submit the form 
    Then the course's name is the one they entered
    And the values they entered remain 
  
  Scenario: Set an invalid name for a Course
    Given a teacher views the course creation page
    And they enter an invalid name for a course
    And all other input fields are valid
    When they submit the form
    Then an error message explains the criteria for a valid course name
    And the values they entered remain

  Scenario: Over maximum characters in description
    Given a teacher views the course creation page
    And they enter more than the maximum amount of characters for a description
    And all other input fields are valid
    Then an error message explains the maximum amount of characters allowed for a description
    And the values they entered remain 

  Scenario: All input fields are valid
    Given a teacher views the course creation page
    And all input fields have valid values
    When they submit the form
    Then they are redirected to their course control panel
    And the values they entered are shown

  Scenario: No Category is selected
    Given a teacher views the course creation page
    And all input values are valid
    But no category is selected
    When they submit the form
    Then an error message explains that no category is selected
    And the teacher is on the course creation page
    And all values they entered remain

  Scenario: Course name already taken
    Given a teacher views the course creation page
    And they enter the name of a course that is already taken
    And all other fields are valid
    When they submit the form
    Then an error message explains that the name is already taken
    And the teacher is on the course creation page
    And all values they entered remain

  Scenario: Description is empty
    Given a teacher views the course creation page
    And they leave the description field empty
    And all other fields are valid
    When they submit the form
    Then an error message explains that the description field is empty
    And the teacher is on the course creation page
    And all values they entered remain

