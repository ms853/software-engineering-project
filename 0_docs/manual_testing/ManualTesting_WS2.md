#Group 12 Manual Testing

##Tests

###Registration Screen Learner

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 1.1 | Go to /signup/learner   | The sign up form should appear |
| 1.2 | Enter `test1` as the username with password `'pass123'`. Click `Sign Up` | You will be automatically redirected to the /user-login page, where the login form should appear |

###Registration Screen Teacher

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 1.1 | Go to /signup/teacher   | The sign up form should appear |
| 1.2 | Enter `teacher1` as the username with password `'password1'`. Click `Sign Up` | You will be automatically redirected to the /user-login page, where the login form should appear |


###Login Screen

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 2.1 | Enter `test1` with password `'pass123'`. Click `Login` | You are taken to the /learner/ page|
| 2.2 | Enter `test2` with password `'pass123'`. Click `Login` | An error message "Invalid username or password." should appear at the below the `Login` button|
| 2.3 | Enter `test1` with password `'pass1234'`. Click `Login` | An error message "Invalid username or password." should appear at the below the `Login` button|
| 2.4 | Enter `teacher1` with password `'password1'`. Click `Login` | You are taken to the /teacher/ page|
| 2.5 | Enter `teacher2` with password `'password1'`. Click `Login` | An error message "Invalid username or password." should appear at the below the `Login` button|
| 2.6 | Enter `teacher1` with password `'password2'`. Click `Login` | An error message "Invalid username or password." should appear at the below the `Login` button|

###Sign Up Screen

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 3.1 | Click Sign up then Learner| You are taken to the /learner/signup page|
| 3.2 | Enter `test` in Display name, Enter `test`in username, Enter `example@example.com` in email, Enter `example@example.com` in re-enter email, Enter `password` in password, Enter `password` in re-enter password, Enter `le1` in Postcode, Enter `example road` in address line, Enter `1234567` in contact number, Click `Sign Up`  | The form should be allowed to enter without error and redirect to login.jsp |
| 3.3 | Enter `test` in Display name, Enter `test`in username, Enter `example@example.com` in email, Enter `examples101@example.com` in re-enter email, Enter `password` in password, Enter `password` in re-enter password, Enter `le1` in Postcode, Enter `example road` in address line, Enter `1234567` in contact number, Click `Sign Up` | An error message "Your entered emails are incorrect" should appear and the form rejected.|
| 3.4 | Enter `test` in Display name, Enter `test`in username, Enter `example@example.com` in email, Enter `examples101@example.com` in re-enter email, Enter `password` in password, Enter `password` in re-enter password, Enter `le1` in Postcode, Enter `example road` in address line, Enter `1234567` in contact number, Click `Sign Up` Create another user with the `test` in username and enter other valid inputs| An error message saying "Username already in use" should appear |
| 3.5 | Enter `test` in Display name, Enter `test`in username, Enter `hjfgfhgfhgf` in email, Enter `hjfgfhgfhgf` in re-enter email, Enter `password` in password, Enter `password` in re-enter password, Enter `le1` in Postcode, Enter `example road` in address line, Enter `1234567` in contact number, Click `Sign Up` | An error message "Invalid email." should appear and the form rejected|
| 3.6 | Enter `test` in Display name, Enter `test`in username, Enter `example@example.com` in email, Enter `example@example.com` in re-enter email, Enter `password` in password, Enter `password1` in re-enter password, Enter `le1` in Postcode, Enter `example road` in address line, Enter `1234567` in contact number, Click `Sign Up` | An error message "passwords do not match" should appear and the form rejected |


###Test Results: Registration Screen Learner

| Test | Result |
|------|:-------|
| 1.1 | sign up for learner appeared |
| 1.2 | Taken tp /user-login page after correctly filling the form |

###Test Results: Login Screen

| Test | Result |
|------|:-------|
| 2.1 | Taken to the /learner/ page |
| 2.2 | Error message as expected appeared below the `Login` button |
| 2.3 | Error message as expected appeared below the `Login` button |
| 2.4 | Taken to the /teacher/ page |
| 2.5 | Error message as expected appeared below the `Login` button |
| 2.6 | Error message as expected appeared below the `Login` button |

###Test Results: Review Courses 

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 4.1 | Learner clicks on the 'review this course' button| You are taken to the /learner/course/2/review page with a form|
| 4.2 | Move mouse cursors to highlight the number of stars for rating, | The form should allow the learner to highlight number of starts to determine the level of rating for each of the four categories in review .jsp |
| 4.3 | Click on the minus button to delete ratings for the selected category out of the four| The learner should be able to delete ratings by clicking on the minus button.|
| 4.4 | Click on the textbox to add comments| The learner should be able to add comments regarding the course.|
| 4.5 | Click on the 'course home page' to display the course summary with the ratings| The learner's comments and overall review at the buttom of the review page.|





###Test Run 6/04/2017
####Tests Executed: 1.1, 1.2, 2.1, 2.2, 2.3, 2.4 ###Tests Failed: 2.4 (Error message "Invalid username or password." appeared)
### Tests corrected: 2.4
####Tests Executed: 3.1, 3.2, 3.3, 3.5, 3.6 ### Tests Failed 3.4 (User was not stopped from duplicate account) 

###Test Run 01/05/2017
###Test Executed: 4.1, 4.2, 4.3, 4.4, 4.5