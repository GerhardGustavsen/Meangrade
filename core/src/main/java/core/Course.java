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
        setResults(r);
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

    public void setResults(ArrayList<Integer> results) throws IllegalArgumentException{
        if(checkIfResultsAreValid(results)){
            this.results = results;
        }else{
            throw new IllegalArgumentException("All of the results must be an integer between 1-6");
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

    public void addScore(Integer score) throws IllegalArgumentException {
        if(checkIfScoreIsValid(score)){
            this.score.add(score);
        }else{
            throw new IllegalArgumentException("Score must be a number between 1-5");
        }

    };

    public boolean checkIfScoreIsValid(Integer score){
        return score <= 5 && score >= 1;
    }

    public boolean checkIfResultsAreValid(ArrayList<Integer> results){
        for(Integer result: results){
            if (result > 6 || result < 1){
                return false;
            }
        }
        return true;
    }
}