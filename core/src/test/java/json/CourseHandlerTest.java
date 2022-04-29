package json;

import core.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CourseHandlerTest {

  CourseHandler testHandler = new CourseHandler("src/test/resources/json/courses.txt");
  Course course;

  @BeforeEach
  void init() {
    testHandler.deleteAll();
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(4);
    list.add(5);
    this.course = new Course("TDT1000", "Java", "This is a java boy", list, list);
  }

  @Test
  @DisplayName("Can save courses")
  void canAddCourse() throws IOException {
    ArrayList<Course> courses = new ArrayList<>();
    courses.add(course);
    testHandler.saveCourses(courses);
    Course testCourse = testHandler.getCourse("TDT1000");
    Assertions.assertEquals(testCourse.getName(), course.getName());
  }

  @Test
  @DisplayName("Can get all courses")
  void canGetAllCourses() throws IOException {
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<Course> testCourses = new ArrayList<>();
    Course course1 = new Course("TDT1000", "Java", "This is a description", list, list);
    Course course2 = new Course("TDT1001", "Java1", "This is a description", list, list);
    Course course3 = new Course("TDT1002", "Java2", "This is a description", list, list);
    Course course4 = new Course("TDT1003", "Java3", "This is a description", list, list);
    testCourses.add(course1);
    testCourses.add(course2);
    testCourses.add(course3);
    testCourses.add(course4);

    testHandler.saveCourses(testCourses);
    ArrayList<Course> courses = testHandler.getAllCourses();
    for (int i = 0; i < 4; i++) {
      Assertions.assertEquals(courses.get(i).getName(), courses.get(i).getName());
    }
  }

  @Test
  @DisplayName("Can get course")
  void canGetCourse() throws FileNotFoundException {
    testHandler.saveCourse(course);
    Course course = testHandler.getCourse("TDT1000");
    Assertions.assertEquals("TDT1000", course.getCode());
  }

  @Test
  @DisplayName("Can convert string to Course")
  void canConvertStringToCourse() {
    Course course = testHandler.stringToCourse("Code: TDT1001 | Name: test score | Description: test test test | Results: [6, 4] | Scores: [3, 5]");
    // if course is not null that means that the function has returned a Course;
    Assertions.assertNotNull(course);
  }

  @Test
  @DisplayName("Throw error with wrong string")
  void stringToCourseThrowsErrorForFormat() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Course course = testHandler.stringToCourse("Wrong input!");
    });
  }

  @Test
  @DisplayName("Throw error if code format is wrong")
  void stringToCourseThrowsForWrongCode() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      testHandler.stringToCourse("Code: TDT100 | Name: Java | Description: Test | Results: [1, 4, 5]");
    });
  }

  @Test
  @DisplayName("Can convert string to array")
  void canConvertStringToArray() {
    ArrayList<Integer> list = testHandler.resultsToArray("[1, 2, 4, 5]");
    Assertions.assertNotNull(list);
  }
}
