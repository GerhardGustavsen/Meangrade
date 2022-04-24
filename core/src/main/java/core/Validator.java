package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

  String username;
  String password;
  String repassword;

  String usermsg = "";
  String pasmsg = "";
  String repasmsg = "";

  public Validator(String un, String pas, String repas) {
    username = un;
    password = pas;
    repassword = repas;
  }

  public boolean register() {
    return (vUserName() & vpas() & vrepas());
  }

  public boolean logginn() {
    return (vUserName() & vpas());
  }

  private boolean vpas() {
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
    pasmsg = "Password can't be empty!";
    return false;

  }

  private boolean vrepas() {
    if (notEmpty(repassword)) {
      if (!repassword.equals(password)) {
        repasmsg = "Password does not mach!";
        return false;
      }
      return true;
    }
    repasmsg = "Password must be repeated!";
    return false;
  }

  private boolean vUserName() {
    if (notEmpty(username)) {
      return true;
    }
    usermsg = "Username can't be empty!";
    return false;
  }

  // General validators:
  static public <T> boolean notEmpty(T input) {
    if (input != null && !input.toString().isEmpty()) {
      return true;
    }
    return false;
  }

  // Returning error info:
  public String[] getMsg() {
    String[] arr = { usermsg, pasmsg, repasmsg };
    return arr;
  }

  public static Integer toInt(String strNum) {
    Integer i;
    if (strNum == null || strNum == "") {
      return 0;
    }
    try {
      i = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
      return null;
    }
    return i;
  }

  public static boolean regex(String string, String regex) {
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(string);
    if (!m.matches()) {
      return false;
    }

    return true;
  }
}
