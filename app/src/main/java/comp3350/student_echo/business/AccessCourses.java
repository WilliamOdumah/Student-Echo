package comp3350.student_echo.business;

import java.util.Collections;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.persistence.CoursePersistence;

public class AccessCourses
{
	private final CoursePersistence coursePersistence;
	private List<Course> courses;

	public AccessCourses()
	{
		coursePersistence = Services.getCoursePersistence();
		courses = null;
	}

	public AccessCourses(CoursePersistence coursePersistence) {
		this.coursePersistence = coursePersistence;
		courses = null;
	}

	public List<Course> getCourses()
    {
        courses = coursePersistence.getCourseSequential();
        return Collections.unmodifiableList(courses);
    }
}
