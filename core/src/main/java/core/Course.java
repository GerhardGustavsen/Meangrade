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
        return avrg(score);
    };

    public void addScore(Integer i) {
        score.add(i);
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
}