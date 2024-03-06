package comp3350.student_echo.business;

import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.student_echo.business.access.AccessCourses;
import comp3350.student_echo.objects.reviewableItems.Course;


public class AddCourseManager {

    private AccessCourses coursesData;

    private AccessDepartments departmentsData;



    public AddCourseManager(){
        coursesData = new AccessCourses();
        departmentsData = new AccessDepartments();
    }

    public Course createCourse(String courseID, String courseName, String department){

        if (!existCourseID(courseID) && validName(courseName)){

            Course newCourse = new Course(department,courseID,courseName);

//            coursesData.addCourse(newCourse);
            return newCourse;
        }
        return null;


    }

//    public boolean verifyDepartment(String department){
//        return departmentsData.getDepartment(department) != null;
//    }

    //verify course ID format, minimum 7 characters, maximum 8 characters
    //courseID cannot contain space and has to be combination of letters and numbers -- 3 or 4 letters at beginning then 4 numbers
    public boolean verifyCourseIDFormat(String courseID){
        //valid course length is either 7 or 8 (e.g: ACC1100 or COMP1010)
        if (courseID.length() == 7 || courseID.length() == 8 ) {
            if (courseID.contains(" ")){
                System.out.println("Invalid Course ID data");
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
        System.out.println("Invalid Course ID length");
        return false;
    }

    // verify if courseID exist in the course database
    // if the course ID format correct, then search in the course database
    public boolean existCourseID(String courseID) {

        if (!verifyCourseIDFormat(courseID)){
            System.out.println("Invalid course ID format");
            return false;
        }
        Course result = coursesData.getCourse(courseID);
        if (result == null){
            System.out.println("Course does not exist in the database");
        }else {
            System.out.println("Course exist in the database");
        }
        return coursesData.getCourse(courseID) != null;
    }

    public boolean validName(String courseName){

        Course result = coursesData.getCourseOnName(courseName);

        if (courseName == null){
            System.out.println("Input empty course name");
        }

        else if(result != null) {
            System.out.println("Course name exist in the database");
        }


        return courseName != null && result == null;
    }

}
