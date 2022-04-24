package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCourseController extends Controller {

  @FXML
  void handleOpenDashboard(ActionEvent event) {
    DashboardController dash = new DashboardController();
    openFXML(dash, "Dashboard.fxml");
  }
}
