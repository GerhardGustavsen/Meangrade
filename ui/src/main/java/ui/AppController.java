package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import core.Validator;
import core.Core;
import core.Encrypt;
import core.Grade;
import core.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppController implements Initializable {

  @FXML
  public TextField usernameTextField;

  @FXML
  public TextField passwordTextField;

  @FXML
  public TextField passwordRepeatField;

  @FXML
  private Button btreg;

  @FXML
  private Button btlog;

  @FXML
  public Label userstar;

  @FXML
  public Label passtar;

  @FXML
  public Label repasstar;

  @FXML
  private Label usermsg;

  @FXML
  private Label pasmsg;

  @FXML
  private Label repasmsg;

  @FXML
  private ImageView img;

  Core app;

  @FXML
  void handleRegister(ActionEvent e) {
    Validator validator = new Validator(usernameTextField.getText(), passwordTextField.getText(),
        passwordRepeatField.getText(), app.getdata());
    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

    if (validator.register()) {
      app.newprofile(usernameTextField.getText(), passwordTextField.getText());
      openDash(stage, "");
    }

    // Error messages:
    Boolean[] stars = validator.getstars();
    userstar.setVisible(stars[0]);
    passtar.setVisible(stars[1]);
    repasstar.setVisible(stars[2]);

    String[] arr = validator.getMsg();
    usermsg.setText(arr[0]);
    pasmsg.setText(arr[1]);
    repasmsg.setText(arr[2]);

  }

  @FXML
  void handleLoggInn(ActionEvent e) {
    Validator validator = new Validator(usernameTextField.getText(), passwordTextField.getText(),
        passwordRepeatField.getText(), app.getdata());
    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

    if (validator.logginn()) {
      String data = app.logginn(usernameTextField.getText(), passwordTextField.getText());
      if (data != null) {
        openDash(stage, data);
        clear();
      } else {
        usermsg.setText("Wrong username or password!");
      }
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    File imgFile = new File("src/main/resources/ui/img.png");

    if (imgFile.exists()) {
      Image image = new Image(imgFile.toURI().toString());
      img.setImage(image);
    } else {
      System.out.println("could not find lock img");
    }

    app = new Core();

    clear();
  }

  private void openDash(Stage stage, String data) {
    String user = usernameTextField.getText();
    String pass = passwordTextField.getText();

    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
      root = loader.load();

      DashboardController controller = loader.getController();
      controller.passUser(createUser(user, pass, data));

      stage.setScene(new Scene(root));
      stage.setTitle("User: " + user);
      stage.show();

    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }

  }

  private User createUser(String username, String password, String data) {
    ArrayList<Grade> grades = new ArrayList<Grade>();

    if (data != null && data.length() > 0) {
      String decryptedData = Encrypt.decrypt(data, password);

      String[] coursesText = decryptedData.split("\\|");
      // ADD A TRY!
      for (String str : coursesText) {
        String[] gradeText = str.split(",");
        Grade grade = new Grade(gradeText[0], gradeText[1].charAt(0), Integer.parseInt(gradeText[2]));
        grades.add(grade);
      }
    }

    return new User(username, password, grades);
  }

  private void clear() {
    userstar.setVisible(false);
    passtar.setVisible(false);
    repasstar.setVisible(false);

    usermsg.setText("");
    pasmsg.setText("");
    repasmsg.setText("");

    usernameTextField.setText("");
    passwordTextField.setText("");
    passwordRepeatField.setText("");
  }

}
