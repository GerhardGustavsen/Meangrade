package json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

public class FileHandlerTest {


  private final String path = "src/test/resources/json/testFile.txt";
  FileHandler fileHandler = new FileHandler(this.path);

  @BeforeEach
  void init(){
    this.fileHandler.deleteAll();
  }

  @Test
  @DisplayName("Can read from file")
  void canReadFromFile() throws FileNotFoundException {
    this.fileHandler.write("Hello");
    Scanner scanner = this.fileHandler.read();
    Assertions.assertEquals("Hello", scanner.nextLine());
  }


  @Test
  @DisplayName("Can delete file content")
  void canDeleteFileContent() throws FileNotFoundException {
    this.fileHandler.write("Hello");
    Scanner scanner = this.fileHandler.read();
    Assertions.assertEquals("Hello", scanner.nextLine());
    this.fileHandler.deleteAll();
    Assertions.assertThrows(NoSuchElementException.class, () -> {
      this.fileHandler.read().nextLine();
    });
  }

  @Test
  @DisplayName("Can write to file")
  void canWriteToFile() throws FileNotFoundException {
    this.fileHandler.deleteAll();
    this.fileHandler.write("This is the second line");
    Scanner scanner = this.fileHandler.read();
    String data = scanner.nextLine();
    Assertions.assertEquals("This is the second line", data);
  }

  @Test
  @DisplayName("Can write multiple lines")
  void canWriteMultipleLines() throws FileNotFoundException {
    this.fileHandler.deleteAll();
    List<String> tests = Arrays.asList("First Line", "Second Line", "Third Line");
    this.fileHandler.write("First Line");
    this.fileHandler.write("Second Line");
    this.fileHandler.write("Third Line");
    Scanner scanner = this.fileHandler.read();
    ArrayList<String> lines = new ArrayList<>();
    while(scanner.hasNextLine()){
      String data = scanner.nextLine();
      lines.add(data);
    }
    int i = 0;
    for(String line: lines){
      Assertions.assertEquals(tests.get(i), line);
      i++;
    }
  }
}
