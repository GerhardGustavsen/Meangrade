package json;

import core.Course;
import core.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseHandler extends FileHandler {

  public CourseHandler(String path) {
    super(path);
  }

  // TODO: Add addResult; lets just add an integer result to a course;

  public void saveCourse(Course course) {
    write(courseToString(course));
  }

  public void saveCourses(ArrayList<Course> courses) throws IOException {
    deleteAll();
    for (Course course : courses) {
      write(courseToString(course));
    }
  }

  public Course getCourse(String code) throws FileNotFoundException {
    Scanner courseReader = read();
    while (courseReader.hasNextLine()) {
      String data = courseReader.nextLine();
      if (data.contains("Code: " + code)) {
        return stringToCourse(data);
      }
    }
    courseReader.close();
    return null;
  }

  public Course stringToCourse(String data) throws IllegalArgumentException {
    try {
      String[] parts = data.split("\\|");
      if (parts.length != 5) {
        throw new IllegalArgumentException("The data is not properly formated");
      }
      ArrayList<String> courseData = new ArrayList<>();
      for (String part : parts) {
        Matcher m = Pattern.compile(": (.*)").matcher(part);
        if (m.find()) {
          courseData.add(m.group(1));
        }
      }
      ArrayList<Integer> list = (resultsToArray(courseData.get(3)));
      ArrayList<Integer> list2 = (resultsToArray(courseData.get(4)));

      return new Course(courseData.get(0), courseData.get(1), courseData.get(2), list, list2);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("The data provided was wrong!");
    }
  }

  public ArrayList<Course> getAllCourses() throws FileNotFoundException {
    try {
      Scanner reader = read();
      ArrayList<Course> courses = new ArrayList<>();
      while (reader.hasNextLine()) {
        String data = reader.nextLine();
        courses.add(stringToCourse(data));
      }
      return courses;
    } catch (FileNotFoundException e) {
      System.out.println("Could not fetch the courses");
      e.printStackTrace();
    }
    return null;
  }

  public String courseToString(Course course) {
    return "Code: " + course.getCode() + " | Name: " + course.getName() + " | Description: " + course.getDesc()
        + " | Results: " + course.getRes().toString() + " | Scores: " + course.getScoreArray().toString();
  }

  public ArrayList<Integer> resultsToArray(String data) throws IllegalArgumentException {
    // Regular expression to digits
    try {
      String regex = "([0-9]+)";
      // Creating a pattern object
      Pattern pattern = Pattern.compile(regex);
      // Creating a Matcher object
      Matcher matcher = pattern.matcher(data);

      ArrayList<Integer> result = new ArrayList<>();
      while (matcher.find()) {
        Integer number = Integer.parseInt(matcher.group());
        result.add(number);
      }
      return result;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(
          "Wrong data input, the string has to have the following format [<int>, <int>, ...]");
    }

  }

}
