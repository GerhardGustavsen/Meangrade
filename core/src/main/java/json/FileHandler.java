package json;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class FileHandler {

  public static JSONArray load(String path) throws IOException {
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


  static boolean createFile(String path) throws IOException {
    try {
      File f = new File(path);
      if (f.createNewFile()) {
        try{
          FileWriter fileWriter = new FileWriter(path);
          fileWriter.write("[]");
          fileWriter.flush();
          fileWriter.close();
          return true;
        }catch (IOException e){
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean save(String path, JSONArray jarr) throws IOException {
    createFile(path);

    try{
      FileWriter fileWriter = new FileWriter(path);
      fileWriter.write(jarr.toJSONString());
      System.out.println(jarr.toJSONString());
      fileWriter.flush();
      fileWriter.close();
      return true;
    }catch (IOException e){
      e.printStackTrace();
      return false;
    }
  }
}
