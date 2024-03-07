package comp3350.student_echo.tests.business.integration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.student_echo.business.access.AccessReviews;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.Instructor;
import comp3350.student_echo.persistence.ReviewPersistence;
import comp3350.student_echo.persistence.hsqldb.ReviewPersistenceHSQLDB;
import comp3350.student_echo.tests.utils.TestUtils;

public class AccessReviewsIT {
    private AccessReviews accessReviews;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final ReviewPersistence hsqldb = new ReviewPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));

        // inject real HSQLDB
        accessReviews = new AccessReviews(hsqldb);
        System.out.println("SETUP");
    }
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
        System.out.println("TEAR DOWN");
    }

    @Test
    public void testGetReviewForCourse() {
        Course testCourse = new Course("CS", "COMP1010", "Introduction to Computer Science 1");
        List<Review> reviews = accessReviews.getReviewsFor(testCourse);

        assertNotNull("Reviews list should not be null", reviews);
        assertTrue("There should be reviews for the course", reviews.size() > 0);
    }

    @Test
    public void testGetReviewForInstructor() {
        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
        List<Review> reviews = accessReviews.getReviewsFor(testInstructor);


        assertNotNull("Reviews list should not be null", reviews);
        assertTrue("There should be reviews for the instructor", reviews.size() > 0);
    }
//
//    @Test
//    public void testAddReviewForCourse(){
//        Course testCourse = new Course("CS", "COMP1010", "Introduction to Computer Science 1");
//        StudentAccount fakeUser = new StudentAccount("fake","fake","fake@myumanitoba.ca");
//        Review r = new Review(testCourse, "nice course",5,5,fakeUser,0,0);
//        accessReviews.addReview(r);
//        List<Review> reviews = accessReviews.getReviewsFor(testCourse);
//
//        assert(r.equals(reviews.get(reviews.size()-1)));
//        accessReviews.deleteReview(r);
//    }
//
//    @Test
//    public void testAddReviewForInstructor(){
//        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
//        StudentAccount fakeUser = new StudentAccount("fake","fake","fake@myumanitoba.ca");
//        Review r = new Review(testInstructor, "good job",5,5,fakeUser,0,0);
//        accessReviews.addReview(r);
//        List<Review> reviews = accessReviews.getReviewsFor(testInstructor);
//
//        assert(r.equals(reviews.get(reviews.size()-1)));
//        accessReviews.deleteReview(r);
//    }
//
//    @Test
//    public void testUpdateCourseReview() {
//        Course testCourse = new Course("CS", "COMP1010", "Introduction to Computer Science 1");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review originalReview = new Review(testCourse, "Original comment", 3, 3, fakeUser, 0, 0);
//        accessReviews.addReview(originalReview);
//
//        // Retrieve to ensure it's there
//        List<Review> reviewsBeforeUpdate = accessReviews.getReviewsFor(testCourse);
//        Review retrievedReview = reviewsBeforeUpdate.get(reviewsBeforeUpdate.size() - 1);
//        assertNotNull(retrievedReview);
//
//        // Update the review
//        retrievedReview.setComment("Updated comment");
//        retrievedReview.setOverallRating(4);
//        accessReviews.updateReviewInDatabase(retrievedReview);
//
//        // Verify update
//        List<Review> reviewsAfterUpdate = accessReviews.getReviewsFor(testCourse);
//        Review updatedReview = reviewsAfterUpdate.get(reviewsAfterUpdate.size() - 1);
//        assertEquals("Updated comment", updatedReview.getComment());
//        assertEquals(4, updatedReview.getOverallRating());
//
//        // Cleanup
//        accessReviews.deleteReview(updatedReview);
//    }
//
//    @Test
//    public void testUpdateInstructorReview() {
//        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review originalReview = new Review(testInstructor, "Original comment", 3, 3, fakeUser, 0, 0);
//        accessReviews.addReview(originalReview);
//
//        // Retrieve to ensure it's there
//        List<Review> reviewsBeforeUpdate = accessReviews.getReviewsFor(testInstructor);
//        Review retrievedReview = reviewsBeforeUpdate.get(reviewsBeforeUpdate.size() - 1);
//        assertNotNull(retrievedReview);
//
//        // Update the review
//        retrievedReview.setComment("Updated comment");
//        retrievedReview.setOverallRating(4);
//        accessReviews.updateReviewInDatabase(retrievedReview);
//
//        // Verify update
//        List<Review> reviewsAfterUpdate = accessReviews.getReviewsFor(testInstructor);
//        Review updatedReview = reviewsAfterUpdate.get(reviewsAfterUpdate.size() - 1);
//        assertEquals("Updated comment", updatedReview.getComment());
//        assertEquals(4, updatedReview.getOverallRating());
//
//        // Cleanup
//        accessReviews.deleteReview(updatedReview);
//    }
//
//    @Test
//    public void testDeleteCourseReview() {
//        // Add a review, then delete it and ensure it's gone
//        Course testCourse = new Course("CS", "COMP1010", "Introduction to Computer Science 1");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testCourse, "Temporary comment", 3, 3, fakeUser, 0, 0);
//        accessReviews.addReview(review);
//
//        // Delete the review
//        accessReviews.deleteReview(review);
//
//        // Verify deletion
//        List<Review> reviewsAfterDeletion = accessReviews.getReviewsFor(testCourse);
//        assertTrue(reviewsAfterDeletion.stream().noneMatch(r -> r.equals(review)));
//    }
//
//    @Test
//    public void testDeleteInstructorReview() {
//        // Add a review, then delete it and ensure it's gone
//        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testInstructor, "Temporary comment", 3, 3, fakeUser, 0, 0);
//        accessReviews.addReview(review);
//
//        // Delete the review
//        accessReviews.deleteReview(review);
//
//        // Verify deletion
//        List<Review> reviewsAfterDeletion = accessReviews.getReviewsFor(testInstructor);
//        assertTrue(reviewsAfterDeletion.stream().noneMatch(r -> r.equals(review)));
//    }
//
//
//    @Test
//    public void testAddOrUpdateCourseInteraction() {
//        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testCourse, "Temporary comment", 3, 3, fakeUser, 0, 0);
//
//        // Test adding a new interaction
//        boolean resultAdd = accessReviews.addOrUpdateInteraction(review, fakeUser, 1);
//        assertTrue("Interaction should be added.", resultAdd);
//
//        // Test updating the existing interaction
//        boolean resultUpdate = accessReviews.addOrUpdateInteraction(review, fakeUser, -1);
//        assertTrue("Interaction should be updated.", resultUpdate);
//    }
//
//    @Test
//    public void testAddOrUpdateInstructorInteraction() {
//        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testInstructor, "Temporary comment", 3, 3, fakeUser, 0, 0);
//
//        // Test adding a new interaction
//        boolean resultAdd = accessReviews.addOrUpdateInteraction(review, fakeUser, 1);
//        assertTrue("Interaction should be added.", resultAdd);
//
//        // Test updating the existing interaction
//        boolean resultUpdate = accessReviews.addOrUpdateInteraction(review, fakeUser, -1);
//        assertTrue("Interaction should be updated.", resultUpdate);
//    }
//
//    @Test
//    public void testGetCourseInteractionState() {
//        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testCourse, "Temporary comment", 3, 3, fakeUser, 0, 0);
//
//        // Assume we already added an interaction with state 1 (like)
//        accessReviews.addOrUpdateInteraction(review, fakeUser, 1);
//
//        int state = accessReviews.getInteractionState(review, fakeUser);
//        assertEquals("The interaction state should be 1 (like).", 1, state);
//    }
//
//    @Test
//    public void testGetInstructorInteractionState() {
//        Instructor testInstructor = new Instructor(1,"Dr.", "Gary", "Chalmers");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testInstructor, "Temporary comment", 3, 3, fakeUser, 0, 0);
//
//        // Assume we already added an interaction with state 1 (like)
//        accessReviews.addOrUpdateInteraction(review, fakeUser, 1);
//
//        int state = accessReviews.getInteractionState(review, fakeUser);
//        assertEquals("The interaction state should be 1 (like).", 1, state);
//    }
//
//    @Test
//    public void testUpdateLikeDislikeCounts() {
//        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testCourse, "Temporary comment", 3, 3, fakeUser, 0, 0);
//        int likesBefore = review.getLikes();
//        int dislikesBefore = review.getDislikes();
//        // Simulate some likes and dislikes
//        review.setLikes(review.getLikes() + 1);
//        review.setDislikes(review.getDislikes() + 1);
//
//        // Update counts in database
//        accessReviews.updateLikeCount(review);
//        accessReviews.updateDislikeCount(review);
//
//        assertNotNull("Updated review should not be null.", review);
//        assertEquals("Like count should be updated.", likesBefore+1, review.getLikes());
//        assertEquals("Dislike count should be updated.", dislikesBefore+1, review.getDislikes());
//    }
//
//    @Test
//    public void testInteractionConstraints() {
//        // Setup test review and user
//        Course testCourse = new Course("CS", "COMP3010", "Distributed Computing");
//        StudentAccount fakeUser = new StudentAccount("fake", "fake", "fake@myumanitoba.ca");
//        Review review = new Review(testCourse, "Interaction Test", 3, 3, fakeUser, 0, 0);
//
//        // User likes the review
//        accessReviews.addOrUpdateInteraction(review, fakeUser, 1);
//
//        // User attempts to also dislike the review
//        accessReviews.addOrUpdateInteraction(review, fakeUser, -1);
//
//        // Reload review interactions from the database
//        Integer interactionState = accessReviews.getInteractionState(review, fakeUser);
//
//        assertEquals("User interaction should enforce constraints.", Integer.valueOf(-1), interactionState);
//
//        // Cleanup
//        accessReviews.deleteReview(review);
//    }
}
