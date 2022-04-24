package core;

public class Grade {
    
    private String code;
    private char grade;
    private int score;

    public Grade(String c, char g) {
        code = c;
        grade = g;
    }

    public String getCode() {
        return code;
    };

    public char getGrade() {
        return grade;
    };

    public String toString() {
        return getCode() + "\t\t\t\t\t\t\tGrade: " + getGrade();
    }
}
