package comp3350.student_echo.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.student_echo.objects.Course;
import comp3350.student_echo.persistence.CoursePersistence;

public class CoursePersistenceStub implements CoursePersistence {
    private List<Course> courses;

    public CoursePersistenceStub() {
        this.courses = new ArrayList<>();
        courses.add(new Course("CS", "COMP1010", "Introduction to Computer Science 1"));
        courses.add(new Course("CS", "COMP1020", "Introduction Computer Science 2"));
        courses.add(new Course("CS", "COMP2080", "Analysis of Algorithms"));
        courses.add(new Course("CS", "COMP2140", "Data Structures and Algorithms"));
        courses.add(new Course("CS", "COMP2160", "Programming Practices"));
        courses.add(new Course("CS", "COMP3010", "Distributed Computing"));
        courses.add(new Course("CS", "COMP3020", "Human-Computer Interaction 1"));
        courses.add(new Course("CS", "COMP3350", "Software Engineering 1"));
        courses.add(new Course("CS", "COMP3380", "Databases Concepts and Usage"));
        courses.add(new Course("CS", "COMP4620", "Professional Practice in Computer Science"));
        courses.add(new Course("CS", "COMP4350", "Software Engineering 2"));
    }

    @Override
    public List<Course> getCourseSequential() {
        return Collections.unmodifiableList(courses);
    }
}
