package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import core.Grade;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewGradeController extends Controller implements Initializable {

  Grade grade;

  @FXML
  public Label titleLabel;

  @FXML
  public Label descriptionLabel;

  @FXML
  public Label medianLabel;

  @FXML
  public Label avarageLabel;

  @FXML
  public Label modeLabel;

  @FXML
  void handleOpenDashboard(ActionEvent event) {
    DashboardController dash = new DashboardController();
    openFXML(dash, "Dashboard.fxml");
    dash.poppulateListView();
    dash.initClickActions();
  }

  void SendGrade(Grade g) {
    grade = g;
  }

  void populateView(){
    titleLabel.setText(grade.getCode());
    avarageLabel.setText(String.valueOf(grade.getScore()));
  }



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    populateView();
  }
}
