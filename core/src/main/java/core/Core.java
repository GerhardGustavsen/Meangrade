package core;

import json.CourseHandler;
import json.UserHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

  public void loggOut() {
    activeUser = null;
  }

  public User getuser(String username) {
    User user = null;

    Iterator<User> it = users.iterator();
    while (it.hasNext()) {
      User realUser = it.next();
      String realUserName = realUser.getName().replaceAll("\\s+", "");
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
    System.out.println("Hello");
    if (encryptedGrades != null && encryptedGrades.length() > 0) {
      System.out.println(encryptedGrades);
      System.out.println(password);
      System.out.println("Fire123!" == password.trim());
      // We decrypt the data:
      String decryptedData = Encrypt.decrypt(encryptedGrades, password);
      System.out.println(decryptedData);
      String[] gradesString = decryptedData.split("\\&");
      System.out.println(Arrays.toString(gradesString));
      for (String grade: gradesString){
        String[] parts = grade.split("\\|");
        ArrayList<String> gradeData = new ArrayList<>();
        for (String part: parts){
          Matcher m = Pattern.compile(": (.*)").matcher(part);
          if(m.find()){
            gradeData.add(m.group(1));
          }
        }
        grades.add(new Grade(gradeData.get(0), gradeData.get(1).charAt(0),Integer.parseInt(gradeData.get(2)), gradeData.get(3)));
      }

    }

    activeUser.setGrades(grades);
  }

  public void newProfile(String username, String pas) {
    User user = new User(username, Encrypt.hash(pas), "");
    users.add(user);
    // save???
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
    ArrayList<Grade> grades = activeUser.getGrades();
    String data = "";
    for (Grade oneGrade : grades) {
      data.concat(userHandler.gradeToString(oneGrade));
      data.concat("&");
    }
    String encryptedData = Encrypt.encrypt(data, activeUser.getPass());
    userHandler.addGrade(activeUser, encryptedData);
    System.out.println(data);
  }

  public void newCourse(String code, String name, int grade, int num, String desc) {
    ArrayList<Integer> allGrades = new ArrayList<Integer>();

    for (int i = 0; i < num; i++) {
      allGrades.add(grade);
    }

    Course course = new Course(code, name, desc, allGrades);
    courses.add(course);
    // save???
  }

  /*
   * public boolean deleteUser(String name) throws JSONException {
   * JSONArray newData = new JSONArray();
   * boolean awnser = false;
   * 
   * if (userData != null) {
   * 
   * for (int i = 0; i < userData.length(); i++) {
   * JSONObject userobj = userData.getJSONObject(i);
   * String listname = userobj.get("UserName").toString();
   * 
   * if (!name.equals(listname)) {
   * newData.put(userobj);
   * }
   * }
   * 
   * if (userData != newData) {
   * userData = newData;
   * // CourseFile.save(userData);
   * awnser = true;
   * }
   * 
   * }
   * 
   * return awnser;
   * }
   */

  /*
   * public boolean addData(String user, String dat) { // WTF this do??? maby move
   * to file handling???
   * JSONObject jsonUser = Validator.userexsist(user, data);
   * 
   * if (jsonUser != null) {
   * deleteUser(user);
   * 
   * jsonUser.put("Data", dat);
   * 
   * data.add(jsonUser);
   * 
   * CourseFile.save(data);
   * return true;
   * }
   * return false;
   * }
   */
  /*
   * public JSONArray getUserData() {
   * return userData;
   * 
   * }
   */

}
