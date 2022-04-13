package json;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileHandlerTest {

  String path = "src/test/resources/json/testFile.json";
  String createPath = "src/test/resources/json/createFile.json";

  @Test
  @DisplayName("Loads json file")
  public void canLoadJsonFile() throws IOException {
    JSONArray obj = FileHandler.load(path);
    System.out.println(obj);
    Assertions.assertNotNull(obj);
  }

  @Test
  @DisplayName("Can create file")
  public void canCreateFile() throws IOException {
    Assertions.assertTrue(FileHandler.createFile(createPath));
  }

  @Test
  @DisplayName("Can save file")
  public void canSaveFile() throws IOException {
    JSONArray jsonArray = new JSONArray();
    Assertions.assertTrue(FileHandler.save(createPath, jsonArray ));
  }
}
