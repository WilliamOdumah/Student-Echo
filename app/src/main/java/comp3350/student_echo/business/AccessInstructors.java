package comp3350.student_echo.business;

import java.util.Collections;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.persistence.InstructorPersistence;

public class AccessInstructors
{
	private InstructorPersistence instructorPersistence;
	private List<Instructor> instructors;
	private Instructor instructor;
	private int currentInstructor;

	public AccessInstructors()
	{
		instructorPersistence = Services.getStudentPersistence();
		instructors = null;
		instructor = null;
		currentInstructor = 0;
	}

    public List<Instructor> getInstructors()
    {
        instructors = instructorPersistence.getInstructorSequential();
        return Collections.unmodifiableList(instructors);
    }

    public Instructor getSequential()
    {
        if (instructors == null)
        {
            instructors = instructorPersistence.getInstructorSequential();
            currentInstructor = 0;
        }
        if (currentInstructor < instructors.size())
        {
            instructor = instructors.get(currentInstructor);
            currentInstructor++;
        }
        else
        {
            instructors = null;
            instructor = null;
            currentInstructor = 0;
        }
        return instructor;
    }

	public Instructor insertStudent(Instructor currentStudent)
	{
		return instructorPersistence.insertInstructor(currentStudent);
	}

	public Instructor updateStudent(Instructor currentStudent)
	{
		return instructorPersistence.updateInstructor(currentStudent);
	}

	public void deleteStudent(Instructor currentStudent)
	{
		instructorPersistence.deleteInstructor(currentStudent);
	}
}
