package comp3350.student_echo.tests.business.integration;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.AccessDepartments;
import comp3350.student_echo.objects.Department;
import comp3350.student_echo.tests.utils.TestUtils;

public class AccessDepartmentsIT {

    private File tempDB;
    private AccessDepartments dept;
    @Before
    public void setUp() throws IOException {
        Services.useHsql();
        this.tempDB = TestUtils.copyDB();
        dept= new AccessDepartments();
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

    @Test
    public void listTest(){
        List<Department> deptList=dept.getDepartmentList();
        assertNotNull("Checking if list is not null", deptList);
    }



}