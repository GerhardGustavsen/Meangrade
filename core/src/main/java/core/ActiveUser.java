package core;

import json.UserHandler;

import java.util.ArrayList;

public class ActiveUser extends User {

    private String password;

    private ArrayList<Grade> grades = new ArrayList<Grade>();

    private UserHandler userHandler = new UserHandler("core/src/main/resources/json/users.txt");

    public ActiveUser(String username, String passhash, String encryptedGrades, String pass) {
        super(username, passhash, encryptedGrades);
        password = pass;
    }

    public void addGrade(Grade grade){
        grades.add(grade);
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
