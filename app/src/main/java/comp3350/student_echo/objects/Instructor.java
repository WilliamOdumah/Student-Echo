package comp3350.student_echo.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Instructor implements Serializable {
	String title;
	String firstName;
	String lastName;
	float averageRating;
	ArrayList<InstructorReview> reviewList;

	public Instructor(String title, String first, String last) {
		this.title = title;
		firstName = first;
		lastName = last;
		averageRating = 0;
		reviewList = new ArrayList<>();
	}

	public String getTitle() {return title;}
	public String getFirstName(){return firstName;}
	public String getLastName() {return lastName;}

	public float getAverageRating() {
		return averageRating;
	}

	public ArrayList<InstructorReview> getReviewList() {
		return reviewList;
	}

	public String toString() {
		return String.format("Student: %s %s %s", title, firstName, lastName);
	}
}
