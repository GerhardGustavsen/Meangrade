package json;

import core.Course;
import core.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class UserHandlerTest {
  UserHandler test = new UserHandler();


  @Test
  @DisplayName("Can save user")
  void canAddUser() throws IOException {
    User user  = new User("hello", "sir", "data");
    test.saveUser(user);
  }

  @Test
  @DisplayName("Can get all users")
  void canGetUser() throws IOException {
    ArrayList<User> users = test.getAllUsers();
    for(User user: users){
      System.out.println(test.userToString(user));
    }
  }
}
