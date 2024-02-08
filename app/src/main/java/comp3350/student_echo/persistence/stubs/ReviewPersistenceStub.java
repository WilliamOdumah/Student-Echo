package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.ReviewPersistence;


public class ReviewPersistenceStub implements ReviewPersistence {

    private final List<CourseReview> courseReviews;
    private final List<InstructorReview> instructorReviews;
    StudentAccount fakeUser = new StudentAccount("fake","fakepassword","fake@myumanitoba.ca");

//    private static comp3350.student_echo.persistence.stubs.ReviewPersistenceStub instance = null;

    public ReviewPersistenceStub() {
        courseReviews = new ArrayList<>();
        instructorReviews = new ArrayList<>();
        populateInitialData();
    }

    // Lazy initialization of the singleton instance
//    public static comp3350.student_echo.persistence.stubs.ReviewPersistenceStub getInstance() {
//        if (instance == null) {
//            instance = new comp3350.student_echo.persistence.stubs.ReviewPersistenceStub();
//        }
//        return instance;
//    }

    private void populateInitialData() {
        // Create example courses and instructors

        Course course1 = new Course("Computer Science", "COMP1010" , "Introductory Computer Science");
        Course course2 = new Course("CS", "COMP3010", "Distributed Computing");

        Instructor instructor1 = new Instructor("Dr.", "Gary", "Chalmers");
        Instructor instructor2 = new Instructor("Professor", "Mary", "Bailey");

        // Add some initial course reviews
        courseReviews.add(new CourseReview(course1, "Great introductory course!", 5, 2,fakeUser));
        courseReviews.add(new CourseReview(course1, "Tough but rewarding.", 4, 3,fakeUser));
        courseReviews.add(new CourseReview(course2, "BLOCKCHAINS!", 5, 5,fakeUser));
        courseReviews.add(new CourseReview(course2, "Blinky blink blink", 3, 5,fakeUser));


        // Add some initial instructor reviews
        instructorReviews.add(new InstructorReview(instructor1, "Very knowledgeable and helpful.", 5, 3,fakeUser));
        instructorReviews.add(new InstructorReview(instructor1, "Challenging exams, but fair.", 4, 4,fakeUser));
        instructorReviews.add(new InstructorReview(instructor2, "She is a great prof", 5, 3,fakeUser));
        instructorReviews.add(new InstructorReview(instructor2, "She gave me a 0 on my the assignment", 1, 5,fakeUser));
    }


    // Add a course review
    public void addCourseReview(CourseReview review) {
        courseReviews.add(review);
    }

    // Get all course reviews
    public List<CourseReview> getAllCourseReviews() {
        return new ArrayList<>(courseReviews); // Return a copy to prevent modification
    }

    // Add an instructor review
    public void addInstructorReview(InstructorReview review) {

        instructorReviews.add(review);
    }

    // Get all instructor reviews
    public List<InstructorReview> getAllInstructorReviews() {
        return new ArrayList<>(instructorReviews); // Return a copy to prevent modification
    }

    public void deleteCourseReview(String reviewId) {
        // Iterate through the list to find the review with the given ID
        for (Iterator<CourseReview> iterator = courseReviews.iterator(); iterator.hasNext(); ) {
            CourseReview review = iterator.next();
            if (review.getId().equals(reviewId)) {
                iterator.remove(); // Remove the review from the list
                break;
            }
        }
    }

    public void deleteInstructorReview(String reviewId) {
        // Iterate through the list to find the review with the given ID
        for (Iterator<InstructorReview> iterator = instructorReviews.iterator(); iterator.hasNext(); ) {
            InstructorReview review = iterator.next();
            if (review.getId().equals(reviewId)) {
                iterator.remove(); // Remove the review from the list
                break;
            }
        }
    }



    public void editReview(CourseReview updatedReview) {
        for (int i = 0; i < courseReviews.size(); i++) {
            CourseReview review = courseReviews.get(i);
            if (review.getId().equals(updatedReview.getId())) {
                courseReviews.set(i, updatedReview); // Replace the old review with the updated one
                break;
            }
        }
    }

    // Method to retrieve a CourseReview by its ID
    public CourseReview getCourseReviewById(String reviewId) {
        System.out.println("ReviewID WE ARE TRYING TO FIND IS = "+ reviewId);
        for (CourseReview review : courseReviews) {
            System.out.println("Review COMMENT IS = "+review.getComment()+" AND ID IS = "+review.getId());
            if (review.getId().equals(reviewId)) {
                return review; // Found the review with the given ID
            }
        }
        return null; // No review found with the given ID
    }

    // Method to retrieve an InstructorReview by its ID
    public InstructorReview getInstructorReviewById(String reviewId) {
        for (InstructorReview review : instructorReviews) {
            if (review.getId().equals(reviewId)) {
                return review; // Found the review with the given ID
            }
        }
        return null; // No review found with the given ID
    }

    // Method to update a CourseReview in the database
    public boolean updateCourseReviewInDatabase(CourseReview updatedReview) {
        int index = findCourseReviewIndexById(updatedReview.getId());
        if (index != -1) {
            courseReviews.set(index, updatedReview); // Replace the old review with the updated one
            return true; // Update successful
        }
        return false; // Update failed, review not found
    }

    // Helper method to find the index of a CourseReview by its ID
    private int findCourseReviewIndexById(String reviewId) {
        for (int i = 0; i < courseReviews.size(); i++) {
            if (courseReviews.get(i).getId().equals(reviewId)) {
                return i; // Found the review at this index
            }
        }
        return -1; // Review not found
    }

    // Method to update an InstructorReview in the database
    public boolean updateInstructorReviewInDatabase(InstructorReview updatedReview) {
        int index = findInstructorReviewIndexById(updatedReview.getId());
        if (index != -1) {
            instructorReviews.set(index, updatedReview); // Replace the old review with the updated one
            return true; // Update successful
        }
        return false; // Update failed, review not found
    }

    // Helper method to find the index of an InstructorReview by its ID
    private int findInstructorReviewIndexById(String reviewId) {
        for (int i = 0; i < instructorReviews.size(); i++) {
            if (instructorReviews.get(i).getId().equals(reviewId)) {
                return i; // Found the review at this index
            }
        }
        return -1; // Review not found
    }

    public List<CourseReview> getReviewsFor(Course c) {
        List<CourseReview> matchingReviews = new ArrayList<>();
        for (CourseReview review : courseReviews) {
            if (review.getCourse().equals(c)) {
                matchingReviews.add(review);
            }
        }
        return matchingReviews; // Returns a list of CourseReviews for the given course
    }

    public List<InstructorReview> getReviewsFor(Instructor inst) {
        List<InstructorReview> matchingReviews = new ArrayList<>();
        for (InstructorReview review : instructorReviews) {
            if (review.getInstructor().equals(inst)) {
                matchingReviews.add(review);
            }
        }
        return matchingReviews; // Returns a list of InstructorReviews for the given instructor
    }




}
