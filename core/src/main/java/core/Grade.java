package core;

public class Grade {
    private String code;
    private char grade;
    private int score;

    public Grade(String c, char g, int s) {
        code = c;
        grade = g;
        score = s;
    }

    public String getCode() {
        return code;
    };

    public char getGrade() {
        return grade;
    };

    public int getScore() {
        return score;
    };
}
