package comp3350.student_echo.tests.business;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.AccessDepartments;
import comp3350.student_echo.objects.Department;
import comp3350.student_echo.persistence.DepartmentPersistence;
import comp3350.student_echo.persistence.stubs.DepartmentPersistenceStub;

public class AccessDepartmentsTest {
    private AccessDepartments accessDepartments;
    private DepartmentPersistence departmentPersistence;

    private List<Department> departmentList;

    @Before
    public void setUp() {
        Services.useStub();
        accessDepartments = new AccessDepartments();
        departmentList = accessDepartments.getDepartmentList();

    }
    @After
    public void tearDown(){
        Services.useHsql();
    }
    @Test
    public void testGetDepartment()
    {
        Department department = new Department("ACC");
        assertTrue("ensure department present in stub", department.equals(departmentList.get(0)));

    }





}
