package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CourseTest {


  Course course;

  @BeforeEach
  void init() {
    ArrayList<Integer> results = new ArrayList<>();
    results.add(1);
    course = new Course("TDT4100", "name", "description", results);
  }

  @Test
  @DisplayName("Test valid code")
  void canCheckValidCode() {
    Assertions.assertTrue(course.codeIsValid("TDT1000"));
  }

  @Test
  @DisplayName("Can check invalid code length")
  void canCheckInvalidCode() {
    Assertions.assertFalse(course.codeIsValid("TDT100"));
  }

  @Test
  @DisplayName("Can check invalid code format")
  void canCheckInvalidCodeFormat() {
    Assertions.assertFalse(course.codeIsValid("1000TDT"));
  }

  @Test
  @DisplayName("Get score avarage")
  void canGetScoreAvarage(){
    course.addScore(1);
    course.addScore(2);
    course.addScore(3);
    Assertions.assertEquals(2, course.getScore());
  }


}

