package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.persistence.ReviewPersistence;


public class ReviewPersistenceStub implements ReviewPersistence {
    private final List<Review> reviews;

    public ReviewPersistenceStub() {
        reviews = new ArrayList<>();
        populateStub();
    }

    private void populateStub() {
        StudentAccount fakeUser = new StudentAccount("fake","fakepassword","fake@myumanitoba.ca");
        Course course1 = new Course("Computer Science", "COMP1010" , "Introductory Computer Science");
        Course course2 = new Course("CS", "COMP3010", "Distributed Computing");

        Instructor instructor1 = new Instructor("Dr.", "Gary", "Chalmers");
        Instructor instructor2 = new Instructor("Professor", "Mary", "Bailey");

        reviews.add(new CourseReview(course1, "Great introductory course!", 5, 2,fakeUser));
        reviews.add(new CourseReview(course1, "Tough but rewarding.", 4, 3,fakeUser));
        reviews.add(new CourseReview(course2, "BLOCKCHAINS!", 5, 5,fakeUser));
        reviews.add(new CourseReview(course2, "Blinky blink blink", 3, 5,fakeUser));

        reviews.add(new InstructorReview(instructor1, "Very knowledgeable and helpful.", 5, 3,fakeUser));
        reviews.add(new InstructorReview(instructor1, "Challenging exams, but fair.", 4, 4,fakeUser));
        reviews.add(new InstructorReview(instructor2, "She is a great prof", 5, 3,fakeUser));
        reviews.add(new InstructorReview(instructor2, "She gave me a 0 on my the assignment", 1, 5,fakeUser));
    }

    public void addReview(Review r) {
        reviews.add(r);
    }

    public void deleteReview(Review r) {
        reviews.remove(r);
    }

    public List<CourseReview> getReviewsFor(Course c) {
        List<CourseReview> result = new ArrayList<>();
        for (Review review : reviews) {
            if(review instanceof CourseReview) {
                CourseReview courseReview = (CourseReview) review;
                if (courseReview.getCourse().equals(c)) {
                    result.add(courseReview);
                }
            }
        }
        return result;
    }

    public List<InstructorReview> getReviewsFor(Instructor inst) {
        List<InstructorReview> result = new ArrayList<>();
        for (Review review : reviews) {
            if(review instanceof InstructorReview) {
                InstructorReview ir = (InstructorReview) review;
                if(ir.getInstructor().equals(inst)) {
                    result.add(ir);
                }
            }
        }
        return result;
    }

    public boolean updateReview(Review r){
        int index = findReviewIndexById(r.getUid());
        if (index != -1) {
            reviews.set(index,r);
            return true;
        }
        return false;
    }

    private int findReviewIndexById(int reviewId) {
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getUid() == reviewId) {
                return i;
            }
        }
        return -1;
    }
}
