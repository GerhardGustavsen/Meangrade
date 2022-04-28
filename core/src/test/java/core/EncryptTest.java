package core;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
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
    this.pas = "123";
    this.data = "Hello";
    this.encryptedData = Encrypt.encrypt(data, pas);
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
    Assertions.assertNotEquals(this.data, this.encryptedData);
    String decryptedData = Encrypt.decrypt(this.encryptedData, this.pas);
    Assertions.assertEquals(this.data, decryptedData);
  }

  @Test
  @DisplayName("Cannot decrypt with wrong password")
  void cannotDecyptWithWrongPassword() {
    Assertions.assertThrows(EncryptionOperationNotPossibleException.class, () -> {
      Encrypt.decrypt(this.data, "wrong password");
    });
  }

  @Test
  @DisplayName("Cannot encrypt with empty password")
  void canEncryptWithEmptyPassword() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Encrypt.encrypt("Hello", "");
    });
  }
}
