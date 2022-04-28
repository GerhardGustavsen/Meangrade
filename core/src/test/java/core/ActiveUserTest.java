package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ActiveUserTest {


  @Test
  @DisplayName("Cannot create User with invalid password format")
  void cannotCreateUserWithInvalidPasswordFormat(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new ActiveUser("user", "", "", "123456789");
    });
  }
}
