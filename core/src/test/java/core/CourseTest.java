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

  @Test
  @DisplayName("Cannot add negative score")
  void cannotAddInvalidScore(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      course.addScore(-1);
    });
  }
  @Test
  @DisplayName("Cannot add score bigger than 5")
  void cannotAddScoreBiggerThanFive(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      course.addScore(6);
    });
  }

  @Test
  @DisplayName("Cannot add 0 as score value")
  void cannotAddZeroScoreValue(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      course.addScore(0);
    });
  }

  @Test
  @DisplayName("Can add valid result list")
  void canAddValidResults(){
      ArrayList<Integer> testResults = new ArrayList<>();
      testResults.add(2);
      testResults.add(4);
      testResults.add(1);
      testResults.add(5);
      Assertions.assertTrue(course.checkIfResultsAreValid(testResults));
  }

  @Test
  @DisplayName("Cannot add invalid result list")
  void cannotAddInvalidResults(){
    ArrayList<Integer> testResults = new ArrayList<>();
    testResults.add(3);
    testResults.add(4);
    testResults.add(1);
    testResults.add(-5);
    Assertions.assertFalse(course.checkIfResultsAreValid(testResults));
  }


}

