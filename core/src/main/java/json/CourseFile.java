package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import core.Course;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CourseFile {

  static String path = "src/main/resources/json/courses.json";

    public static void save(JSONArray jarr) {
    FileWriter file = null;

    try {
      File f = new File(path);

      if (f.createNewFile()) {
        System.out.println("Created new file at " + path);
      }

      try {
        file = new FileWriter(path);
        file.write(jarr.toJSONString());

        System.out.println("Successfully Copied JSON Object to File.");
        System.out.println("\nJSON Object: " + jarr);
      } catch (IOException e) {
        System.out.println("Program failed to write to file!");
        e.printStackTrace();
      }
    } catch (IOException e) {
      System.out.println("Could not create file!");
      e.printStackTrace();
    } finally {

      try {
        file.flush();
        file.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static JSONArray load() {
    JSONArray obj = new JSONArray();

    try {
      File File = new File(path);
      System.out.println("hello");
      if (File.length() >= 0) {
        FileReader reader = new FileReader(File);
        JSONParser jsonParser = new JSONParser();
        obj = (JSONArray) jsonParser.parse(reader);
        System.out.println(obj);
      }

    } catch (org.json.simple.parser.ParseException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("File not found!");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return obj;
  }
}

