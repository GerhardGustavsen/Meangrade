package json;

import core.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagerTest {


  @Test
  @DisplayName("Can add course")
  void canAddCourse() throws IOException {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(4);
    list.add(5);
    Course course = new Course("TDT1000", "Java", "This is a java boy", list);
    CourseManager.addCourse(course);
  }
}
