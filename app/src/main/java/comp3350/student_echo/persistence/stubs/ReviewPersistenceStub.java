package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
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
    private final StudentAccount fakeUser = new StudentAccount("fake","fakepassword","fake@myumanitoba.ca");

    public ReviewPersistenceStub() {
        courseReviews = new ArrayList<>();
        instructorReviews = new ArrayList<>();
        populateStub();
    }

    private void populateStub() {
        Course course1 = new Course("Computer Science", "COMP1010" , "Introductory Computer Science");
        Course course2 = new Course("CS", "COMP3010", "Distributed Computing");

        Instructor instructor1 = new Instructor("Dr.", "Gary", "Chalmers");
        Instructor instructor2 = new Instructor("Professor", "Mary", "Bailey");

        courseReviews.add(new CourseReview(course1, "Great introductory course!", 5, 2,fakeUser));
        courseReviews.add(new CourseReview(course1, "Tough but rewarding.", 4, 3,fakeUser));
        courseReviews.add(new CourseReview(course2, "BLOCKCHAINS!", 5, 5,fakeUser));
        courseReviews.add(new CourseReview(course2, "Blinky blink blink", 3, 5,fakeUser));

        instructorReviews.add(new InstructorReview(instructor1, "Very knowledgeable and helpful.", 5, 3,fakeUser));
        instructorReviews.add(new InstructorReview(instructor1, "Challenging exams, but fair.", 4, 4,fakeUser));
        instructorReviews.add(new InstructorReview(instructor2, "She is a great prof", 5, 3,fakeUser));
        instructorReviews.add(new InstructorReview(instructor2, "She gave me a 0 on my the assignment", 1, 5,fakeUser));
    }

    public void addCourseReview(CourseReview review) {
        courseReviews.add(review);
    }

    public List<CourseReview> getAllCourseReviews() {
        return Collections.unmodifiableList(courseReviews);
    }

    public void addInstructorReview(InstructorReview review) {
        instructorReviews.add(review);
    }

    public List<InstructorReview> getAllInstructorReviews() {
        return Collections.unmodifiableList(instructorReviews);
    }

    public void deleteCourseReview(String reviewId) {
        for(CourseReview review : courseReviews) {
            if(review.getId().equals(reviewId)) {
                courseReviews.remove(review);
                return;
            }
        }
    }

    public void deleteInstructorReview(String reviewId) {
        for(InstructorReview review : instructorReviews) {
            if(review.getId().equals(reviewId)) {
                instructorReviews.remove(review);
                return;
            }
        }
    }

    public CourseReview getCourseReviewById(String reviewId) {
        for (CourseReview review : courseReviews) {
            if (review.getId().equals(reviewId)) {
                return review;
            }
        }
        return null;
    }

    public InstructorReview getInstructorReviewById(String reviewId) {
        for (InstructorReview review : instructorReviews) {
            if (review.getId().equals(reviewId)) {
                return review;
            }
        }
        return null;
    }

    public boolean updateCourseReviewInDatabase(CourseReview updatedReview) {
        int index = findCourseReviewIndexById(updatedReview.getId());
        if (index != -1) {
            courseReviews.set(index, updatedReview);
            return true;
        }
        return false;
    }

    // helper method
    private int findCourseReviewIndexById(String reviewId) {
        for (int i = 0; i < courseReviews.size(); i++) {
            if (courseReviews.get(i).getId().equals(reviewId)) {
                return i;
            }
        }
        return -1;
    }

    public boolean updateInstructorReviewInDatabase(InstructorReview updatedReview) {
        int index = findInstructorReviewIndexById(updatedReview.getId());
        if (index != -1) {
            instructorReviews.set(index, updatedReview);
            return true;
        }
        return false;
    }

    // helper method
    private int findInstructorReviewIndexById(String reviewId) {
        for (int i = 0; i < instructorReviews.size(); i++) {
            if (instructorReviews.get(i).getId().equals(reviewId)) {
                return i;
            }
        }
        return -1;
    }

    public List<CourseReview> getReviewsFor(Course c) {
        List<CourseReview> matchingReviews = new ArrayList<>();
        for (CourseReview review : courseReviews) {
            if (review.getCourse().equals(c)) {
                matchingReviews.add(review);
            }
        }
        return matchingReviews;
    }

    public List<InstructorReview> getReviewsFor(Instructor inst) {
        List<InstructorReview> matchingReviews = new ArrayList<>();
        for (InstructorReview review : instructorReviews) {
            if (review.getInstructor().equals(inst)) {
                matchingReviews.add(review);
            }
        }
        return matchingReviews;
    }
}
