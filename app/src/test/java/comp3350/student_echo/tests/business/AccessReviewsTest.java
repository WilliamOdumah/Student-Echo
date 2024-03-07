package comp3350.student_echo.tests.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.student_echo.business.access.AccessReviews;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.persistence.ReviewPersistence;
import comp3350.student_echo.persistence.stubs.ReviewPersistenceStub;


public class AccessReviewsTest {
    private AccessReviews accessReviews;

    @Before
    public void setup() {
        ReviewPersistence stub = new ReviewPersistenceStub();
        accessReviews = new AccessReviews(stub);
    }
    @Test
    public void testGetReviewsFor() {
        List<Review> reviewList= accessReviews.getReviewsFor(new Course("Computer Science", "COMP1010" , "Introductory Computer Science"));
        assertEquals("get reviews for given course",2, reviewList.size());

        reviewList = accessReviews.getReviewsFor(new Instructor("Dr.", "Gary", "Chalmers"));
        assertEquals("get reviews for given instructor", 2, reviewList.size());
    }

    @Test
    public void testAddReview() {
        // add review with instructor
        Instructor inst = new Instructor("Abc", "2", "3");
        Review r1 = new Review(inst,"xyz",1,2,null,0,0);
        accessReviews.addReview(r1);
        List<Review> l1 = accessReviews.getReviewsFor(inst);
        assertEquals(r1.getComment(), l1.get(0).getComment());

        // add review with course
        Course course = new Course("dep","cour1234","name");
        Review r2 = new Review(course, "yooo", 5, 3, null,0,0);
        accessReviews.addReview(r2);
        List<Review> l2 = accessReviews.getReviewsFor(course);
        assertEquals(r2.getComment(), l2.get(0).getComment());
    }

    @Test
    public void testDeleteReview() {
        // test with Instructor
        Instructor inst = new Instructor("Abc", "2", "3");
        Review newReview = new Review(inst,"xyz",1,2,null,0,0);
        int sizeBefore = accessReviews.getReviewsFor(inst).size();

        accessReviews.addReview(newReview);
        accessReviews.deleteReview(newReview);

        int sizeAfter = accessReviews.getReviewsFor(inst).size();
        assertEquals("expect no change after insertion and deletion", sizeBefore, sizeAfter);

        // test with Course
        Course course = new Course("dept", "comp1111", "courseName");
        newReview = new Review(course, "yooo", 4,4,null,0,0);
        sizeBefore = accessReviews.getReviewsFor(course).size();

        accessReviews.addReview(newReview);
        accessReviews.deleteReview(newReview);

        sizeAfter = accessReviews.getReviewsFor(course).size();
        assertEquals("expect no change after insertion and deletion", sizeBefore, sizeAfter);
    }
}