package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

  String username;
  String password;
  String repassword;

  JSONArray data;
  Validator validator;

  String usermsg = "";
  String pasmsg = "";
  String repasmsg = "";

  @BeforeEach
  public void setup() {
    validator = new Validator();
    data = new JSONArray();
  }

  @Test
  @DisplayName("Testing setdata")
  public void TestSetData() {
    validator.setData("John", "Password123@", "Password123@", data);
    String[] stringArray = (String[]) data.toArray(new String[data.size()]);
    String[] arr = { "John", "Password123@", "Password123@", stringArray.toString() };

    assertEquals(validator.getData(), arr);

  }

  @Test
  @DisplayName("Testing username")
  public void TestUsername() {
    assertEquals(validator.vusername(), true);
    validator.setData("John", "Password123@", "Password123@", data);
    assertEquals(validator.vusername(), true);

  }

  @Test
  @DisplayName("Testing if user already exists")
  public void TestFindUser() {
    validator.setData("John", "Password123@", "Password123@", data);
    assertEquals(Validator.userexsist("David Guetta", data), null);
    assertNotNull(Validator.userexsist("John", data));
  }

  @Test
  @DisplayName("Testing password")
  public void TestVpas() {
    assertEquals(validator.vpas(), false);

    this.password = "Passwo";
    assertEquals(validator.vpas(), false);

    this.password = "PasswordVeryLongwithALotOfCharacters";
    assertEquals(validator.vpas(), false);

    this.password = "Password";
    assertEquals(validator.vpas(), false);

    this.password = "password";
    assertEquals(validator.vpas(), false);

    this.password = "PASSWORD";
    assertEquals(validator.vpas(), false);

    this.password = "Password123";
    assertEquals(validator.vpas(), false);

    this.password = "Password123@";
    assertEquals(validator.vpas(), true);

  }

  @Test
  @DisplayName("Testing repeated password")
  public void TestVrepas() {

    assertEquals(validator.vrepas(), false);

    this.password = "Password123@";
    this.repassword = "Password123";
    assertEquals(validator.vrepas(), false);

    this.repassword = "Password123@";
    assertEquals(validator.vrepas(), true);
  }

}
