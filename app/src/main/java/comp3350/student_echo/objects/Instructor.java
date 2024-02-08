package comp3350.student_echo.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Instructor implements Serializable {
	String title;
	String firstName;
	String lastName;

	public Instructor(String title, String first, String last) {
		this.title = title;
		firstName = first;
		lastName = last;
	}

	public String getTitle() {return title;}
	public String getFirstName(){return firstName;}
	public String getLastName() {return lastName;}
}
