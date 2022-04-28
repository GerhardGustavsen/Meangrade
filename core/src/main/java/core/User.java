package core;

public class User {
    private final String username;
    private final String passHash;
    private String encryptedGrades;

    public User(String u, String p, String g) throws IllegalArgumentException{
        if(u != null && p != null){
            username = u;
            passHash = p;
            encryptedGrades = g;
        }else{
            throw new IllegalArgumentException("Neither username nor passwordHash can be empty");
        }
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

    public void setEncryptedGrades(String s) {
        encryptedGrades = s;
    }
}
