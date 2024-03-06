package comp3350.student_echo.objects;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Department implements Serializable {

    private final String departmentName;
    public Department(String departmentName) {

        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public boolean equals(Department d) {
        return this.departmentName.equals((d.departmentName));
    }
}

