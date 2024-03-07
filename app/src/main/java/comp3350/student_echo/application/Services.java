package comp3350.student_echo.application;

import comp3350.student_echo.persistence.AccountPersistence;
import comp3350.student_echo.persistence.CoursePersistence;
import comp3350.student_echo.persistence.DepartmentPersistence;
import comp3350.student_echo.persistence.InstructorPersistence;
import comp3350.student_echo.persistence.hsqldb.CoursePersistenceHSQLDB;
import comp3350.student_echo.persistence.hsqldb.DepartmentPersistenceHSQLDB;
import comp3350.student_echo.persistence.hsqldb.InstructorPersistenceHSQLDB;
import comp3350.student_echo.persistence.hsqldb.AccountPersistenceHSQLDB;
import comp3350.student_echo.persistence.hsqldb.CoursePersistenceHSQLDB;
import comp3350.student_echo.persistence.hsqldb.InstructorPersistenceHSQLDB;
import comp3350.student_echo.persistence.hsqldb.ReviewPersistenceHSQLDB;
import comp3350.student_echo.persistence.stubs.AccountPersistenceStub;
import comp3350.student_echo.persistence.ReviewPersistence;
import comp3350.student_echo.persistence.stubs.CoursePersistenceStub;
import comp3350.student_echo.persistence.stubs.DepartmentPersistenceStub;
import comp3350.student_echo.persistence.stubs.InstructorPersistenceStub;
import comp3350.student_echo.persistence.stubs.ReviewPersistenceStub;

public class Services {
    private static InstructorPersistence instructorPersistence = null;
	private static CoursePersistence coursePersistence = null;
    private static AccountPersistence accountPersistence = null;
    private static ReviewPersistence reviewPersistence = null;

    private static DepartmentPersistence departmentPersistence = null;

    //Make this false to use Stub
    private static boolean hsqlDataBase=true;

	public static synchronized InstructorPersistence getInstructorPersistence() {
		if (instructorPersistence == null) {
            if(hsqlDataBase) {
                instructorPersistence = new InstructorPersistenceHSQLDB(Main.getDBPathName());
            } else {
                instructorPersistence = new InstructorPersistenceStub();
            }
        }
        return instructorPersistence;
	}

    public static synchronized CoursePersistence getCoursePersistence() {
        if (coursePersistence == null) {
            if(hsqlDataBase) {
                coursePersistence = new CoursePersistenceHSQLDB(Main.getDBPathName());
            }
            else {
                coursePersistence = new CoursePersistenceStub();
            }
        }
        return coursePersistence;
    }

    public static synchronized ReviewPersistence getReviewPersistence() {
        if(reviewPersistence == null){
            if(hsqlDataBase) {
                reviewPersistence = new ReviewPersistenceHSQLDB(Main.getDBPathName());
            }
            else {
                reviewPersistence = new ReviewPersistenceStub();
            }
        }
        return reviewPersistence;
    }

    public static synchronized AccountPersistence getAccountPersistence() {
        if (accountPersistence == null) {
            if (hsqlDataBase){
                accountPersistence = new AccountPersistenceHSQLDB(Main.getDBPathName());
            }else{
                accountPersistence = new AccountPersistenceStub();
            }
        }
        return accountPersistence;
    }
    public static synchronized DepartmentPersistence getDepartmentPersistence() {
        if (departmentPersistence == null) {
            if (hsqlDataBase){
                departmentPersistence = new DepartmentPersistenceHSQLDB(Main.getDBPathName());
            }else{
                departmentPersistence = new DepartmentPersistenceStub();
            }
        }
        return departmentPersistence;
    }

    public static synchronized void useHsql(){
        hsqlDataBase=true;
    }
    public static synchronized void useStub(){
        hsqlDataBase=false;
    }
}