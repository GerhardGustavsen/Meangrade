package json;

import java.io.*;
import java.util.Scanner;

public class FileHandler {

  private String path;

  public FileHandler(String path){
    this.path = path;
  }

  public void createNewFile() {
    try {
      File courseFile = new File(path);
      if (courseFile.createNewFile()) {
        System.out.println("The file was created: " + courseFile.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("We were not able to create a new file.");
      e.printStackTrace();
    }
  }

  public void write(String string) {
    createNewFile();
    try{
      FileWriter courseWriter = new FileWriter(path, true);
      BufferedWriter out = new BufferedWriter(courseWriter);
      courseWriter.write(string);
      out.newLine();
      out.close();
      System.out.println("Successfully wrote to the file.");
    } catch(IOException e){
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public Scanner read() throws FileNotFoundException {
    createNewFile();
    File file = new File(path);
    return new Scanner(file);
  }


}
