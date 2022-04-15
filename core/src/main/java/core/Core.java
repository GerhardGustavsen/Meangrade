package core;

import json.CourseFile;
import json.UserFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Core {

  private JSONArray userData = new JSONArray();
  private JSONArray courseData = new JSONArray();

  private User activeUser;

  public Core() {
    userData = UserFile.load();
    courseData = CourseFile.load();
  }

  public boolean logginn(String user, String pas) {
    JSONObject jsonUser = Validator.userexsist(user, userData);
    if (jsonUser != null) {
      String pasHash = jsonUser.get("PassHash").toString();
      if (Encrypt.hash(pas).equals(pasHash)) {
        System.out.println("LOGG INN SUCSESS!");
        String encryptedGrades = jsonUser.get("Data").toString();
        setActiveUser(user, pas, encryptedGrades);
        return true;
      }
    }
    return false;
  }

  public void newProfile(String user, String pas) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("UserName", user);
    map.put("PassHash", Encrypt.hash(pas));
    map.put("Data", "");

    userData.add(map);
    // save???
  }

  private void setActiveUser(String username, String password, String encryptedGrades) {
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

    activeUser = new User(username, password, grades);
  }

  public boolean deleteUser(String name) {
    ArrayList<JSONObject> newdata = new ArrayList<JSONObject>();
    boolean awnser = false;

    if (userData != null) {
      Iterator<?> iterator = userData.iterator();
      while (iterator.hasNext()) {
        JSONObject userobj = (JSONObject) iterator.next();
        String listname = userobj.get("UserName").toString();
        if (!name.equals(listname)) {
          newdata.add(userobj);
        }
      }
      if (userData != newdata) {
        userData = (JSONArray) newdata;
        CourseFile.save(userData);
        awnser = true;
      }
    }
    return awnser;
  }

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

  public JSONArray getUserData() {
    return userData;

  }

  public User getActiveUser() {
    return activeUser;
  }

}
