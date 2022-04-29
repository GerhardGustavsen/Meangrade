package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

  private String username;
  private String password;
  private String repassword;

  private String usermsg = "";
  private String pasmsg = "";
  private String repasmsg = "";

  public Validator(String username, String password, String repassword) {
    this.username = username;
    this.password = password;
    this.repassword = repassword;
  }

  public boolean register() {
    return (vUserName() & vpas() & vrepas());
  }

  public boolean logginn() {
    return (vUserName() & vpas());
  }

  public boolean vpas() {
    if (notEmpty(this.password)) {

      String upperCaseChars = "(.*[A-Z].*)";
      String lowerCaseChars = "(.*[a-z].*)";
      String numbers = "(.*[0-9].*)";
      String specialChars = "(.*[@,#,$,%,!].*$)";

      if (this.password.length() > 15 || this.password.length() < 8) {
        this.pasmsg = "Password must be less than 20 and more than 8 characters in length.";
        return false;
      } else if (!this.password.matches(upperCaseChars)) {
        this.pasmsg = "Password must have atleast one uppercase character";
        return false;
      } else if (!this.password.matches(lowerCaseChars)) {
        this.pasmsg = "Password must have atleast one lowercase character";
        return false;
      } else if (!this.password.matches(numbers)) {
        this.pasmsg = "Password must have atleast one number";
        return false;
      } else if (!this.password.matches(specialChars)) {
        this.pasmsg = "Password must have atleast one special character among @#$%!";
        return false;
      }

      return true;
    }
    this.pasmsg = "Password can't be empty!";
    return false;
  }

  public boolean vrepas() {
    if (notEmpty(this.repassword)) {
      if (!this.repassword.equals(this.password)) {
        this.repasmsg = "Password does not mach!";
        return false;
      }
      return true;
    }
    this.repasmsg = "Password must be repeated!";
    return false;
  }

  public boolean vUserName() {
    if (notEmpty(this.username)) {
      return true;
    }
    this.usermsg = "Username can't be empty!";
    return false;
  }

  // General validators:
  static public <T> boolean notEmpty(T input) {
    return input != null && !input.toString().isEmpty();
  }

  // Returning error info:
  public String[] getMsg() {
    String[] arr = { usermsg, pasmsg, repasmsg };
    return arr;
  }

  public static Integer toInt(String strNum) {
    int i;
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
    return m.matches();
  }
}
