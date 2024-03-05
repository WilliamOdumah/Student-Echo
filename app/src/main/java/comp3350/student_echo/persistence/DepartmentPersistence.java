package comp3350.student_echo.persistence;

import comp3350.student_echo.objects.Department;

import java.util.List;
public interface DepartmentPersistence {
    List<Department> getDepartmentSequential();
    Department getDepartment(String departmentName);

}
