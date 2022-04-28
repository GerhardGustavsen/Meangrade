package json;

import java.io.*;
import java.util.Scanner;

public class FileHandler implements FileInterface {

  private String path;

  public FileHandler(String path) {
    this.path = path;
  }

  public void createNewFile() {
    try {
      File courseFile = new File(this.path);
      if (courseFile.createNewFile()) {
        System.out.println("The file was created: " + courseFile.getName());
      }
    } catch (IOException e) {
      System.out.println("We were not able to create a new file.");
      e.printStackTrace();
    }
  }

  public void write(String string) {
    createNewFile();
    try {
      FileWriter courseWriter = new FileWriter(this.path, true);
      BufferedWriter out = new BufferedWriter(courseWriter);
      courseWriter.write(string);
      out.newLine();
      out.close();
    } catch (IOException e) {
      //System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public File getFile(){
    return new File(this.path);
  }

  public Scanner read() throws FileNotFoundException {
    createNewFile();
    File file = new File(this.path);
    return new Scanner(file);
  }

  public void deleteAll(){
    createNewFile();
    try{
      new FileWriter(this.path, false).close();
    }catch (IOException e) {
      //System.out.println("Could not delete file content");
      e.printStackTrace();
    }
  }
}