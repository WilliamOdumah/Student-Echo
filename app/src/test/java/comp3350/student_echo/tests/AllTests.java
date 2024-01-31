package comp3350.student_echo.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.student_echo.tests.objects.CourseTest;
import comp3350.student_echo.tests.objects.StudentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentTest.class,
        CourseTest.class,
})
public class AllTests
{

}
