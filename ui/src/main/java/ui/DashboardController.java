package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class DashboardController extends Controller implements Initializable {

  @FXML
  private ListView<Grade> gradeList;

  @FXML
  private void handleOpenNewGrade(ActionEvent event) {
    openFXML(stage, "NewGrade.fxml");
  }

  @FXML
  private void handleOpenNewCourse(ActionEvent event) {
    openFXML(stage, "NewCourse.fxml");
  }

  private ObservableList<Grade> gradeCollection = FXCollections.observableArrayList();
  Stage stage = (Stage) gradeList.getScene().getWindow();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    poppulateListView();
    initClickActions();
  }

  public void poppulateListView() {

    // TESTING:
    Grade test1 = new Grade("TDT0000", 'A');
    gradeCollection.add(test1);

    // Add TRY???
    // Populating listview:
    gradeList.setItems(gradeCollection);
    for (Grade grade : core.getActiveUser().getGrades()) {
      gradeCollection.add(grade);
    }
  }

  public void initClickActions() {
    // Detecting mouse clicked on ListView
    gradeList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent arg0) {
        System.out.println(gradeList.getSelectionModel().getSelectedItem().getCode());
        // Open gradeview :)
        // NEED TO SEND GRADE!
        openFXML(stage, "ViewGrade.fxml");
      }
    });
  }

}
