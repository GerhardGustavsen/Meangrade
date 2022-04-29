package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface FileInterface {
  public abstract void createNewFile();

  public abstract void write(String string);

  public abstract File getFile();

  public abstract Scanner read() throws FileNotFoundException;

  public abstract void deleteAll();

}
