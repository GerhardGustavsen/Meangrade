package json;

import core.Encrypt;
import core.Grade;
import core.User;

import java.util.ArrayList;

public class UserHandler extends FileHandler {
  public UserHandler() {
    super("src/main/resources/json/users.txt");
  }

  public void createUser(String userName, String pass){
    ArrayList<Grade> empty = new ArrayList<>();
    User user = new User(userName, pass, empty);
    write(userToString(user));
  }

  public void deleteUser(){

  }

  public void addGrades(){

  }

  public  String userToString(User user){
    return "Username: " + user.getName() + " | HashPassword: " + Encrypt.hash(user.getPass()) + " | Grades: " + "[]";
  }
}
