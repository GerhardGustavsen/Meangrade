package json;

import core.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class UserHandlerTest {
  UserHandler testHandler = new UserHandler("src/test/resources/json/users.txt");


  @BeforeEach
  void init(){
    testHandler.deleteAll();
    testHandler.saveUser(new User("Test", "test", "data"));
  }

  @Test
  @DisplayName("Can save user")
  void canSaveUser() throws FileNotFoundException {
    User user  = new User("hello", "sir", "data");
    testHandler.saveUser(user);
    Assertions.assertEquals(user.getName(), testHandler.getUser("hello").getName());
  }


  @Test
  @DisplayName("Can get all users")
  void canGetUser() throws IOException {
    Assertions.assertNotNull(testHandler.getUser("Test"));
  }

  @Test
  @DisplayName("Can get all users")
  void canGetAllUsers(){
    ArrayList<User> users = testHandler.getAllUsers();
    for(User user: users){
      Assertions.assertNotNull(user);
    }
  }

  @Test
  @DisplayName("Can delete user")
  void canDeleteUser() throws IOException {
    Assertions.assertNotNull(testHandler.getUser("Test"));
    testHandler.deleteUser("Test");
    Assertions.assertNull(testHandler.getUser("Test"));
  }

  @Test
  @DisplayName("Can convert string to User")
  void canConvertStringToCourse(){
    User course = testHandler.stringToUser("Username: Test| HashPassword: test| Grades: data\n");
    //if course is not null that means that the function has returned a Course;
    Assertions.assertNotNull(course);
  }

  @Test
  @DisplayName("Cannot convert string to User with wrong input")
  void cannotConvertStringToCourseWithWrongInput(){
    //if course is not null that means that the function has returned a Course;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      testHandler.stringToUser("Wrong input data");
    });
  }
}
