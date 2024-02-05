package comp3350.student_echo.objects;

import java.util.ArrayList;

public class Course {
	final String courseID;
	final String department;
	final String courseName;
	float averageRating;
	ArrayList<CourseReview> reviewList;

	public Course(String department, String courseID, String courseName) {
		this.department = department;
		this.courseID = courseID;
		this.courseName = courseName;
		averageRating = -1;
		reviewList = new ArrayList<>();
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

	public float getAverageRating() {
		return averageRating;
	}

	public ArrayList<CourseReview> getReviewList() {
		return reviewList;
	}

	public String toString()
	{
		return String.format("Course: %s %s", courseID, courseName);
	}
}