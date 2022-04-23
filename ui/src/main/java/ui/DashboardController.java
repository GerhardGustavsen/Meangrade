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
    Stage stage = (Stage) gradeList.getScene().getWindow();
    try {
      openFXML(stage, "NewCourse.fxml");
    } catch (CloneNotSupportedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void poppulateListView() {

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

  private ObservableList<Grade> gradeCollection = FXCollections.observableArrayList();

  public void initClickActions() {
    Stage stage = (Stage) gradeList.getScene().getWindow();

    // Detecting mouse clicked on ListView
    gradeList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent arg0) {
        System.out.println(gradeList.getSelectionModel().getSelectedItem().getCode());
        // Open gradeview :)
        // NEED TO SEND GRADE!
        try {
          openFXML(stage, "ViewGrade.fxml");
        } catch (CloneNotSupportedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ObservableList<Grade> loading = FXCollections.observableArrayList();
    loading.add(new Grade("Loading...", 'X'));

    Thread one = new Thread() {
      public void run() {
        try {
          while (core == null) {
            gradeList.setItems(loading);
            Thread.sleep(100);
          }
          poppulateListView();
          initClickActions();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    };

    one.start();
  }
}