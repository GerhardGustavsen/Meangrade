package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
