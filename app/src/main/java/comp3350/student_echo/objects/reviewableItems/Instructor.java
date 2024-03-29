package comp3350.student_echo.objects.reviewableItems;

import java.io.Serializable;

public class Instructor implements Serializable,ReviewableItem {
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
		return this.title.equals(i.title) && this.firstName.equals(i.firstName) && this.lastName.equals(i.lastName);
	}

	// IMPLEMENTATION OF REVIEWABLE ITEM
	@Override
	public String getPrimaryName(){return firstName+" "+lastName;}
	@Override
	public String getSecondaryName() {return title;}
	@Override
	public String getDisplayInfo() {return title+" "+firstName+" "+lastName;}
	@Override
	public String getDepartment() {return null;}
	@Override
	public String getID() {return String.valueOf(instructorID);}
}
