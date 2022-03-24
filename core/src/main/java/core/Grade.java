package core;

public class Grade {
    private String code;
    private char grade;
    private int score;
    private String comment;

    public Grade(String c, char g, int s, String t) {
        code = c;
        grade = g;
        score = s;
        comment = t;
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

    public String getComment() {
        return comment;
    };
}
