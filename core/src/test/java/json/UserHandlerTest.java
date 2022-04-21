package json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class UserHandlerTest {

  UserHandler instance = new UserHandler();

  @Test
  @DisplayName("Can add user")
  public void canAddUser() throws IOException {
    instance.createUser("Thomas", "123123213");
  }
}
