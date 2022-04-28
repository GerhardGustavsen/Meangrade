package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GradeTest {

  Grade grade;

  @BeforeEach
  void init() {
    grade = new Grade("TDT4100", 'A', 5, "comment");
  }

  @Test
  @DisplayName("Test valid code")
  void canCheckValidCode() {
    Assertions.assertTrue(grade.codeIsValid("TDT1000"));
  }
  @Test
  @DisplayName("Can check invalid code length")
  void canCheckInvalidCode() {
    Assertions.assertFalse(grade.codeIsValid("TDT100"));
  }

  @Test
  @DisplayName("Can check invalid code format")
  void canCheckInvalidCodeFormat() {
    Assertions.assertFalse(grade.codeIsValid("1000TDT"));
  }

  @Test
  @DisplayName("Cannot set invalid score")
  void cannotSetInvalidScore(){
    Assertions.assertTrue(grade.scoreIsValid(4));
    Assertions.assertFalse(grade.scoreIsValid(-4));
    Assertions.assertFalse(grade.scoreIsValid(0));
    Assertions.assertFalse(grade.scoreIsValid(7));
  }

  @Test
  @DisplayName("Cannot set invalid grade")
  void cannotSetInvalidGrade(){
    Assertions.assertTrue(grade.gradeIsValid('A'));
    Assertions.assertTrue(grade.gradeIsValid('C'));
    Assertions.assertFalse(grade.gradeIsValid('G'));
    Assertions.assertFalse(grade.gradeIsValid('a'));
  }
}
