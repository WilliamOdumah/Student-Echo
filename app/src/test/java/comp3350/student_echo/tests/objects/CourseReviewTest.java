package comp3350.student_echo.tests.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;

public class CourseReviewTest {

    @Test
    public void
    List<CourseReview> reviewList= reviews.getReviewFor(new Course("Computer Science", "COMP1010" , "Introductory Computer Science"));

    assertEquals(2, reviewList.size());

    Review review=reviewList.get(0);



    review=reviews.getCourseReviewById(review.getId());

    assertNotNull(review);

}



    @Test
    public void testInstructorReview(){

        Instructor inst1= new Instructor("Dr.", "Gary", "Chalmers");
        List<InstructorReview> reviewList= reviews.getReviewFor(inst1);

        assertEquals(2, reviewList.size());

        Review review=reviewList.get(0);
        review=reviews.getInstructorReviewById(review.getId());
        assertNotNull(review);
    }

    @Test
    public void testAdd(){
        reviews=new AccessReviews();
        Instructor inst= new Instructor("Abc", "2", "3");
        InstructorReview newReview=new InstructorReview(inst,"xyz",1,2,null);
        reviews.addInstructorReview(newReview);
        List<InstructorReview> reviewList1= reviews.getReviewFor(inst);
        assertEquals(newReview.getComment(), reviewList1.get(0).getComment());

        Course c= new Course("a","b","c");
        CourseReview newReview1=new CourseReview(c,"xyz",1,2,null);
        reviews.addCourseReview(newReview1);
        List<InstructorReview> reviewList2= reviews.getReviewFor(inst);
        assertEquals(newReview.getComment(), reviewList2.get(0).getComment());
    }

}