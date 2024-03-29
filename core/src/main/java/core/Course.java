package core;

import java.util.ArrayList;

public class Course {
    private String code;
    private String name;
    private final String description;
    private ArrayList<Integer> score;
    private ArrayList<Integer> results;

    public Course(String code, String name, String description, ArrayList<Integer> results, ArrayList<Integer> score) {
        setCode(code.trim());
        this.name = name.trim();
        this.description = description;
        this.score = score;
        setResults(results);
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
        return this.code;
    };

    public String getName() {
        return this.name;
    };

    public String getDesc() {
        return this.description;
    };

    public ArrayList<Integer> getRes() {
        return this.results;
    };

    public double getScore() {
        return avrg(score);
    };

    public void addScore(int score) throws IllegalArgumentException {
        if (numBetwean(score, 1, 5)) {
            this.score.add(score);
        } else {
            throw new IllegalArgumentException("Score must be a number between 1 and 5");
        }

    };

    public void addGrade(int grade) throws IllegalArgumentException {
        if (numBetwean(grade, 1, 6)) {
            this.results.add(grade);
        } else {
            throw new IllegalArgumentException("Grade must be a number between 1 and 6\nGrade was " + grade);
        }

    };

    public String getAvrg() {
        double avrg = avrg(results);
        // System.out.println("Avrage grade: " + avrg);
        char grade = Grade.toChar((int) avrg);
        int rest = (int) Math.round((avrg - (int) avrg) * 100);
        return String.valueOf(grade) + "." + rest;
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
            int[] gradeList = { 0, 0, 0, 0, 0, 0, 0 };
            // - - - - - - - - - - -X -F -E -D -C -B -A
            for (int i : results) {
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
        for (int i : list) {
            sum = sum + i;
        }
        // System.out.println(sum);
        // System.out.println(list.size());
        return sum / list.size();
    }

    public boolean numBetwean(int num, int low, int high) {
        return num <= high && num >= low;
    }

    public boolean resultsAreValid(ArrayList<Integer> results) {
        for (int result : results) {
            if (result > 6 || result < 1) {
                return false;
            }
        }
        return true;

    }

    public ArrayList<Integer> getScoreArray() {
        return this.score;
    }

    public static String toStars(int stars) {
        if (stars == 0) {
            return "No rating";
        }
        return "★".repeat(stars) + "☆".repeat(5 - stars);
    }
}