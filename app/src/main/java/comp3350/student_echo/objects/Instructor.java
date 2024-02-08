package comp3350.student_echo.objects;

import java.io.Serializable;

public class Instructor implements Serializable {
	private String title;
	private String firstName;
	private String lastName;

	public Instructor(String title, String first, String last) {
		this.title = title;
		firstName = first;
		lastName = last;
	}

	public String getTitle() {return title;}
	public String getFirstName(){return firstName;}
	public String getLastName() {return lastName;}

	public boolean equals(Instructor i) {
		return this.firstName.equals(i.firstName) && this.lastName.equals(i.lastName);
	}
}
