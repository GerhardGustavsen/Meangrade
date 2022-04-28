package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EncryptTest {

  String pas;
  String data;
  String encryptedData;

  @BeforeEach
  void init() {
    String pas = "123";
    String data = "Hello";
    String encryptedData = Encrypt.encrypt(data, pas);
  }

  @Test
  @DisplayName("Test that hashing the same string returns same hash")
  void testHashingTwoStringWithSameResult() {
    String beforeHash = "testString";
    String afterHash = Encrypt.hash(beforeHash);
    Assertions.assertNotEquals(beforeHash, afterHash);
    String beforeHash1 = "testString";
    String afterHash2 = Encrypt.hash(beforeHash1);
    Assertions.assertEquals(afterHash2, afterHash);
  }

  @Test
  @DisplayName("Can encrypt and decrypt string")
  void canDecryptSring() {
    Assertions.assertNotEquals(data, encryptedData);
    String decryptedData = Encrypt.decrypt(encryptedData, pas);
    Assertions.assertEquals(data, decryptedData);
  }

  @Test
  @DisplayName("Cannot decrypt with wrong password")
  void cannotDecyptWithWrongPassword() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Encrypt.decrypt(data, "wrong password");
    });
  }

  @Test
  @DisplayName("Cannot encrypt with empty password")
  void canEncryptWithEmptyPassword() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      String data = Encrypt.encrypt("Hello", "");
      // System.out.println(data);
    });
  }
}
