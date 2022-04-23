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

  private JSONArray userData = new JSONArray();
  private JSONArray courseData = new JSONArray();

  private User activeUser;

  public Core() {

    // userData = UserFile.load();
    // courseData = CourseFile.load();

  }

  public boolean logginn(String user, String pas) throws JSONException {
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

    userData.put(map);
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

  public boolean deleteUser(String name) throws JSONException {
    JSONArray newData = new JSONArray();
    boolean awnser = false;

    if (userData != null) {

      for (int i = 0; i < userData.length(); i++) {
        JSONObject userobj = userData.getJSONObject(i);
        String listname = userobj.get("UserName").toString();

        if (!name.equals(listname)) {
          newData.put(userobj);
        }
      }

      if (userData != newData) {
        userData = newData;
        // CourseFile.save(userData);
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
