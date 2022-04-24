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

public class DashboardController extends Controller {

  private ObservableList<Grade> gradeCollection = FXCollections.observableArrayList();
  @FXML
  private ListView<Grade> gradeList;

  @FXML
  private void handleOpenNewCourse(ActionEvent event) {
    NewCourseController newCourse = new NewCourseController();
    openFXML(newCourse, "NewCourse.fxml");
  }

  @FXML
  private void handleOpenNewGrade(ActionEvent event) {
    NewGradeController newGrade = new NewGradeController();
    openFXML(newGrade, "NewGrade.fxml");
  }

  @FXML
  void handleLoggOut(ActionEvent event) {
    LogginController loggin = new LogginController();
    core.loggOut();
    openFXML(loggin, "Loggin.fxml");
  }

  void poppulateListView() {

    // TESTING:
    Grade test1 = new Grade("TDT0000", 'A', 8, "Hello");
    gradeCollection.add(test1);

    // Add TRY???
    // Populating listview:

    for (Grade grade : core.getActiveUser().getGrades()) {
      gradeCollection.add(grade);
    }

    gradeList.setItems(gradeCollection);
  }

  public void initClickActions() {
    // Detecting mouse clicked on ListView
    gradeList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent arg0) {
        System.out.println(gradeList.getSelectionModel().getSelectedItem().getCode());
        // Open gradeview :)
        OpenGrade(gradeList.getSelectionModel().getSelectedItem());
      }
    });
  }

  private void OpenGrade(Grade g) {
    ViewGradeController viewGrade = new ViewGradeController();
    viewGrade.SendGrade(g);
    openFXML(viewGrade, "ViewGrade.fxml");
  }

  /*
   * @Override
   * public void initialize(URL location, ResourceBundle resources) {
   * ObservableList<Grade> loading = FXCollections.observableArrayList();
   * loading.add(new Grade("Loading...", 'X'));
   * 
   * Thread one = new Thread() {
   * public void run() {
   * try {
   * while (core == null) {
   * gradeList.setItems(loading);
   * Thread.sleep(100);
   * }
   * 
   * } catch (InterruptedException e) {
   * // TODO Auto-generated catch block
   * e.printStackTrace();
   * }
   * }
   * };
   * 
   * one.start();
   * }
   */

}