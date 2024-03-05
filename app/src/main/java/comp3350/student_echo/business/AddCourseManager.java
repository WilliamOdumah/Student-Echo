package comp3350.student_echo.business;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.objects.reviewableItems.Course;
import comp3350.student_echo.objects.Department;

public class AddCourseManager {

    private AccessCourses coursesData;

    private AccessDepartments departmentsData;



    public AddCourseManager(){
        coursesData = new AccessCourses();
        departmentsData = new AccessDepartments();
    }

    public Course createCourse(String courseID, String courseName, String department){
        if (!existCourseID(courseID) && verifyCourseIDFormat(courseID)&& verifyDepartment(department) && verifyName(courseName)){

            Course newCourse = new Course(department,courseID,courseName);

//            coursesData.addCourse(newCourse);
            return newCourse;
        }
        return null;


    }

    public boolean verifyDepartment(String department){
        return departmentsData.getDepartment(department) != null;
    }

    //verify course ID format, minimum 7 characters, maximum 8 characters
    //courseID cannot contain space and has to be combination of letters and numbers -- 3 or 4 letters at beginning then 4 numbers
    public boolean verifyCourseIDFormat(String courseID){
        //valid course length is either 7 or 8 (e.g: ACC1100 or COMP1010)
        if (courseID.length() == 7 || courseID.length() == 8 ) {
            if (courseID.contains(" ")){
                return false;
            }else{
                //check format combination of  3 or 4 letters, and 4 numbers
                String regex = "^[A-Za-z]{3}\\d{4}$";

                Pattern pattern = Pattern.compile(regex);

                // Create a matcher with the input courseID
                Matcher matcher = pattern.matcher(courseID);

                // Return true if the input courseID match the pattern
                return matcher.matches();
            }
        }
        return false;
    }

    // verify if courseID exist in the course list
    public boolean existCourseID(String courseID) {

        return coursesData.getCourse(courseID) != null;

    }

    public boolean verifyName(String courseName){
        return courseName != null &&  !courseName.equals("") ;
    }

}
