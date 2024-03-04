package comp3350.student_echo.objects;

import java.io.Serializable;

public class Instructor implements Serializable {
	private int instructorID;
	private String title;
	private String firstName;
	private String lastName;

	public Instructor(String title, String first, String last) {
		this(-1, title, first, last);
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
	public void setID(int id){this.instructorID = id;}

	public boolean equals(Instructor i) {
		return this.firstName.equals(i.firstName) && this.lastName.equals(i.lastName);
	}
}
