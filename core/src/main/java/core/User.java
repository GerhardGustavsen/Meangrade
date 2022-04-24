package core;

public class User {
    private String username;
    private String passHash;
    private String encryptedGrades;

    public User(String u, String p, String g) {
        username = u;
        passHash = p;
        encryptedGrades = g;
    }

    public String getName() {
        return username;
    }

    public String getPassHash() {
        return passHash;
    }

    public String getEncryptedGrades() {
        return encryptedGrades;
    }
}
