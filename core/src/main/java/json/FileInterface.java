package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface FileInterface {
  public void createNewFile();

  public void write(String string);

  public File getFile();

  public Scanner read() throws FileNotFoundException;

  public void deleteAll();

  }
