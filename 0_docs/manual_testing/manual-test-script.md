# Group 15 Manual Testing

## Tests

### Signup Screen

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 1.1 | Go to /signup   | The sign up form should appear |
| 1.2 | Choose between learner and teacher options | Only one should be selected at a time |
| 1.3 | Enter word below 3 characters in Display name | An error saying `Enter your full name` should appear below the field |
| 1.4 | Leave the Display name field empty | An error saying `Field is required` should appear below the field |
| 1.5 | Enter `Goku` in Display name field | The value should be accepted |
| 1.6 | Enter `abc` in Email field | An error saying `Enter a valid email address` should appear below the field | 
| 1.7 | Leave the Email field blank | An error saying `Field is required` should appear below the field |
| 1.8 | Enter `getDigital321@gmail.com` in the Email field | The value should be accepted |
| 1.9 | Enter value in Re-enter Email field which is different then the Email field | An error saying `Emails do not match` should appear below the field |
| 1.10 | Leave the Re-enter Email field blank | An error saying `Field is required` should appear below the field |
| 1.11 | Enter the `getDigital321@gmail.com` in the Re-enter Email field | The value should be accepted if its the same as the Email field |
| 1.12 | Enter `abc` in Password field | An error saying `Passwords should be at least 8 characters long and contain both letters & numbers` should appear below the field |
| 1.13 | Enter `123` in Password field | An error saying `Passwords should be at least 8 characters long and contain both letters & numbers` should appear below the field |
| 1.14 | Leave the Password field blank | An error saying `Field is required` should appear below the field |
| 1.15 | Enter `abc1234567` in Password field | The value should be accepted |
| 1.16 | Enter value in Re-enter Password field which is different then the Password field | An error saying `Passwords do not match` should appear below the field |
| 1.17 | Leave the Re-enter Password field blank | An error saying `Field is required` should appear below the field |
| 1.18 | Enter `abc1234567` in the Re-enter Password field | The value should be accepted if its the same as the Password field |
| 1.19 | Enter all correct values and click Sign up | A `Thanks for regestering` page should appear with instructions on what to do in order to login. The verification email should also be sent to the user |
| 1.20 | Click on the link provided in the email to verify the email | should be taken to the login page with message saying `Account verification successful!` |

### Login Screen

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 2.1 | Enter `verified@gmail.com` with password `'rightpassword'`. Click `Login` Enter `authenticator code` | You are taken to the /learner/ page|
| 2.2 | Enter `verified@gmail.com` with password `'rightpassword'`. Click `Login` Enter `wrong authenticator code` |  An error message "Incorrect authenticator code. Please try again." should appear at the below the `Logout` URL |
| 2.3 | Enter `verified@gmail.com` with password `'wrongpassword'`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.4 | Enter `verified@gmail.com` with password `' '`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.5 | Enter `unverified@gmail.com` with password `'rightpassword'`. Click `Login` | An error message "You have not verified your email address for this account. Click here to resend verification instructions to your email." should appear at the below the `Forgotten your password?` URL|
| 2.6 | Enter `unverified@gmail.com` with password `'wrongpassword'`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.7 | Enter `unverified@gmail.com` with password `' '`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.8 | Enter ` invalid ` with password `'password'`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.9 | Enter ` invalid ` with password `' '`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.10 | Enter `  ` with password `'password'`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.11 | Enter `  ` with password `' '`. Click `Login` | An error message "Invalid email address or password. Please try again." should appear at the below the `Forgotten your password?` URL|
| 2.12 | Enter `teacher1@gmail.com` with password `'password1'`. Click `Login` Enter `authenticator code` | You are taken to the /teacher/ page|


### Forgotten Password Screen
| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 3.1 | Enter `verified@gmail.com`. Click `Send instructions` | A link has sent to your email to reset password|
| 3.2 | Enter `unverified@gmail.com`. Click `Send instructions` | A link has sent to your email to reset password|
| 3.3 | Enter `invalid`. Click `Send instructions` |  An error message "There is no user registered with that email. Please try again." should appear at the below the `Return to login` URL|


### Reset Password Screen
| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 4.1 | Enter password `password` with re-enter password `password` . Click `Reset your password` | You are taken to the /login page and a message "Your password has been changed successfully!" should appear at the below the "Forgotten your password?" URL and you will receive a email "Your password was changed."|
| 4.2 | Enter password `invalid` | An error message "Passwords should be at least 8 characters long and contain both letters & numbers" should appear at the below the input field|
| 4.3 | Enter password ` ` | An error message "Field is required"should appear at the below the input field|
| 4.4 | Enter re-enter password ` ` | An error message "Field is required"should appear at the below the input field|
| 4.5 | Enter password `password` with re-enter password `pass` | An error message "Passwords do not match"should appear at the below the input field|


### Two Factor Authentication

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 5.1  |Scan QR on webpage using Google Authenticator application to enable application to generate codes. | Account is set up on two factor authentication application on smartphone and time based codes begin to generate.|
| 5.2   |Enter manual code `7VT6CCQ2SRJBHVCJ` to enable two factor authentication on Google Authenticator application on smartphone.| Account is set up on two factor authentication application on smartphone and time based codes begin to generate.|
| 5.3   | Enter the correct time based code `03115` generated by the app on smartphone.| After clicking `Verify Code`, `/learner/` page should load.|
| 5.4 | Enter a code different to the one generated by app `123456`.| `Please try again` error message should appear below input field. |


### Course Creation

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 6.1  | Enter a valid a valid course name `Testing` in `Course Name` field |     Name will be accepted and green tick will appear next to the field              |
| 6.2  | Enter string of numbers and special characters `12345£$`   in `Course Name` field   |Error message, “Enter an appropriate course name” will appear below field.|
| 6.3  | Leave `Course Name` field blank  |   “Field is required” error should be shown below the input field.  |
| 6.4  | Enter course name that is already registered in the system      |   Message, “This course name is already registered” will appear below the field.          |
| 6.5  | Enter a valid number `5` in  `Max number of learners` field | Value will be accepted and green tick will appear next to the field.      |
| 6.6  | Leave the `Max number of learners` field blank |    “Field is required” error should be shown below the input field.   |
| 6.7  | Enter string `test` in  `Max number of learners` field |   “Enter a whole number between 1 to 300 (no preceding 0)” error should be shown below input field.    |
| 6.8  | Choose a valid date `06/05/2017` from the calendar widget for the `Start Date` field.   |    Date is accepted. |
| 6.9  | Choose a past date `06/04/2017` from the calendar widget for the `Start Date` field.   |   “Please enter a future date” error should be displayed below input field.|
| 6.10 | Choose a present date `30/04/2017` from the calendar widget for the `Start Date` field.   |   “Please enter a future date” error should be displayed below input field. |
| 6.11 | Choose `Advanced` course difficulty level from a drop down list. From the `Difficulty Level` field.   |  Value is accepted and field is green.    |
| 6.12 | Do not select and option from   `Difficulty Level` field |   “Please select a level” error message will appear below field and field will turn red.        |
| 6.13 | Choose `Arts` from `Course Category` field drop down list.   | Value is accepted and field is green.   |
| 6.14 | Do not choose anything from  `Course Category` field drop down list.   |“Please select a category” error message will appear below field and field will turn red.     |
| 6.15 | Enter description `This is a test!!!` into the `Course Description` field.   |  Value is accepted.   |
| 6.16 | Leave`Course Description` field blank.   |  “Field is required” error should be shown below the input field.  |
| 6.17 | Enter description `<10char` into the `Course Description` field.   |  “Please enter description between the character limit of 10 to 2000”, error message will show below field.   |
| 6.18 | Leave all fields blank and click on `Submit` button       |  Button should be disabled     |
| 6.19 | Enter a valid a valid course name `Testing` in `Course Name` field,  Enter a valid number `5` in  `Max number of learners` field, Choose a valid date `06/05/2017` from the calendar widget for the `Start Date` field,  Choose `Advanced` course difficulty level from a drop down list. From the `Difficulty Level` field,Choose `Arts` from `Course Category` field drop down list, Enter description `This is a test!!!` into the `Course Description` field and click on `Submit` button        |  `\teacher\course\[id]` should load with data matching all the inputs     |


### Review Courses 

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 7.1 | Learner clicks on the 'review this course' button| You are taken to the `/learner/course/[id]/review` page with a form|
| 7.2 | Move mouse cursors to highlight the number of stars for rating, | The form should allow the learner to highlight number of starts to determine the level of rating for each of the four categories in review.jsp |
| 7.3 | Click on the minus button to clear ratings for the selected category out of the four| The learner should be able to clear ratings by clicking on the minus button.|
| 7.4 | Click on the textbox to add comments| The learner should be able to add comments regarding the course.|
| 7.5 | Click on the 'course home page' to display the course summary with the ratings| The learner's comments and overall review at the buttom of the course page.|



### Content Upload

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 8.1 | Teacher chooses a `pdf` file to upload and clicks the 'Upload' button | You are redirected to the same page and a message says the upload was successful |
| 8.2 | Teacher chooses an empty `docx` file to upload and clicks the 'Upload' button | You are redirected to the same page and an error message says to choose a file first |
| 8.3 | Teacher chooses a `mp3` file to upload and clicks the 'Upload' button | You are redirected to the same page and an error message says that the content type is not allowed |
| 8.4 | Teacher clicks the 'Upload' button without choosing anything to upload | You are redirected to the same page and an error message says that you need to choose a file first |



### Admin Tests

| Test | Step | Expected Outcome |
|------|:-----|:-----------------|
| 9.1 | Enter `Goku` in search bar and press `Enter` | Goku's user preview card should be displayed|
| 9.2 | Enter `goku@gmail.com` in search bar and press `Enter` | Goku's user preview card should be displayed |
| 9.3 | Enter `tester` in search bar| Page should display none|
| 9.4 |Click on user's profile link after searching `Goku`|A user's full profile should be displayed|
| 9.5 | Click on the `disbale` button on users's profile| Pop up window should appear asking user to confirm decision.|
| 9.6 |  Click on the `disbale` button on pop up window confirming decision |The user's disabled status will change to `False `     |
|  9.7|  Click on the `enable` button on user's profile    |  Pop up window should appear asking user to confirm decision      |
|  9.8|  Click on the `enable` button on pop up window confirming decision |The user's disabled status will change to `True` |
| 9.9 | When viewing a teacher profile clicking on `user details` button | Should display full teacher profile |
| 9.10| When viewing a teacher profile clicking on `taught course` button  |   A teachers course details should be displayed  |
|9.11 | When viewing a teacher profile clicking on `enrolled course` button   | The course details the learner is enrolled on should be displayed  |

# WS3 Tests below this line
---

### Test Results: Signup Screen

| Test | Result |
|------|:-------|
| 1.1 | Taken to /signup where the registration form appeared |
| 1.2 | Only able to choose Learner or Teacher at a time |
| 1.3 | The error saying `Enter your full name` appeared below the field |
| 1.4 | The error saying `Field is required` appeared below the field | 
| 1.5 | The value was accepted |
| 1.6 | The error saying `Enter a valid email address` appeared below the field |
| 1.7 | The error saying `Field is required` appeared below the field |
| 1.8 | The value was accepted |
| 1.9 | The error saying `Emails do not match` appeared below the field |
| 1.10 | The error saying `Field is required` appeared below the field |
| 1.11 | The value was accepted |
| 1.12 | The error saying `Passwords should be at least 8 characters long and contain both letters & numbers` appeared below the field |
| 1.13 | The error saying `Passwords should be at least 8 characters long and contain both letters & numbers` appeared below the field |
| 1.14 | The error saying `Field is required` appeared below the field |
| 1.15 | The value was accepted |
| 1.16 | The error saying `Passwords do not match` appeared below the field |
| 1.17 | The error saying `Field is required` appeared below the field |
| 1.18 | The value was accepted |
| 1.19 | A `Thanks for regestering` page appeared with instructions on requiring to verify email and using an authenticator to log in. The email was also recieved |
| 1.20 | Taken to the login page and a message at the bottom appeared saying `Account verification successful!` |

### Test Results: Login Screen

| Test | Result |
|------|:-------|
| 2.1 | Taken to the /learner/ page |
| 2.2 | Error message as expected appeared below the `logout` URL |
| 2.3 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.4 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.5 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.6 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.7 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.8 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.9 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.10 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.11 | Error message as expected appeared below the `Forgotten your password?` URL |
| 2.12 | Taken to the /teacher/ page |


### Test Results: Forgotten Password Screen

| Test | Result |
|------|:-------|
| 3.1 | An link has sent to user's email |
| 3.2 | An link has sent to user's email |
| 3.3 | Error message as expected appeared below the `Return to login` URL |

### Test Results: Reset Password Screen

| Test | Result |
|------|:-------|
| 4.1 | Taken to the /login/ page |
| 4.2 | Error message as expected appeared below the the input field |
| 4.3 | Error message as expected appeared below the the input field |
| 4.4 | Error message as expected appeared below the the input field |
| 4.5 | Error message as expected appeared below the the input field |

### Test Results: Two Factor Authentication

| Test | Result |
|------|:-----------------|
| 5.1  | Account was set up on two factor authentication application on smartphone and time based codes were being generated |
| 5.2  | Account was set up on two factor authentication application on smartphone and time based codes were being generated |
| 5.3  | The `/learner/` page should loaded |
| 5.4  | `Please try again` error appeared below field |

### Test Results: Course Creation

| Test | Result |
|------|:-------|
| 6.1  | Name was accepted and green tick appeared next to the field |
| 6.2  | `Enter an appropriate course name` appeared below field |
| 6.3  | `Field is required` appeared below field |
| 6.4  | `This course name is already registered` appeared below the field |
| 6.5  | Value was accepted and green tick will appeared next to the field |
| 6.6  | `Field is required` appeared below the input field |
| 6.7  | `Enter a whole number between 1 to 300 (no preceding 0)` error was shown below input field |
| 6.8  | Date was accepted |
| 6.9  | `Please enter a future date` appeared below input field |
| 6.10 | `Please enter a future date` appeared below input field |
| 6.11 | Value was accepted and field was green |
| 6.12 | `Please select a level` appeared below the field and field turned red |
| 6.13 | Value was accepted and field turned green |
| 6.14 | `Please select a category` appeared below the field and field turned red |
| 6.15 | Value was accepted |
| 6.16 | `Field is required` appeared below input field |
| 6.17 | `Please enter description between the character limit of 10 to 2000` appeared below input field |
| 6.18 | Button was disabled |
| 6.19 | `\teacher\course\[id]` loaded with data matching all the inputs |


### Test Results: Review Courses 

| Test | Result |
|------|:-------|
| 7.1 | Taken to the `/learner/course/[id]/review` page with a form|
| 7.2 | The form allowed to highlight number of starts to determine the level of rating for each of the four categories|
| 7.3 | Cleared ratings by clicking on the minus button |
| 7.4 | Value was accepted in the comments field |
| 7.5 | The Comments and overall review appeared at the buttom of the course page.|


### Test Results: Content Upload
| Test | Result |
|------|:-------|
| 8.1 | Redirected to the same `/teacher/` with a successful upload message |
| 8.2 | Redirected to the same page with an error message explaining to choose a file first |
| 8.3 | Redirected to the same page with an error message explaining that the file type is not allowed |
| 8.4 | Redirected to the same page with an error message explaining that a file needs to be chosen first |



# Test Results

## Tests Run 28/04/2017
### Tests Executed: 5.1, 5.2, 5.3, 5.4 
### Tests Failed: None

## Tests Run 30/04/2017
### Tests Executed: 6.1, 6.2, 6.3, 6.4, 6.5, 6.6, 6.7, 6.8, 6.9, 6.10, 6.11, 6.12, 6.13, 6.14, 6.15, 6.17, 6.18, 6.19, 3.1, 3.2, 3.3, 4.1, 4.2, 4.3, 4.4, 4.5, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9, 2.10, 2.11, 2.12
### Tests Failed: None

## Tests Run 01/05/2017
### Tests Executed: 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 1.10, 1.11, 1.12, 1.13, 1.14, 1.15, 1.17, 1.18, 1.19, 1.20, 7.1, 7.2, 7.3, 7.4, 7.5, 9.1, 9.2, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9, 9.10, 9.11
### Tests Failed: None

