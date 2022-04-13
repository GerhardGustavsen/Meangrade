package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.Grade;
import core.User;

public class DashboardController {

  User user;
  private ObservableList<Grade> gradeCollection = FXCollections.observableArrayList();

  @FXML
  private ListView<Grade> gradeList;

  @FXML
  private void handleOpenGrades(ActionEvent event) {
    openFXML(event, "Grades.fxml");
  }

  @FXML
  private void handleOpenCourse(ActionEvent event) {
    openFXML(event, "Course.fxml");
  }

  @FXML
  private void handleOpenNewCourse(ActionEvent event) {
    openFXML(event, "CourseView.fxml");
  }

  public void passUser(User u) {
    user = u;
    poppulate();
  }

  public void poppulate() {

    // TESTING:
    Grade test1 = new Grade("TDT0000", 'A');
    gradeCollection.add(test1);

    // Add TRY???
    // Populating listview:
    gradeList.setItems(gradeCollection);
    for (Grade grade : user.getGrades()) {
      gradeCollection.add(grade);
    }

    initActions();
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

  public void initActions() {
    // Detecting mouse clicked on ListView
    gradeList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent arg0) {
        System.out.println(gradeList.getSelectionModel().getSelectedItem().getCode());
        // Open gradeview :)

      }
    });
  }
}
