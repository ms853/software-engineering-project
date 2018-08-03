@must
Feature: content_upload
  As a Teacher 
  I want to upload some content
  So that I can start adding content to a course I am teaching


  Scenario: upload things successfully
    Given a teacher views the teacher page
    And they choose files to upload
    When they submit the form
    Then a message says upload successfully
    And the teacher is on teacher page
    And the upload content field is empty
    And the file can be found below uploaded files

  Scenario: upload nothing
    Given a teacher views the teacher page
    And they did not choose any file to upload
    When they submit the form
    Then an error message reminds them to choose a file
    And the teacher is on teacher page
    And the upload content field is empty

  Scenario: upload invalid content type
    Given a teacher views the teacher page
    And they choose an invalid content type to upload
    When they submit the form
    Then a message explains that the content type is not allowed
    And the teacher is on the teacher page
    And the upload content field is empty

  Scenario: upload file that is larger then max allowed size
    Given a teacher views the teacher page
    And they choose a file that is larger then max allowed size
    When they submit the form
    Then an exception explains to the teacher that the file is too large
    And the teacher is on the exception page

  Scenario: upload an empty file
    Given a teacher views the teacher page
    And they choose a file that is empty
    When they submit the form
    Then an exception explains that the file is empty
    And the teacher is on the teacher page
    And the upload content field is empty
