package json;

import core.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CourseHandlerTest {

  private CourseHandler instance = new CourseHandler();

  @Test
  @DisplayName("Can create file")
  public void canCreateFile(){
    instance.createNewFile();
  }

  @Test
  @DisplayName("Can add course")
  public void canAddCourse(){
    ArrayList<Integer> results = new ArrayList<>();
    Course course = new Course("TDT4100", "Java", "Java course", results );
    instance.addCourse(course);
  }

  @Test
  @DisplayName("Can get course")
  public void canGetCourse() throws FileNotFoundException {
    String code = "TDT4100";
    Course course = instance.getCourse(code);
    System.out.println(course);
    assert course != null;
    System.out.println(instance.courseToString(course));
  }
}
