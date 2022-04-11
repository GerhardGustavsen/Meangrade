package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

import core.Grade;
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

    // Add TRY???
    ObservableList<String> gradeCollection = FXCollections.observableArrayList();
    for (Grade grade : user.getGrades()) {
      String listString = "- " + grade.getCode() + "\t\t\t" + grade.getGrade();
      gradeCollection.add(listString);
    }

    ListView<String> listView = new ListView<String>(gradeCollection);

  }

  @FXML
  private void handleOpenGrades(ActionEvent event) {
    openGrades(event);
  }

  public void passUser(User u) {
    user = u;
  }
}
