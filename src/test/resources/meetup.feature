@must
Feature: Meetup
  As an Teacher
  I want to be able to arrange meetups
  So that I can tell my students the location of the meetup
  
  Scenario: Arranging a meetup
  Given a teacher is on the meetup page
  And they enter a valid location
  When they submit that location
  Then the learners on that course are notified
  And can view the location on the map
  
  Scenario: Entering an invalid location
  Given a teacher is on the meetup page
  And they enter an invalid location
  When they submit that location
  Then the teacher is notified of the error
  And the meetup is not arranged
  
  Scenario: Trying to view meetup when there isn't one arranged
  Given a learner is on the meetup page
  And no meetup has been arranged
  Then they see a map without a marker in it
  And the given address is blank
  
  Scenario: Viewing the meetup location
  Given a learner is on the meetup page
  And a meetup has been arranged
  Then they can see on the map where it is
  And be given the address
  


