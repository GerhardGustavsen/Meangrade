package json;

import core.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class UserHandlerTest {
  String path = "src/test/resources/json/testFile.json";

  JSONArray data;


  public UserHandlerTest() throws IOException {
    data = UserHandler.load(path);
  }


  @Test
  @DisplayName("Can add user")
  public void canAddUser() throws IOException {
    Assertions.assertTrue(UserHandler.createUser("Thomas", "12345678A!", path, data ));
  }

  @Test
  @DisplayName("Can delete user")
  public void canDeleteUser() throws IOException {
    String name = "Thomas";
    Assertions.assertTrue(UserHandler.deleteUser(name, path, data));
  }

  @Test
  @DisplayName("Can add grades")
  public void canAddGrades() throws IOException {
    JSONArray grades = new JSONArray();
    JSONObject grade1 = new JSONObject();
    JSONObject grade2 = new JSONObject();
    JSONObject grade3 = new JSONObject();

    grade1.put("TDT4100", "5");
    grade2.put("TDT4100", "5");
    grade3.put("TDT4100", "5");
    grades.add(grade1);
    grades.add(grade2);
    grades.add(grade3);

    Assertions.assertTrue(UserHandler.addGrades("Thomas", grades.toJSONString(), path, data));
  }
}
