package core;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Validator {

  JSONArray data;

  String username;
  String password;
  String repassword;

  boolean userstar = false;
  boolean passtar = false;
  boolean repasstar = false;

  String usermsg = "";
  String pasmsg = "";
  String repasmsg = "";

  public Validator() {
  }

  public Validator(String un, String pas, String repas, JSONArray d) {
    data = d;

    username = un;
    password = pas;
    repassword = repas;
  }

  public void setData(String un, String pas, String repas, JSONArray d) {
    data = d;
    username = un;
    password = pas;
    repassword = repas;
  }

  public String[] getData() {
    String[] stringArray = (String[]) data.toArray(new String[data.size()]);
    String[] arr = { username, password, repassword, stringArray.toString() };
    return arr;
  }

  public boolean register() {
    return (vusername() & vpas() && vrepas());
  }

  public boolean logginn() {
    return (notEmpty(username) && vpas());
  }

  public boolean vusername() {
    if (notEmpty(username)) {
      if (userexsist(username, data) == null) {
        return true;
      } else {
        usermsg = "This username is taken!";
        return false;
      }
    }
    userstar = true;
    return false;
  }

  public static JSONObject userexsist(String name, JSONArray arr) {
    JSONObject user = null;
    if (arr != null) {
      Iterator<?> iterator = arr.iterator();
      while (iterator.hasNext()) {
        JSONObject userobj = (JSONObject) iterator.next(); // HER MÅ VI FÅ JACKSON!!!
        String listname = userobj.get("UserName").toString();

        if (name.equals(listname)) {
          System.out.println("Found user");
          user = userobj;
        }
      }
    } else {
      System.out.println("no users exist!");
    }
    return user;
  }

  public boolean vpas() {
    if (notEmpty(password)) {

      String upperCaseChars = "(.*[A-Z].*)";
      String lowerCaseChars = "(.*[a-z].*)";
      String numbers = "(.*[0-9].*)";
      String specialChars = "(.*[@,#,$,%,!].*$)";

      if (password.length() > 15 || password.length() < 8) {
        pasmsg = "Password must be less than 20 and more than 8 characters in length.";
        return false;
      } else if (!password.matches(upperCaseChars)) {
        pasmsg = "Password must have atleast one uppercase character";
        return false;
      } else if (!password.matches(lowerCaseChars)) {
        pasmsg = "Password must have atleast one lowercase character";
        return false;
      } else if (!password.matches(numbers)) {
        pasmsg = "Password must have atleast one number";
        return false;
      } else if (!password.matches(specialChars)) {
        pasmsg = "Password must have atleast one special character among @#$%!";
        return false;
      }

      return true;
    }
    passtar = true;
    return false;

  }

  public boolean vrepas() {
    if (notEmpty(repassword)) {
      if (!repassword.equals(password)) {
        repasmsg = "Password does not mach!";
        return false;
      }
      return true;
    }
    repasstar = true;
    return false;
  }

  // General validators:
  private <T> boolean notEmpty(T input) {
    if (input != null) {
      return !input.toString().isEmpty();
    }

    return false;
  }

  // Returning error info:
  public Boolean[] getstars() {
    Boolean[] stars = { userstar, passtar, repasstar };
    return stars;
  }

  public String[] getMsg() {
    String[] arr = { usermsg, pasmsg, repasmsg };
    return arr;
  }
}
