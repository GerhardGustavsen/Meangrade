package core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encrypt {

  public static String hash(String pas) {
    String generatedPas = null;
    try {
      // Using md5. (I know its not safe, but this is mostly a proof of consept and i
      // want to understand the technology)
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      // I saw online that people get the bytes of the pasword. guessing this is
      // becoude md5 is for binary.
      md5.update(pas.getBytes());
      byte[] bytes = md5.digest();
      // Convert it to hexadecimal format because its cool, and i also saw it online.
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      // Get complete hashed password:
      generatedPas = builder.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPas;
  }

  public static String encrypt(String uncrypted, String password) {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setPassword(password);
    return encryptor.encrypt(uncrypted);
  }

  public static String decrypt(String encrypted, String password) throws IllegalArgumentException {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
    encryptor.setPassword(password);
    String data = encryptor.decrypt(encrypted);
    if(data == null){
      throw new IllegalArgumentException("Wrong password");
    }else{
      return data;
    }
  }
}
