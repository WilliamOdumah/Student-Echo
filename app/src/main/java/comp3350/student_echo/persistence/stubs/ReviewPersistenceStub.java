package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.CourseReview;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.InstructorReview;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.persistence.ReviewPersistence;

public class ReviewPersistenceStub implements ReviewPersistence {
    private List<CourseReview> courseReviews;
    private List<InstructorReview> instructorReviews;

    public ReviewPersistenceStub() {
        courseReviews = new ArrayList<>();
        instructorReviews = new ArrayList<>();

        // ADD COURSE REVIEWS
        Course c1 = new Course("CS", "COMP1010", "Intro Comp 1");
        Course c2 = new Course("CS", "MATH1020", "Intro Comp 2");
        courseReviews.add(new CourseReview(c1, "It was good", 5, 3));
        courseReviews.add(new CourseReview(c1, "It was not good at all", 1, 5));
        courseReviews.add(new CourseReview(c2, "It was medium", 3, 4));

        // ADD INSTRUCTOR REVIEWS
        Instructor i1 = new Instructor("Dr.", "John", "Smith");
        Instructor i2 = new Instructor("Professor", "Nancy", "Rogers");
        instructorReviews.add(new InstructorReview(i1, "Good Prof", 4, 3));
        instructorReviews.add(new InstructorReview(i2, "AMAZING PROFESSORRRRR", 5, 2));
    }
    @Override
    public void insertCourseReview(CourseReview R) {
        // not implemented
    }

    @Override
    public void insertInstructorReview(InstructorReview R) {
        // not implemented
    }

    @Override
    public List<CourseReview> getReviewsFor(Course course) {
        List<CourseReview> res = new ArrayList<>();
        for(CourseReview cur : courseReviews) {
            if(cur.getCourse().equals(course)){
                res.add(cur);
            }
        }
        return res;
    }

    @Override
    public List<InstructorReview> getReviewsFor(Instructor instructor) {
        List<InstructorReview> res = new ArrayList<>();
        for(InstructorReview cur : instructorReviews) {
            if(cur.getInstructor().equals(instructor)) {
                res.add(cur);
            }
        }
        return res;
    }
}
