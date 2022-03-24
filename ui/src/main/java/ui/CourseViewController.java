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

public class CourseViewController {

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

  private void openDashboard(ActionEvent event) {
    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
      root = loader.load();

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setTitle("Grades");
      stage.setScene(new Scene(root));
      stage.show();

    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }
  }

  @FXML
  void handleOpenDashboard(ActionEvent event) {
    openDashboard(event);
  }

  public void initialize() {

  }
}
