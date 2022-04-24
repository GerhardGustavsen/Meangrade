package json;

import core.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserHandler extends FileHandler {
  public UserHandler(String path) {
    super("core/src/main/resources/json/users.txt");
  }

  public void saveUser(User user) {
    write(userToString(user));
  }

  public void deleteUser(String username) throws IOException {
    File file = getFile();
    List<String> users = Files.lines(file.toPath()).filter(user -> !user.contains(username))
        .collect(Collectors.toList());
    Files.write(file.toPath(), users, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
  }
  // TODO: Get all users

  public void saveAllUsers(ArrayList<User> users) throws IOException {
    deleteAll();
    for (User user : users) {
      write(userToString(user));
    }
  }

  public ArrayList<User> getAllUsers() {
    try {
      Scanner reader = read();
      ArrayList<User> users = new ArrayList<>();
      while (reader.hasNextLine()) {
        String data = reader.nextLine();
        users.add(stringToUser(data));
      }
      return users;
    } catch (FileNotFoundException e) {
      System.out.println("Could not fetch the courses");
      e.printStackTrace();
    }
    return null;
  }

  public User getUser(String username) throws FileNotFoundException {
    Scanner courseReader = read();
    while (courseReader.hasNextLine()) {
      String data = courseReader.nextLine();
      if (data.contains("Username: " + username)) {
        return stringToUser(data);
      }
    }
    courseReader.close();
    return null;
  }

  public void saveGrade(ActiveUser user) throws IOException {
    deleteUser(user.getName());
    write(userToString(user));
  }

  public User stringToUser(String data) {
    String[] parts = data.split("\\|");
    ArrayList<String> userData = new ArrayList<>();
    for (String part : parts) {
      Matcher m = Pattern.compile(": (.*)").matcher(part);
      if (m.find()) {
        userData.add(m.group(1));
      }
    }
    return new User(userData.get(0), userData.get(1), userData.get(2));
  }

  public static String gradeToString(Grade grade) {
    return "Code: " + grade.getCode() + "| Grade: " + grade.getGrade() + "| Score: " + grade.getScore() + "| Comment: "
        + grade.getComment();
  }

  public static String userToString(User user) {
    return "Username: " + user.getName() + "| HashPassword: " + user.getPassHash() + "| Grades: "
        + user.getEncryptedGrades();
  }
}
