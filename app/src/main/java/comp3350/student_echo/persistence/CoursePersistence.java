package comp3350.student_echo.persistence;

import java.util.List;

import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.objects.reviewableItems.Course;

public interface CoursePersistence {
    List<Course> getCourseSequential();
    Course getCourse(String courseID);
    void addCourse(Course newCourse) throws InvalidCourseException;
}
