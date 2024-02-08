package comp3350.student_echo.objects;

import java.io.Serializable;

public class Course implements Serializable {
	private final String courseID;
	private final String department;
	private final String courseName;

	public Course(String department, String courseID, String courseName) {
		this.department = department;
		this.courseID = courseID;
		this.courseName = courseName;
	}

	public String getCourseID()
	{
		return (courseID);
	}

	public String getCourseName()
	{
		return (courseName);
	}

	public String getDepartment() {
		return department;
	}

	public boolean equals(Course c) {
		return this.courseID.equals(c.courseID);
	}
}