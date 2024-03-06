package comp3350.student_echo.business;


import java.util.Collections;
import java.util.List;
import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Department;
import comp3350.student_echo.persistence.DepartmentPersistence;

public class AccessDepartments {
    private final DepartmentPersistence departmentPersistence;
    private List<Department> departmentList;


    public AccessDepartments(){
        departmentPersistence = Services.getDepartmentPersistence(true);
        departmentList = null;
    }

    public List<Department> getDepartmentList()
    {
        departmentList = departmentPersistence.getDepartmentSequential();
        return Collections.unmodifiableList(departmentList);
    }


    public Department getDepartment(String departmentname) {
        return departmentPersistence.getDepartment(departmentname);
    }




}
