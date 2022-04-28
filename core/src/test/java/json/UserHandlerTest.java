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
    this.testHandler.deleteAll();
    this.testHandler.saveUser(new User("Test", "test", "data"));
  }

  @Test
  @DisplayName("Can save user")
  void canSaveUser() throws FileNotFoundException {
    User user  = new User("hello", "sir", "data");
    this.testHandler.saveUser(user);
    Assertions.assertEquals(user.getName(), this.testHandler.getUser("hello").getName());
  }


  @Test
  @DisplayName("Can get all users")
  void canGetUser() throws IOException {
    Assertions.assertNotNull(this.testHandler.getUser("Test"));
  }

  @Test
  @DisplayName("Can get all users")
  void canGetAllUsers(){
    ArrayList<User> users = this.testHandler.getAllUsers();
    for(User user: users){
      Assertions.assertNotNull(user);
    }
  }

  @Test
  @DisplayName("Can delete user")
  void canDeleteUser() throws IOException {
    Assertions.assertNotNull(this.testHandler.getUser("Test"));
    this.testHandler.deleteUser("Test");
    Assertions.assertNull(this.testHandler.getUser("Test"));
  }

  @Test
  @DisplayName("Can convert string to User")
  void canConvertStringToCourse(){
    User course = this.testHandler.stringToUser("Username: Test| HashPassword: test| Grades: data\n");
    //if course is not null that means that the function has returned a Course;
    Assertions.assertNotNull(course);
  }

  @Test
  @DisplayName("Cannot convert string to User with wrong input")
  void cannotConvertStringToCourseWithWrongInput(){
    //if course is not null that means that the function has returned a Course;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.testHandler.stringToUser("Wrong input data");
    });
  }
}
