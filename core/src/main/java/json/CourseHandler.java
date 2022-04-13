package json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import core.Course;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CourseHandler extends FileHandler {
  static String path = "src/main/resources/json/courses.json";


  public static void addCourse(Course course) throws IOException {
    try {
      FileWriter file = new FileWriter(path);
      JSONArray array = load(path);
      JSONArray newArray = new JSONArray();


      System.out.println(array);
      for (Object value : array) {
        JSONObject o = (JSONObject) value;
        System.out.println(o);
      }
      System.out.println(newArray.toJSONString());
      file.write(newArray.toJSONString());
      file.flush();
      file.close();
    }catch (IOException e){
      e.printStackTrace();
    }
  }


  public static boolean checkIfCourseExists(String code) throws IOException {
    JSONArray array = load(path);
    for (Object o: array){
      JSONObject jsonObject = (JSONObject) o;
      if (code.equals(jsonObject.get("code"))){
        return true;
      }
    };
    return false;
  }

  public static Course getCourseByCode(String code) throws IOException {
    JSONArray array = load(path);
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