package comp3350.student_echo.application;

import comp3350.student_echo.persistence.AccountPersistence;
import comp3350.student_echo.persistence.CoursePersistence;
import comp3350.student_echo.persistence.InstructorPersistence;
import comp3350.student_echo.persistence.stubs.AccountPersistenceStub;
import comp3350.student_echo.persistence.ReviewPersistence;
import comp3350.student_echo.persistence.stubs.CoursePersistenceStub;
import comp3350.student_echo.persistence.stubs.InstructorPersistenceStub;
import comp3350.student_echo.persistence.stubs.ReviewPersistenceStub;

public class Services
{
	private static InstructorPersistence studentPersistence = null;
	private static CoursePersistence coursePersistence = null;
    private static AccountPersistence accountPersistence = null;
    private static ReviewPersistence reviewPersistence = null;


	public static synchronized InstructorPersistence getInstructorPersistence()
    {
		if (studentPersistence == null) {
		    studentPersistence = new InstructorPersistenceStub();
        }
        return studentPersistence;
	}

    public static synchronized CoursePersistence getCoursePersistence()
    {
        if (coursePersistence == null) {
            coursePersistence = new CoursePersistenceStub();
        }
        return coursePersistence;
    }

    public static synchronized ReviewPersistence getReviewPersistence() {
        if(reviewPersistence == null){
            reviewPersistence = new ReviewPersistenceStub();
        }
        return reviewPersistence;
    }

    public static synchronized AccountPersistence getAccountPersistence()
    {
        if (accountPersistence == null)
        {
            accountPersistence = new AccountPersistenceStub();
        }

        return accountPersistence;
    }
}
