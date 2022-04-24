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

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogginController extends Controller {

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

  @FXML
  void handleBtnReg(ActionEvent e) throws JSONException {
    clear();
    Validator validator = new Validator(usernameTextField.getText(), passwordTextField.getText(),
        passwordRepeatField.getText());

    if (core.getuser(usernameTextField.getText()) == null) {
      if (validator.register()) {
        // Creates the new profile before logginn:
        core.newProfile(usernameTextField.getText(), passwordTextField.getText());
        core.logginn(usernameTextField.getText(), passwordTextField.getText());
        openDash();
      } else {
        show(validator);
      }
    } else {
      usermsg.setText("Username is taken!");
    }
  }

  @FXML
  void handleBtnLog(ActionEvent e) throws JSONException {
    clear();
    Validator validator = new Validator(usernameTextField.getText(), passwordTextField.getText(),
        passwordRepeatField.getText());

    if (validator.logginn()) {
      if (core.logginn(usernameTextField.getText(), passwordTextField.getText())) {
        openDash();
      } else {
        usermsg.setText("Username or passowrd wrong!");
      }
    } else {
      show(validator);
    }
  }

  void openDash() {
    DashboardController dash = new DashboardController();
    dash.sendCore(core);

    openFXML(dash, "Dashboard.fxml");

    dash.poppulateListView();
    dash.initClickActions();
  }

  void clear() {
    // Error msg:
    usermsg.setText("");
    pasmsg.setText("");
    repasmsg.setText("");
    // Error stars:
    userstar.setVisible(false);
    passtar.setVisible(false);
    repasstar.setVisible(false);
  }

  void show(Validator v) {
    // Error messages:
    String[] msgArr = v.getMsg();
    usermsg.setText(msgArr[0]);
    pasmsg.setText(msgArr[1]);
    repasmsg.setText(msgArr[2]);
    // Error stars:
    userstar.setVisible(usermsg.getText() != "");
    passtar.setVisible(pasmsg.getText() != "");
    repasstar.setVisible(repasmsg.getText() != "");
  }
}
