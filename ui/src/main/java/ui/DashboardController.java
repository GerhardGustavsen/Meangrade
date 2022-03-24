package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

  private void openGrades(ActionEvent event) {
    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Grades.fxml"));
      root = loader.load();

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setTitle("Grades");
      stage.setScene(new Scene(root));
      stage.show();

    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }}

  @FXML
  private void handleOpenGrades(ActionEvent event){
    openGrades(event);
  }

  public void initialize(){

  }
}
