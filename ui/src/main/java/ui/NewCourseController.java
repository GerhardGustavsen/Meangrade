package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import core.Validator;

public class NewCourseController extends Controller {

  @FXML
  private TextField iCode;

  @FXML
  private TextField iName;

  @FXML
  private TextField iGrade;

  @FXML
  private TextField iNum;

  @FXML
  private TextArea iDesc;

  @FXML
  private Label errorMsg;

  private int grade;
  private int num;

  @FXML
  void handleOnSave(ActionEvent event) {

    if (checkEmpty() && checkInput()) {
      if (Validator.regex(iCode.getText(), "[A-Z]{3}\\d{4}")) {
        errorMsg.setText("Sucsess!");
        // SUCSESS!
        core.newCourse(iCode.getText(), iName.getText(), grade, num, iDesc.getText());
      } else {
        errorMsg.setText("Course code must be 3 capital chars and 4 numbers");
      }
    }
  }

  boolean checkEmpty() {
    if (!Validator.notEmpty(iCode.getText())) {
      errorMsg.setText("Course code cant be empty!");
      return false;
    } else if (!Validator.notEmpty(iName.getText())) {
      errorMsg.setText("Course name cant be empty!");
      return false;
    } else if (!Validator.notEmpty(iDesc.getText())) {
      errorMsg.setText("Course description cant be empty!");
      return false;
    }

    return true;
  }

  boolean checkInput() {

    if (Validator.toInt(iGrade.getText()) == null) {
      errorMsg.setText("Grade must be an int!");
      return false;
    } else {
      grade = Validator.toInt(iGrade.getText());
    }
    if (grade > 1 && grade <= 6) {
      errorMsg.setText("Grade must be between 1 and 6!");
      return false;
    } else if (Validator.toInt(iNum.getText()) == null) {
      errorMsg.setText("Participant number must be an int!");
      return false;
    } else {
      num = Validator.toInt(iNum.getText());
    }

    return true;
  }

  @FXML
  void handleOpenDashboard(ActionEvent event) {
    DashboardController dash = new DashboardController();
    openFXML(dash, "Dashboard.fxml");
    dash.poppulateListView();
    dash.initClickActions();
  }
}
