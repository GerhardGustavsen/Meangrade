package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import core.Course;
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

  public static JSONObject courseToJson(Course course){
    JSONObject obj = new JSONObject();
    obj.put("name", course.getName());
    obj.put("description", course.getDesc());
    obj.put("code", course.getCode());
    obj.put("results", course.getRes());
    return obj;
  }

  public static void addCourse(Course course) throws IOException {

    try {
      FileWriter file = new FileWriter(path);
      JSONArray array = CourseFile.load();
      JSONArray newArray = new JSONArray();

      JSONObject obj = courseToJson(course);
      JSONObject obj2 = courseToJson(course);
      newArray.add(obj);
      newArray.add(obj2);
      System.out.println(array);
      for (Object value : array) {
        JSONObject o = (JSONObject) value;
        System.out.println(o);
        newArray.add(o);
      }
      System.out.println(newArray.toJSONString());
      file.write(newArray.toJSONString());
      file.flush();
      file.close();
    }catch (IOException e){
      e.printStackTrace();
    }
  }


  public static boolean checkIfCourseExists(String code) {
    JSONArray array = CourseFile.load();
    for (Object o: array){
      JSONObject jsonObject = (JSONObject) o;
      if (code.equals(jsonObject.get("code"))){
        return true;
      }
    };
    return false;
  }

  public static Course getCourseByCode(String code){
    JSONArray array = CourseFile.load();
    for (Object o: array){
      JSONObject jsonObject = (JSONObject) o;
      Object courseCode = jsonObject.get("code").toString();
      if (code.equals(courseCode)){
        return new Course(code, (String)jsonObject.get("name"), (String)jsonObject.get("description"), (ArrayList<Integer>) jsonObject.get("results"));
      }
    }
    return null;
  }
}