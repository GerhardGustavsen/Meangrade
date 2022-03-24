package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import core.Validator;
import core.Vault;
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
  public TextField username;

  @FXML
  public TextField pas;

  @FXML
  public TextField repas;

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

  Vault app;

  @FXML
  void handleRegister(ActionEvent e) {
    Validator validator = new Validator(username.getText(), pas.getText(), repas.getText(), app.getdata());
    // validator.setData(username.getText(), pas.getText(), repas.getText(),
    // app.getdata());

    if (validator.register()) {
      app.newprofile(username.getText(), pas.getText());
      openDataForm("", e);
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
    Validator validator = new Validator(username.getText(), pas.getText(), repas.getText(), app.getdata());
    // validator.setData(username.getText(), pas.getText(), repas.getText(),
    // app.getdata());

    if (validator.logginn()) {
      String data = app.logginn(username.getText(), pas.getText());
      if (data != null) {
        openDataForm(data, e);
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

    app = new Vault();

    clear();
  }

  private void openDataForm(String data, ActionEvent event) {
    String user = username.getText();
    String pass = pas.getText();

    Parent root;
    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("Grades.fxml"));
      root = loader.load();

      // GradesController controller = loader.getController();
      // controller.passdata(user, data, app, pass);

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.setTitle("Vault - " + user);
      stage.show();

    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }

  }

  private void clear() {
    userstar.setVisible(false);
    passtar.setVisible(false);
    repasstar.setVisible(false);

    usermsg.setText("");
    pasmsg.setText("");
    repasmsg.setText("");

    username.setText("");
    pas.setText("");
    repas.setText("");
  }

}
