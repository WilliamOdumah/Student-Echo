# Student Echo's Architecture

An overview of our classes and its locations.

### Iteration 1 Diagram

![Architecture](docs/"Iteration 1"/Architecture1.png)

#### Presentation

LoginActivity
* First page of application 

NOTE: We are currently forcing the users to log in before proceeding. In the next iteration, we will allow users to continue as a guest.

SignUpActivity
* Page where new users can create an account 

HomeActivity
* Page directing user to either searching a course or an instructor

CourseActivity
* Page where users can search through a list of courses

InstructorActivity
* Page where users can search through a list of instructors

ViewCourseActivity
* Resulting page after the user clicks a particular course from CourseActivity
* Contains reviews about the specified course

ViewInstructorActivity
* Resulting page after the user clicks on a particular instructor from InstructorActivity
* Contains reviews about the specified instructor

#### Logic

AuthenticateLogin
* Speaks to the AccessAccounts class to check if user's log-in input is valid 

AccessAccounts
* Handles get/set/add/delete requests directed to the AccountsPersistenceStub 

AccessInstructors
* Handles get/set/add/delete requests directed to the InstructorPersistenceStub

AccessCourses
* Handles get/set/add/delete requests directed to the CoursePersistenceStub

AccessReviews
* Handles get/set/add/delete requests directed to the ReviewPersistenceStub

AverageCalculator
* Determines and returns the average rating of a specified object 
* Used by ViewCourseActivity and ViewInstructorActivity

#### Data

AccountPersistenceStub
* Stub used to store Account objects

InstructorPersistenceStub
* Stub used to store Instructor objects

CoursePersistenceStub
* Stub used to store Course objects

ReviewPersistenceStub
* Stub used to store Review objects

### Iteration 2 Diagram

### Iteration 3 Diagram