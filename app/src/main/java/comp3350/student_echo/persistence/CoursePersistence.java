package comp3350.student_echo.persistence;

import java.util.List;

import comp3350.student_echo.objects.Course;

public interface CoursePersistence {
    List<Course> getCourseSequential();
    Course getCourse(String courseID);
}
