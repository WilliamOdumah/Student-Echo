package comp3350.student_echo.persistence.stubs;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.persistence.CoursePersistence;

public class CoursePersistenceStub implements CoursePersistence {
    private Map<String,Course> courses; // (courseID, courseObject)

    public CoursePersistenceStub() {
        this.courses = new HashMap<>();
        addCourse(new Course("CS", "COMP1010", "Introduction to Computer Science 1"));
        addCourse(new Course("CS", "COMP1020", "Introduction Computer Science 2"));
        addCourse(new Course("CS", "COMP2080", "Analysis of Algorithms"));
        addCourse(new Course("CS", "COMP2140", "Data Structures and Algorithms"));
        addCourse(new Course("CS", "COMP2160", "Programming Practices"));
        addCourse(new Course("CS", "COMP3010", "Distributed Computing"));
        addCourse(new Course("CS", "COMP3020", "Human-Computer Interaction 1"));
        addCourse(new Course("CS", "COMP3350", "Software Engineering 1"));
        addCourse(new Course("CS", "COMP3380", "Databases Concepts and Usage"));
        addCourse(new Course("CS", "COMP4620", "Professional Practice in Computer Science"));
        addCourse(new Course("CS", "COMP4350", "Software Engineering 2"));
    }

    @Override
    public List<Course> getCourseSequential() {
        return Collections.unmodifiableList((List<Course>)courses.values());
    }

    @Override
    public Course getCourse(String courseID) {
        return courses.getOrDefault(courseID,null);
    }

    @Override
    public void addCourse(Course newCourse) throws InvalidCourseException{
        // no duplicate primary keys (courseID)
        if(courses.containsKey(newCourse.getCourseID())){
            throw new InvalidCourseException(String.format("courseID=%s already exists!",newCourse.getCourseID()));
        }
        courses.put(newCourse.getCourseID(), newCourse);
    }
}
