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

    public boolean codeIsValid(String code) {
        return Validator.regex(code, "[A-Z]{3}\\d{4}");
    }

    public void setCode(String code) {
        if (codeIsValid(code)) {
            this.code = code;
        } else {
            throw new IllegalArgumentException("Code format is invalid");
        }
    }

    public void setResults(ArrayList<Integer> results) throws IllegalArgumentException {
        if (resultsAreValid(results)) {
            this.results = results;
        } else {
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
        return avrg(score);
    };

    public void addScore(Integer score) throws IllegalArgumentException {
        if (scoreIsValid(score)) {
            this.score.add(score);
        } else {
            throw new IllegalArgumentException("Score must be a number between 1-5");
        }

    };

    public Double getAvrg() {
        return avrg(results);
    };

    public char getMean() {
        int mean = results.get((int) (results.size() / 2));

        return toChar(mean);
    };

    public char getMode() {
        Integer[] gradeList = { 0, 0, 0, 0, 0, 0 };
        // - - - - - - - - - - -F -E -D -C -B -A
        for (Integer i : results) {
            gradeList[i - 1]++;
        }

        int modeCount = 0;
        int mode = 0;
        for (int i = 0; i < 6; i++) {
            if (gradeList[i] > modeCount) {
                modeCount = gradeList[i];
                mode = i;
            }
        }

        return toChar(mode);
    };

    private static double avrg(ArrayList<Integer> list) {
        Double sum = 0.0;
        for (Integer i : list) {
            sum = sum + i;
        }

        return sum / list.size();
    }

    private static char toChar(int i) {
        int a = 65;
        return (char) (a + i);
    }

    public boolean scoreIsValid(Integer score) {
        return score <= 5 && score >= 1;
    }

    public boolean resultsAreValid(ArrayList<Integer> results) {
        for (Integer result : results) {
            if (result > 6 || result < 1) {
                return false;
            }
        }
        return true;

    }
}