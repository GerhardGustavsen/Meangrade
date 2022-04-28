package core;

import java.util.ArrayList;

import json.UserHandler;

public class ActiveUser extends User {

    private final String password;

    private ArrayList<Grade> grades = new ArrayList<Grade>();

    public ActiveUser(String username, String passhash, String encryptedGrades, String pass) throws IllegalArgumentException{
        super(username, passhash, encryptedGrades);
        Validator validator = new Validator("", pass, "");
        if(validator.vpas()){
            password = pass;
        }else{
            throw new IllegalArgumentException("Wrong password format");
        }
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
