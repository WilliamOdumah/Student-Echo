package comp3350.student_echo.tests.objects;

import org.junit.Test;

import comp3350.student_echo.objects.Instructor;
import static org.junit.Assert.*;

public class StudentTest
{
	@Test
	public void testStudent1()
	{
		Instructor student;
		
		System.out.println("\nStarting testStudent");
		
		student = new Instructor("123", "Joe", "12 Street");
		assertNotNull(student);
		assertTrue("123".equals(student.getStudentID()));
		assertTrue("Joe".equals(student.getStudentName()));
		assertTrue("12 Street".equals(student.getStudentAddress()));
		
		System.out.println("Finished testStudent");
	}
}