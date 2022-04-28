package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class CoreTest {




  public CoreTest() throws FileNotFoundException {
  }

  @Test
  @DisplayName("Can add new grade")
  public void canAddNewGrade(){
    String data = "";
    data.concat("hello");
    data.concat("hello");
    //System.out.println(data);
  }
}
