package comp3350.student_echo.tests.objects;

import org.junit.Before;
import org.junit.Test;

import comp3350.student_echo.objects.Course;

import static org.junit.Assert.*;

public class CourseTest {
	Course c1, c2, c3;
	@Before
	public void before() {
		c1 = new Course("CS", "COMP1010", "Intro Comp");
		c2 = new Course("CS", "COMP1010", "Intro Computer Science I");
		c3 = new Course("MATH", "MATH1240", "Discrete Math");
	}

	@Test
	public void testCourseCreation() {
		assertEquals("Check department name" , c1.getDepartment(), "CS");
		assertEquals("Check courseID", c1.getCourseID(), "COMP1010");
		assertEquals("Check name", c1.getCourseName(), "Intro Comp");
	}

	@Test
	public void testEquals() {
		assertTrue("Same courseId is equal course", c1.equals(c2));
		assertFalse("Different courses", c1.equals(c3));
	}

}