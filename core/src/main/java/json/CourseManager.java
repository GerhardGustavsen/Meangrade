package json;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CourseManager {
  static String path = "src/main/resources/json/courses.json";


  public static void read() {
    // JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader(path)) {
      // Read JSON file
      Object obj = jsonParser.parse(reader);

      JSONArray courseList = (JSONArray) obj;

      // Iterate over employee array
      courseList.forEach(course -> parseCourseObject((JSONObject) course));

    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  private static void parseCourseObject(JSONObject course){
    String id = (String) course.get("name");
    System.out.println(id);

    // Get user first name
    String firstName = (String) course.get("code");
    System.out.println(firstName);

    // Get user last name
    String lastName = (String) course.get("description");
    System.out.println(lastName);
  }

}
