package comp3350.student_echo.tests.business;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import comp3350.student_echo.business.access.AccessReviews;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.persistence.ReviewPersistence;
import comp3350.student_echo.persistence.stubs.ReviewPersistenceStub;


public class AccessReviewsTest {
    private AccessReviews accessReviews;
    private Course c1;
    private Instructor i1;
    private Review courseRev;
    private Review instRev;
    private StudentAccount sa;

    @Before
    public void setup() {
        ReviewPersistence stub = new ReviewPersistenceStub();
        accessReviews = new AccessReviews(stub);

        // set variables
        c1 = new Course("CS", "COMP1010", "Intro Comp");
        i1 = new Instructor(1, "Dr.", "Gary","Chalmers");
        sa = new StudentAccount("fake","fake","fake@myumanitoba.ca");
        courseRev = new Review(1, c1, "comment", 2,2,sa,0,0);
        instRev = new Review(1, i1, "comment", 2,2,sa, 0,0);
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

    @Test
    public void testGetReviewForCourse() {
        List<Review> reviews = accessReviews.getReviewsFor(c1);

        assertNotNull("Reviews list should not be null", reviews);
        assertTrue("There should be reviews for the course", reviews.size() > 0);
    }

    @Test
    public void testGetReviewForInstructor() {
        List<Review> reviews = accessReviews.getReviewsFor(i1);

        assertNotNull("Reviews list should not be null", reviews);
        assertTrue("There should be reviews for the instructor", reviews.size() > 0);
    }

    @Test
    public void testAddReviewForCourse(){
        accessReviews.addReview(courseRev);
        List<Review> reviews = accessReviews.getReviewsFor(c1);
        Review last = reviews.get(reviews.size()-1);
        assertTrue("Expect review to be present", courseRev.equals(last));
    }

    @Test
    public void testAddReviewForInstructor(){
        accessReviews.addReview(instRev);
        List<Review> reviews = accessReviews.getReviewsFor(i1);
        Review last = reviews.get(reviews.size()-1);
        assertTrue("Expect review to be present", instRev.equals(last));
    }

    @Test
    public void testUpdateCourseReview() {
        Course testCourse = new Course("CS", "COMP1010", "Introduction to Computer Science 1");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review originalReview = new Review(testCourse, "Original comment", 3, 3, fakeUser, 0, 0);
        accessReviews.addReview(originalReview);

        // Retrieve to ensure it's there
        List<Review> reviewsBeforeUpdate = accessReviews.getReviewsFor(testCourse);
        Review retrievedReview = reviewsBeforeUpdate.get(reviewsBeforeUpdate.size() - 1);
        assertNotNull(retrievedReview);

        // Update the review
        retrievedReview.setComment("Updated comment");
        retrievedReview.setOverallRating(4);
        accessReviews.updateReviewInDatabase(retrievedReview);

        // Verify update
        List<Review> reviewsAfterUpdate = accessReviews.getReviewsFor(testCourse);
        Review updatedReview = reviewsAfterUpdate.get(reviewsAfterUpdate.size() - 1);
        assertEquals("Updated comment", updatedReview.getComment());
        assertEquals(4, updatedReview.getOverallRating());

        // Cleanup
        accessReviews.deleteReview(updatedReview);
    }

    @Test
    public void testUpdateInstructorReview() {
        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review originalReview = new Review(testInstructor, "Original comment", 3, 3, fakeUser, 0, 0);
        accessReviews.addReview(originalReview);

        // Retrieve to ensure it's there
        List<Review> reviewsBeforeUpdate = accessReviews.getReviewsFor(testInstructor);
        Review retrievedReview = reviewsBeforeUpdate.get(reviewsBeforeUpdate.size() - 1);
        assertNotNull(retrievedReview);

        // Update the review
        retrievedReview.setComment("Updated comment");
        retrievedReview.setOverallRating(4);
        accessReviews.updateReviewInDatabase(retrievedReview);

        // Verify update
        List<Review> reviewsAfterUpdate = accessReviews.getReviewsFor(testInstructor);
        Review updatedReview = reviewsAfterUpdate.get(reviewsAfterUpdate.size() - 1);
        assertEquals("Updated comment", updatedReview.getComment());
        assertEquals(4, updatedReview.getOverallRating());

        // Cleanup
        accessReviews.deleteReview(updatedReview);
    }

    @Test
    public void testDeleteCourseReview() {
        // Add a review, then delete it and ensure it's gone
        accessReviews.addReview(courseRev);

        // Delete the review
        accessReviews.deleteReview(courseRev);

        // Verify deletion
        List<Review> reviewsAfterDeletion = accessReviews.getReviewsFor(c1);
        assertTrue(reviewsAfterDeletion.stream().noneMatch(r -> r.equals(courseRev)));
    }

    @Test
    public void testDeleteInstructorReview() {
        // Add a review, then delete it and ensure it's gone
        accessReviews.addReview(instRev);

        // Delete the review
        accessReviews.deleteReview(instRev);

        // Verify deletion
        List<Review> reviewsAfterDeletion = accessReviews.getReviewsFor(i1);
        assertTrue(reviewsAfterDeletion.stream().noneMatch(r -> r.equals(instRev)));
    }


    @Test
    public void testAddOrUpdateCourseInteraction() {
        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review review = new Review(testCourse, "Temporary comment", 3, 3, fakeUser, 0, 0);

        // Test adding a new interaction
        boolean resultAdd = accessReviews.addOrUpdateInteraction(review, fakeUser, 1);
        assertTrue("Interaction should be added.", resultAdd);

        // Test updating the existing interaction
        boolean resultUpdate = accessReviews.addOrUpdateInteraction(review, fakeUser, -1);
        assertTrue("Interaction should be updated.", resultUpdate);
    }

    @Test
    public void testAddOrUpdateInstructorInteraction() {
        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review review = new Review(testInstructor, "Temporary comment", 3, 3, fakeUser, 0, 0);

        // Test adding a new interaction
        boolean resultAdd = accessReviews.addOrUpdateInteraction(review, fakeUser, 1);
        assertTrue("Interaction should be added.", resultAdd);

        // Test updating the existing interaction
        boolean resultUpdate = accessReviews.addOrUpdateInteraction(review, fakeUser, -1);
        assertTrue("Interaction should be updated.", resultUpdate);
    }

    @Test
    public void testGetCourseInteractionState() {
        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review review = new Review(testCourse, "Temporary comment", 3, 3, fakeUser, 0, 0);

        // Assume we already added an interaction with state 1 (like)
        accessReviews.addOrUpdateInteraction(review, fakeUser, 1);

        int state = accessReviews.getInteractionState(review, fakeUser);
        assertEquals("The interaction state should be 1 (like).", 1, state);
    }

    @Test
    public void testGetInstructorInteractionState() {
        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review review = new Review(testInstructor, "Temporary comment", 3, 3, fakeUser, 0, 0);

        // Assume we already added an interaction with state 1 (like)
        accessReviews.addOrUpdateInteraction(review, fakeUser, 1);

        int state = accessReviews.getInteractionState(review, fakeUser);
        assertEquals("The interaction state should be 1 (like).", 1, state);
    }

    @Test
    public void testUpdateLikeDislikeCounts() {
        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review review = new Review(testCourse, "Temporary comment", 3, 3, fakeUser, 0, 0);
        int likesBefore = review.getLikes();
        int dislikesBefore = review.getDislikes();
        // Simulate some likes and dislikes
        review.setLikes(review.getLikes() + 1);
        review.setDislikes(review.getDislikes() + 1);

        // Update counts in database
        accessReviews.updateLikeCount(review);
        accessReviews.updateDislikeCount(review);

        assertNotNull("Updated review should not be null.", review);
        assertEquals("Like count should be updated.", likesBefore+1, review.getLikes());
        assertEquals("Dislike count should be updated.", dislikesBefore+1, review.getDislikes());
    }

    @Test
    public void testInteractionConstraints() {
        // Setup test review and user
        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
        Review review = new Review(testCourse, "Interaction Test", 3, 3, fakeUser, 0, 0);

        // User likes the review
        accessReviews.addOrUpdateInteraction(review, fakeUser, 1);

        // User attempts to also dislike the review
        accessReviews.addOrUpdateInteraction(review, fakeUser, -1);

        // Reload review interactions from the database
        Integer interactionState = accessReviews.getInteractionState(review, fakeUser);

        assertEquals("User interaction should enforce constraints.", Integer.valueOf(-1), interactionState);

        // Cleanup
        accessReviews.deleteReview(review);
    }
}