package comp3350.student_echo.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.student_echo.tests.business.integration.AccessAccountsIT;
import comp3350.student_echo.tests.business.integration.AccessCoursesIT;
import comp3350.student_echo.tests.business.integration.AccessInstructorsIT;
import comp3350.student_echo.tests.business.integration.AccessReviewsIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessAccountsIT.class,
        AccessCoursesIT.class,
        AccessInstructorsIT.class,
        AccessReviewsIT.class
})
public class IntegrationTests
{

}