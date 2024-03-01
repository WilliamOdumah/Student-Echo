package comp3350.student_echo.objects;

import java.io.Serializable;

public class Instructor implements Serializable {
	private final int instructorID;
	private String title;
	private String firstName;
	private String lastName;

	private static int nextInstructorID = 1;

	public Instructor(String title, String first, String last) {
		this(nextInstructorID++, title, first, last);
	}
	public Instructor(int id, String title, String first, String last) {
		this.instructorID = id;
		this.title = title;
		this.firstName = first;
		this.lastName = last;
	}

	public int getInstructorID() {return instructorID;}
	public String getTitle() {return title;}
	public String getFirstName(){return firstName;}
	public String getLastName() {return lastName;}

	public boolean equals(Instructor i) {
		return this.firstName.equals(i.firstName) && this.lastName.equals(i.lastName);
	}
}
