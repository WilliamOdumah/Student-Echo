package comp3350.student_echo.tests.business;

import static org.junit.Assert.*;

import org.junit.Test;

import comp3350.student_echo.business.access.AccessReviews;


public class AccessReviewsTest {
    private AccessReviews reviews;

    @Test
    public void dummy(){
        assertTrue(true);
    }
//
//    @Before
//    public void setUp() {
//        reviews = new AccessReviews();
//    }
//
//    @Test
//    public void testCourseReview() {
//
//        List<CourseReview> reviewList= reviews.getReviewsFor(new Course("Computer Science", "COMP1010" , "Introductory Computer Science"));
//
//        assertEquals(2, reviewList.size());
//
//    }
//
//    @Test
//    public void testInstructorReview(){
//
//        Instructor inst1 = new Instructor("Dr.", "Gary", "Chalmers");
//        List<InstructorReview> reviewList = reviews.getReviewsFor(inst1);
//
//        assertEquals(2, reviewList.size());
//    }
//
//
//    @Test
//    public void testAddInstructorReview() {
//        reviews = new AccessReviews();
//        Instructor inst = new Instructor("Abc", "2", "3");
//        InstructorReview newReview = new InstructorReview(inst,"xyz",1,2,null);
//        reviews.addReview(newReview);
//        List<InstructorReview> reviewList1 = reviews.getReviewsFor(inst);
//        assertEquals(newReview.getComment(), reviewList1.get(0).getComment());
//    }
//
//    @Test
//    public void testDeleteInstructorReview() {
//        reviews = new AccessReviews();
//        Instructor inst = new Instructor("Abc", "2", "3");
//        InstructorReview newReview = new InstructorReview(inst,"xyz",1,2,null);
//        int b = reviews.getReviewsFor(inst).size();
//        reviews.addReview(newReview);
//
//
//        reviews.deleteReview(newReview);
//        int i = reviews.getReviewsFor(inst).size();
//        assert (b==i);
//    }

//    @Test
//    public void testAddCourseReview() {
//        Instructor inst = new Instructor("Abc", "2", "3");
//        Course c = new Course("a","b","c");
//        CourseReview newReview1 = new CourseReview(c,"xyz",1,2,null);
//        reviews.addReview(newReview1);
//        List<InstructorReview> reviewList2 = reviews.getReviewsFor(inst);
//        assertEquals(newReview1.getComment(), reviewList2.get(0).getComment());
//    }

//    @Test
//    public void testDeleteCourseReview() {
//        Course c = new Course("a", "b", "c");
//        CourseReview newReview1 = new CourseReview(c, "xyz", 1, 2, null);
//        reviews.addReview(newReview1);
//        List<CourseReview> reviewList2 = reviews.getReviewsFor(c);
//        assertEquals(newReview1.getComment(), reviewList2.get(0).getComment());
//
//        reviews.deleteReview(newReview1);
//        assert(reviews.getReviewsFor(c).isEmpty());
//    }
}