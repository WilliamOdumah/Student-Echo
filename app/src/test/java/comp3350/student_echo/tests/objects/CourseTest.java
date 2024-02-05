package comp3350.student_echo.tests.objects;

import org.junit.Test;

import comp3350.student_echo.objects.Course;

import static org.junit.Assert.*;

public class CourseTest
{
	@Test
	public void testCourse1()
	{
		Course course;

		System.out.println("\nStarting testCourse");

		course = new Course("LOL", "12345", "Software Development");
		assertNotNull(course);
		assertTrue("12345".equals(course.getCourseID()));
		assertTrue("Software Development".equals(course.getCourseName()));

		System.out.println("Finished testCourse");
	}

}