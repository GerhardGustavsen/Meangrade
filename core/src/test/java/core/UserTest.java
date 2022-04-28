package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

  @Test
  @DisplayName("Get IllegalArgumentException when trying to create user with empty inputs")
  void cannotCreateUserWithEmptyInputs(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new User(null, null, null);
    });
  }
}
