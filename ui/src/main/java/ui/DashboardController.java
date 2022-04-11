package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.User;

public class DashboardController {


  User user;

  private void openGrades(ActionEvent event) {

    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Grades.fxml"));
      root = loader.load();
      GradesController controller = loader.getController();
      controller.passUser(user);


      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setTitle("Grades");
      stage.setScene(new Scene(root));
      stage.show();

    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }
  }

  private void openFXML(ActionEvent event, String fxmlPath) {

    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
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
  private void handleOpenGrades(ActionEvent event){
    openFXML(event, "Grades.fxml");
  }
  @FXML
  private void handleOpenCourse(ActionEvent event){
    openFXML(event, "Course.fmxl");
  }
  @FXML
  private void handleOpenNewCourse(ActionEvent event){
    openFXML(event, "CourseView.fxml");
  }


  public void passUser(User u) {
    user = u;
  }
}
