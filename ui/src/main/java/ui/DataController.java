package ui;

import core.Encrypt;
import core.Vault;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DataController {

  @FXML
  private TextArea datafield;

  @FXML
  private Label usernamelabel;

  @FXML
  private Button btsave;

  @FXML
  private Button btDelete;

  Vault app;
  String user;
  String password;

  @FXML
  void delete() {
    if (app.delete(user)) {
      System.out.println("Deleted user-data!");
      Stage stage = (Stage) datafield.getScene().getWindow();
      stage.close();
    } else {
      System.out.println("Culd not delete user-data!");
    }

  }

  @FXML
  void save() {
    String data = datafield.getText();
    if (data != null) {
      data = Encrypt.encrypt(data, password);
      if (app.addData(user, data)) {
      } else {
        System.out.println("Culd not save data!");
      }
    }
  }

  public void passdata(String name, String data, Vault v, String pass) {
    user = name;
    password = pass;
    app = v;
    usernamelabel.setText(name);
    if (data != null && data.length() > 0) {
      data = Encrypt.decrypt(data, password);
      datafield.setText(data);
    }
  }

}