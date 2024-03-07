package comp3350.student_echo.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.student_echo.tests.business.AccessAccountsTest;
import comp3350.student_echo.tests.business.AccessCoursesTest;
import comp3350.student_echo.tests.business.AccessInstructorsTest;
import comp3350.student_echo.tests.business.AccessReviewsTest;
import comp3350.student_echo.tests.business.LoginManagerTest;
import comp3350.student_echo.tests.business.AverageCalculatorTest;
import comp3350.student_echo.tests.business.StudentAccountManagerTest;
import comp3350.student_echo.tests.objects.ReviewWithCourseTest;
import comp3350.student_echo.tests.objects.CourseTest;
import comp3350.student_echo.tests.objects.ReviewWithInstructorTest;
import comp3350.student_echo.tests.objects.InstructorTest;
import comp3350.student_echo.tests.objects.ReviewTest;
import comp3350.student_echo.tests.objects.StudentAccountTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // business
        AccessAccountsTest.class,
        AccessCoursesTest.class,
        AccessInstructorsTest.class,
        AccessReviewsTest.class,
        LoginManagerTest.class,
        AverageCalculatorTest.class,
        StudentAccountManagerTest.class,
        AccessReviewsTest.class,

        // objects
        ReviewWithCourseTest.class,
        CourseTest.class,
        ReviewWithInstructorTest.class,
        InstructorTest.class,
        ReviewTest.class,
        StudentAccountTest.class
})
public class AllTests
{

}
