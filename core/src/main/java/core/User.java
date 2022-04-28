package core;

public class User {
    private final String username;
    private final String passHash;
    private String encryptedGrades;

    public User(String username, String password, String grades) throws IllegalArgumentException{
        if(username != null && password != null){
            this.username = username;
            this.passHash = password;
            encryptedGrades = grades;
        }else{
            throw new IllegalArgumentException("Neither username nor passwordHash can be empty");
        }
    }

    public String getName() {
        return this.username;
    }

    public String getPassHash() {
        return this.passHash;
    }

    public String getEncryptedGrades() {
        return this.encryptedGrades;
    }

    public void setEncryptedGrades(String s) {
        this.encryptedGrades = s;
    }
}
