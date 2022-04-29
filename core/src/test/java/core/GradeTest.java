package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GradeTest {

  Grade grade;

  @BeforeEach
  void init() {
    this.grade = new Grade("TDT4100", 'A', 5, "comment");
  }

  @Test
  @DisplayName("Test valid code")
  void canCheckValidCode() {
    Assertions.assertTrue(this.grade.codeIsValid("TDT1000"));
  }
  @Test
  @DisplayName("Can check invalid code length")
  void canCheckInvalidCode() {
    Assertions.assertFalse(this.grade.codeIsValid("TDT100"));
  }

  @Test
  @DisplayName("Can check invalid code format")
  void canCheckInvalidCodeFormat() {
    Assertions.assertFalse(this.grade.codeIsValid("1000TDT"));
  }

  @Test
  @DisplayName("Cannot set invalid score")
  void cannotSetInvalidScore(){
    Assertions.assertTrue(this.grade.scoreIsValid(4));
    Assertions.assertFalse(this.grade.scoreIsValid(-4));
    Assertions.assertFalse(this.grade.scoreIsValid(0));
    Assertions.assertFalse(this.grade.scoreIsValid(7));
  }

  @Test
  @DisplayName("Cannot set invalid grade")
  void cannotSetInvalidGrade(){
    Assertions.assertTrue(this.grade.gradeIsValid('A'));
    Assertions.assertTrue(this.grade.gradeIsValid('C'));
    Assertions.assertFalse(this.grade.gradeIsValid('G'));
    Assertions.assertFalse(this.grade.gradeIsValid('a'));
  }
}
