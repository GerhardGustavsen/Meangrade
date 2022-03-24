package core;

import java.util.ArrayList;

public class User {
    private String user;
    private String password;

    private ArrayList<Grade> grades = new ArrayList<Grade>();

    public User(String u, String p, ArrayList<Grade> g) {
        user = u;
        password = p;
        grades = g;
    }

}
