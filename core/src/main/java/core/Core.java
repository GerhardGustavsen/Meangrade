package core;

import json.CourseHandler;
import json.UserHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Core {

  private ArrayList<User> users = new ArrayList<User>();
  private ArrayList<Course> courses = new ArrayList<Course>();

  private ActiveUser activeUser;

  UserHandler userHandler = new UserHandler("core/src/main/resources/json/users.txt");
  CourseHandler courseHandler = new CourseHandler("core/src/main/resources/json/courses.txt");

  public Core() throws FileNotFoundException {
    this.users = userHandler.getAllUsers();
    this.courses = courseHandler.getAllCourses();
  }

  public boolean logginn(String username, String pas) {
    User realUser = getuser(username); // Find the user if it exists
    if (realUser != null) {
      String pasHash = realUser.getPassHash();
      if (Encrypt.hash(pas).equals(pasHash)) {
        System.out.println("LOGG INN SUCSESS!");

        String encryptedGrades = realUser.getEncryptedGrades();
        setActiveUser(username, pas, encryptedGrades);
        return true;
      }
    }
    return false;
  }

  public void loggOut() throws FileNotFoundException {
    this.users = userHandler.getAllUsers();
    this.courses = courseHandler.getAllCourses();
    activeUser = null;
  }

  public User getuser(String username) {
    User user = null;

    Iterator<User> it = users.iterator();
    while (it.hasNext()) {
      User realUser = it.next();
      if (realUser.getName().equals(username)) {
        System.out.println("Found user!");
        user = realUser;
        break;
      } else {
        System.out.println(realUser.getName() + " != " + username);
      }
    }

    return user;
  }

  private void setActiveUser(String username, String password, String encryptedGrades) {
    activeUser = new ActiveUser(username, Encrypt.hash(password), encryptedGrades, password);

    // decrypt the grades:
    ArrayList<Grade> grades = new ArrayList<Grade>();

    if (encryptedGrades != null && encryptedGrades.length() > 0) {

      // We decrypt the data:
      String decryptedData = Encrypt.decrypt(encryptedGrades, password);

      String[] gradesString = decryptedData.split("\\&");
      for (String gradeString : gradesString) {
        ArrayList<String> gradeData = new ArrayList<>();

        String[] parts = gradeString.split("\\|");

        for (String part : parts) {
          Matcher m = Pattern.compile(": (.*)").matcher(part);
          if (m.find()) {
            gradeData.add(m.group(1));
          }
        }

        Grade grade = new Grade(gradeData.get(0), gradeData.get(1).charAt(0), Integer.parseInt(gradeData.get(2)),
            gradeData.get(3));

        grades.add(grade);
      }

    } else {
      System.out.println("no data found!");
    }
    activeUser.setGrades(grades);
  }

  public void newProfile(String username, String pas) {
    User user = new User(username, Encrypt.hash(pas), "");
    users.add(user);
    userHandler.saveUser(user);
  }

  public ActiveUser getActiveUser() {
    return activeUser;
  }

  public ArrayList<Course> getCourses() {
    return courses;
  }

  public void newGrade(char grade, String courseCode, Integer score, String comment) throws IOException {
    Grade newGrade = new Grade(courseCode, grade, score, comment);
    activeUser.addGrade(newGrade);
    userHandler.saveGrade(activeUser);
  }

  public void newCourse(String code, String name, int grade, int num, String desc) {
    ArrayList<Integer> allGrades = new ArrayList<Integer>();

    for (int i = 0; i < num; i++) {
      allGrades.add(grade);
    }

    Course course = new Course(code, name, desc, allGrades);

    courses.add(course);
    courseHandler.saveCourse(course);
  }

  public void removeGrade(String code) throws IOException {
    for (int i = 0; i < activeUser.getGrades().size(); i++) {
      if (code.equals(activeUser.getGrades().get(i).getCode())) {
        activeUser.getGrades().remove(i);
      }
    }
    userHandler.saveGrade(activeUser);
  }
}
