package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ValidatorTest {

  @Test
  @DisplayName("Cannot add password that is shorter than 8 characters")
  void passwordCannotBeShorterThan8(){
    Validator validator = new Validator("", "Aa123!", "");
    Assertions.assertFalse(validator.vpas());
  }
  @Test
  @DisplayName("Cannot add password that is longer than 20 characters")
  void passwordCannotBeLongerThan20(){
    Validator validator = new Validator("", "Aa!4567891233456789123456789", "");
    Assertions.assertFalse(validator.vpas());
  }
  @Test
  @DisplayName("Cannot add password that does not contain an upperCase letter")
  void passwordContainsUpperCase(){
    Validator validator = new Validator("", "aaa1234567!", "");
    Assertions.assertFalse(validator.vpas());
  }

  @Test
  @DisplayName("Cannot add password that does not contain a lower case letters")
  void passwordContainsLowerCase(){
    Validator validator = new Validator("", "AAA1234567!", "");
    Assertions.assertFalse(validator.vpas());
  }

  @Test
  @DisplayName("Cannot add password that only contains numbers")
  void passwordContainsOnlyNumbers(){
    Validator validator = new Validator("", "123456789", "");
    Assertions.assertFalse(validator.vpas());
  }

  @Test
  @DisplayName("Cannot add password that does not contain numbers")
  void passwordContainsNoNumbers(){
    Validator validator = new Validator("", "aaaaaAAAA!", "");
    Assertions.assertFalse(validator.vpas());
  }

  @Test
  @DisplayName("Cannot add password that only contains special characters")
  void passwordContainsOnlySpecialCharacters(){
    Validator validator = new Validator("", "!!!!&!&!&!&&!", "");
    Assertions.assertFalse(validator.vpas());
  }

  @Test
  @DisplayName("Cannot add password that does not contain special characters")
  void passwordContainsNoSpecialCharacters(){
    Validator validator = new Validator("", "Abcdefg123", "");
    Assertions.assertFalse(validator.vpas());
  }

  @Test
  @DisplayName("Cannot add empty password")
  void emptyPassword(){
    Validator validator = new Validator("", "", "");
    Assertions.assertFalse(validator.vpas());
  }

  @Test
  @DisplayName("Can add a correct password")
  void correctPassword(){
    Validator validator = new Validator("", "Password123!", "");
    Assertions.assertTrue(validator.vpas());
  }

  @Test
  @DisplayName("Cannot add user with empty username")
  void cannotAddUserWithEmptyUsername(){
    Validator validator = new Validator("", "", "");
    Assertions.assertFalse(validator.vUserName());
  }

  @Test
  @DisplayName("Can add valid username")
  void canAddUserWithEmptyUsername(){
    Validator validator = new Validator("s", "", "");
    Assertions.assertTrue(validator.vUserName());
  }

  @Test
  @DisplayName("Cannot add repeat password that does not equal password")
  void cannotAddNotMatchingRepeatingPassword(){
    Validator validator = new Validator("", "Password123!", "NoPassword123!");
    Assertions.assertFalse(validator.vrepas());
  }

  @Test
  @DisplayName("Cannot add repeat password that does not equal password")
  void cannotAddEmptyRepeatingPassword(){
    Validator validator = new Validator("", "", "");
    Assertions.assertFalse(validator.vrepas());
  }

  @Test
  @DisplayName("Can add matching passwords")
  void canAddMatchingPasswords(){
    Validator validator = new Validator("", "Password123!", "Password123!");
    Assertions.assertTrue(validator.vrepas());
  }

  @Test
  @DisplayName("Regex matches with string")
  void regexMatchesString(){
    Assertions.assertTrue(Validator.regex("12345", "\\d{5}"));
  }

  @Test
  @DisplayName("Regex does not match with string")
  void regexDoesNotMatchString(){
    Assertions.assertFalse(Validator.regex("12345", "\\d{6}"));
  }

  @Test
  @DisplayName("Writting a letter to int returns null")
  void letterToInt(){
    Assertions.assertNull(Validator.toInt("A"));
  }

  @Test
  @DisplayName("Integer to int returns number")
  void integerToInt(){
    Assertions.assertEquals(4, Validator.toInt("4"));
  }

  @Test
  @DisplayName("Double to int return null")
  void doubleToInt(){
    Assertions.assertNull(Validator.toInt("4.2"));
  }

  @Test
  @DisplayName("Passing non null to notEmpty returns true")
  void objectToNotEmpty(){
    Validator validator = new Validator("", "", "");
    Assertions.assertTrue(Validator.notEmpty(validator));
    Assertions.assertTrue(Validator.notEmpty(2));
    Assertions.assertTrue(Validator.notEmpty("Hello"));
    Assertions.assertTrue(Validator.notEmpty(2.23123));
  }


  @Test
  @DisplayName("Passing non null to notEmpty returns true")
  void emptyToNotEmpty(){
    Assertions.assertFalse(Validator.notEmpty(""));
    Assertions.assertFalse(Validator.notEmpty(null));
  }


}
