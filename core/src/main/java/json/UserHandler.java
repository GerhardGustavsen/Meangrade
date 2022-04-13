package json;


import core.Encrypt;
import core.Validator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserHandler extends FileHandler {


  public static boolean createUser(String user, String pas, String path, JSONArray data) throws IOException {
    data = load(path);
    Map<String, String> map = new HashMap<String, String>();
    map.put("UserName", user);
    map.put("PassHash", Encrypt.hash(pas));
    map.put("Grades", "");
    data.add(map);
    try{
      save(path, data);
      return true;
    }catch (IOException e){
      e.printStackTrace();
    }
    return false;
  }

  public static boolean deleteUser(String name, String path, JSONArray data) throws IOException {

    JSONArray userList = new JSONArray();
    boolean answer = false;
    if (data != null) {
      for (Object jsonUser : data) {
        JSONObject user = (JSONObject) jsonUser;
        String userName = user.get("UserName").toString();
        if (!name.equals(userName)) {
          userList.add(user);
        }
      }
      if (userList != data) {
        data = userList;
        save(path, data);
        answer = true;
      }
    }
    return answer;
  }

  public static boolean addGrades(String userName, String grades, String path, JSONArray data) throws IOException {
    JSONObject jsonUser = Validator.userexsist(userName, data);

    if (jsonUser != null) {
      deleteUser(userName, path, data);
      jsonUser.put("Grades", grades);
      data.add(jsonUser);
      save(path, data);
      return true;
    }
    return false;
  }

  public static JSONArray load() throws IOException {
    String path = "src/main/resources/json/user.json";
    createFile(path);
    JSONArray obj = new JSONArray();
    try {
      File file = new File(path);
      if (file.length() >= 0) {
        FileReader reader = new FileReader(file);
        JSONParser jsonParser = new JSONParser();
        obj = (JSONArray) jsonParser.parse(reader);
      }
    } catch (ParseException | IOException e) {
      e.printStackTrace();
    }
    return obj;
  }
}
