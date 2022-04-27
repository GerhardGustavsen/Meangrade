package core;

import java.util.ArrayList;

public class Course {
    private String code;
    private String name;
    private String description;
    private ArrayList<Integer> score;
    private ArrayList<Integer> results;

    public Course(String c, String n, String d, ArrayList<Integer> r) {
        setCode(c.trim());
        name = n.trim();
        description = d;
        results = r;
        score = new ArrayList<>();
    }

    public boolean codeIsValid(String code){
        return Validator.regex(code, "[A-Z]{3}\\d{4}");
    }

    public void setCode(String code) {
        if (codeIsValid(code)){
            this.code = code;
        }else{
            throw new IllegalArgumentException("Code format is invalid");
        }
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