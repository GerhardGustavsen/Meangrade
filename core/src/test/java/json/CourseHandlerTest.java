package json;

import core.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseHandlerTest {

  CourseHandler testHandler = new CourseHandler();

  @Test
  @DisplayName("Can save courses")
  void canAddCourse() throws IOException {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(4);
    list.add(5);
    Course course = new Course("TDT1000", "Java", "This is a java boy", list);
    Course course1 = new Course("TDT1000", "Java", "This is a java boy", list);
    Course course2 = new Course("TDT1000", "Java", "This is a java boy", list);
    ArrayList<Course> courses = new ArrayList<>();
    courses.add(course2);
    courses.add(course1);
    courses.add(course);

    testHandler.saveCourses(courses);
  }

  @Test
  @DisplayName("Can get all courses")
  void canGetAllCourses() throws IOException {
    ArrayList<Course> courses = testHandler.getAllCourses();
    for (Course course: courses){
      System.out.println(testHandler.courseToString(course));
    }
  }
}
