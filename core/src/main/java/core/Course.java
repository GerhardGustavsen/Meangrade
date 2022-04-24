package core;

import java.util.ArrayList;

public class Course {
    private String code;
    private String name;
    private String description;
    private ArrayList<Integer> score;
    private ArrayList<Integer> results;

    public Course(String c, String n, String d, ArrayList<Integer> r) {
        code = c;
        name = n;
        description = d;
        results = r;
    }

    public String getCode() {
        return code;
    };

    public String getName() {
        return name;
    };

    public String getDesc() {
        return description;
    };

    public ArrayList<Integer> getRes() {
        return results;
    };

    public double getScore() {
        Double sum = 0.0;
        for (Integer i : score) {
            sum = sum + i;
        }

        return sum / score.size();
    };

    public void addScore(Integer i) {
        score.add(i);
    };
}