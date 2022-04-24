package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import core.Grade;

public class ViewGradeController extends Controller {

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
}
