package comp3350.student_echo.business;

import java.util.Collections;
import java.util.List;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.persistence.CoursePersistence;
import comp3350.student_echo.persistence.InstructorPersistence;

public class AccessInstructors
{
	private InstructorPersistence instructorPersistence;
	private List<Instructor> instructors;
	private Instructor instructor;
	private int currentInstructor;

	public AccessInstructors()
	{
		instructorPersistence = Services.getInstructorPersistence();
		instructors = null;
		instructor = null;
		currentInstructor = 0;
	}

    public AccessInstructors(InstructorPersistence instructorPersistence){

        this.instructorPersistence = instructorPersistence;
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

	public Instructor insertInstructor(Instructor currentInstructor)
	{
		return instructorPersistence.insertInstructor(currentInstructor);
	}

	public Instructor updateInstructor(Instructor currentInstructor)
	{
		return instructorPersistence.updateInstructor(currentInstructor);
	}

	public void deleteInstructor(Instructor currentInstructor)
	{
		instructorPersistence.deleteInstructor(currentInstructor);
	}
}
