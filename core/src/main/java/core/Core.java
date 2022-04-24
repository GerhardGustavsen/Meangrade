package core;

import json.CourseFile;
import json.UserFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Core {

  private ArrayList<User> users = new ArrayList<User>();
  private ArrayList<Course> courses = new ArrayList<Course>();

  private ActiveUser activeUser;

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

      String[] coursesText = decryptedData.split("\\|");
      // ADD A TRY!
      for (String str : coursesText) {
        String[] gradeText = str.split(",");
        Grade grade = new Grade(gradeText[0], gradeText[1].charAt(0));
        grades.add(grade);
      }
    }

    activeUser.setGrades(grades);
  }

  public void newProfile(String username, String pas) {
    User user = new User(username, Encrypt.hash(pas), "");
    users.add(user);
    // save???
  }

  public ActiveUser getActiveUser() {
    return activeUser;
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
