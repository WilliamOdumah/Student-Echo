package comp3350.student_echo.persistence.stubs;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import comp3350.student_echo.objects.Department;
import comp3350.student_echo.persistence.DepartmentPersistence;

public class DepartmentPersistenceStub implements DepartmentPersistence {
    private final List<Department> departments;
    public DepartmentPersistenceStub(){

        departments = new ArrayList<>();
        departments.add(new Department("ACC"));
        departments.add(new Department("BIOL"));
        departments.add(new Department("CHEM"));
        departments.add(new Department("CIVL"));
        departments.add(new Department("CS"));
        departments.add(new Department("ECON"));
        departments.add(new Department("HNSC"));
        departments.add(new Department("HNSC"));
    }


    @Override
    public List<Department> getDepartmentSequential() {
        return  Collections.unmodifiableList(departments);
    }

    @Override
    public Department getDepartment(String departmentName) {
        return null;
    }

}
