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
        score = new ArrayList<>();
        setResults(r);
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
        if (numBetwean(score, 1, 5)) {
            this.score.add(score);
        } else {
            throw new IllegalArgumentException("Score must be a number between 1 and 5");
        }

    };

    public void addGrade(Integer grade) throws IllegalArgumentException {
        if (numBetwean(grade, 1, 6)) {
            this.results.add(grade);
        } else {
            throw new IllegalArgumentException("Grade must be a number between 1 and 6\nGrade was " + grade);
        }

    };

    public Double getAvrg() {
        return avrg(results);
    };

    public char getMean() {
        if (results != null && results.size() > 0) {
            int mean = results.get((int) (results.size() / 2));
            return Grade.toChar(mean);
        }
        return 'X';
    };

    public char getMode() {
        if (results != null && results.size() > 0) {
            Integer[] gradeList = { 0, 0, 0, 0, 0, 0, 0 };
            // - - - - - - - - - - -X -F -E -D -C -B -A
            for (Integer i : results) {
                gradeList[i]++;
            }

            int modeCount = 0;
            int mode = 0;
            for (int i = 0; i < 6; i++) {
                if (gradeList[i] > modeCount) {
                    modeCount = gradeList[i];
                    mode = i;
                }
            }

            return Grade.toChar(mode);
        }
        return 'X';
    };

    private static double avrg(ArrayList<Integer> list) {
        Double sum = 0.0;
        for (Integer i : list) {
            sum = sum + i;
        }

        return sum / list.size();
    }

    public boolean numBetwean(Integer num, Integer low, Integer high) {
        return num <= high && num >= low;
    }

    public boolean resultsAreValid(ArrayList<Integer> results) {
        for (Integer result : results) {
            if (result > 6 || result < 1) {
                return false;
            }
        }
        return true;

    }

    public ArrayList<Integer> getScoreArray() {
        return score;
    }

    public ArrayList<Integer> getGradeArray() {
        return results;
    }
}