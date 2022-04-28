package core;

import java.util.ArrayList;

public class Grade {

    private String code;
    private char grade;
    private Integer score;
    private String comment;

    public Grade(String c, char g, Integer s, String com) {
        code = c;
        grade = g;
        score = s;
        comment = com;
        // update course score!
    }

    public String getCode() {
        return code;
    };

    public char getGrade() {
        return grade;
    };

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return getCode() + "\t\t\t\t\t\t\tGrade: " + getGrade();
    }

    public void setScore(Integer i) {
        score = i;
        // update course score!
    }

    public Course getCourse(Core core) {
        for (Course singleCourse : core.getCourses()) {
            if (singleCourse.getName() == code) {
                return singleCourse;
            }
        }
        // Throw error
        return new Course("XXX0000", "Course not found!", "404", new ArrayList<>());
    }
}
