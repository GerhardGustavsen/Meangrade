package json;

import core.Course;
import core.Encrypt;
import core.Grade;
import core.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHandler extends FileHandler {
  public UserHandler() {
    super("src/main/resources/json/users.txt");
  }

  public void saveUser(User user){
    ArrayList<Grade> empty = new ArrayList<>();
    write(userToString(user));
  }

  public void deleteUser(){

  }
  //TODO: Get all users



  public ArrayList<User> getAllUsers(){
      try{
        Scanner reader = read();
        ArrayList<User> users = new ArrayList<>();
        while(reader.hasNextLine()){
          String data = reader.nextLine();
          users.add(stringToUser(data));
        }
        return users;
      }catch (FileNotFoundException e){
        System.out.println("Could not fetch the courses");
        e.printStackTrace();
      }
      return null;
  }

  public User stringToUser(String data){
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

  public  String userToString(User user){
    return "Username: " + user.getName() + " | HashPassword: " + user.getPassHash() + " | Grades: " + user.getEncryptedGrades();
  }
}

