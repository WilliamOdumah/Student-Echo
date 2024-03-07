package comp3350.student_echo.business.access;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.business.CourseValidator;
import comp3350.student_echo.business.Exceptions.InvalidCourseException;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.reviewableItems.ReviewableItem;
import comp3350.student_echo.persistence.CoursePersistence;

public class AccessCourses implements AccessReviewableItems {
	private CoursePersistence coursePersistence;
	private List<Course> courses;

	public AccessCourses() {
		coursePersistence = Services.getCoursePersistence(true);
		courses = null;
	}
	public AccessCourses(final CoursePersistence persistence) {
		this();
		coursePersistence = persistence;
	}

	public ArrayList<Course> filterCourses(String searchText ,List<Course> courseList) {

		ArrayList<Course> filteredCourses = new ArrayList<>();

		for (Course course: courseList) {
			if (course.getCourseID().toLowerCase().contains(searchText.toLowerCase()) || course.getCourseName().toLowerCase().contains(searchText.toLowerCase())) {
				filteredCourses.add(course);
			}
		}
		return filteredCourses;
	}

	public List<Course> getCourses() {
        courses = coursePersistence.getCourseSequential();
        return Collections.unmodifiableList(courses);
    }

	public Course getCourse(String courseID) {
		return coursePersistence.getCourse(courseID);
	}

	public void addCourse(Course newCourse) throws InvalidCourseException {
		CourseValidator.validateCourse(newCourse);	// throws InvalidCourseException (upon invalid fields)
		coursePersistence.addCourse(newCourse);		// throws InvalidCourseException (upon duplicate)
	}

	@Override
	public List<ReviewableItem> getItems() {
		List<ReviewableItem> list = getCourses().stream().map(course -> (ReviewableItem)course).collect(Collectors.toList());
		return Collections.unmodifiableList(list);
	}

	@Override
	public List<ReviewableItem> filter(String input, List<ReviewableItem> items) {
		List<Course> courseItem = items.stream().map(item ->(Course)item).collect(Collectors.toList());
		List<Course> filteredCourses = filterCourses(input, courseItem);
		return filteredCourses.stream().map(course -> (ReviewableItem)course).collect(Collectors.toList());
	}


}

