package comp3350.student_echo.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import comp3350.student_echo.application.Services;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.ReviewableItem;
import comp3350.student_echo.persistence.CoursePersistence;

public class AccessCourses implements AccessReviewableItems {
	private final CoursePersistence coursePersistence;
	private List<Course> courses;

	public AccessCourses() {
		coursePersistence = Services.getCoursePersistence(true);
		courses = null;
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

	@Override
	public List<ReviewableItem> getItems() {
		return getCourses().stream().map(course -> (ReviewableItem)course).collect(Collectors.toList());
	}

	@Override
	public List<ReviewableItem> filter(String input, List<ReviewableItem> items) {
		List<Course> courseItem = items.stream().map(item ->(Course)item).collect(Collectors.toList());
		List<Course> filteredCourses = filterCourses(input, courseItem);
		return filteredCourses.stream().map(course -> (ReviewableItem)course).collect(Collectors.toList());
	}
}

