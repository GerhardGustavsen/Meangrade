package core;

import java.util.ArrayList;

import json.UserHandler;

public class ActiveUser extends User {

    private String password;

    private ArrayList<Grade> grades = new ArrayList<Grade>();

    public ActiveUser(String username, String passhash, String encryptedGrades, String pass) {
        super(username, passhash, encryptedGrades);

        password = pass;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);

        System.out.println("grades: " + grades);

        String data = "";
        for (Grade oneGrade : grades) {
            data = data + UserHandler.gradeToString(oneGrade);
            data = data + "&";

            System.out.println(oneGrade);
        }
        setEncryptedGrades(Encrypt.encrypt(data, password));
    }

    public void setGrades(ArrayList<Grade> g) {
        grades = g;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public String getPass() {
        return password;
    }
}
