package ui;

import core.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import core.Grade;
import javafx.scene.paint.Paint;
import json.CourseHandler;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewGradeController extends Controller implements Initializable {

  Grade grade;

  Course course;

  @FXML
  public Label titleLabel;

  @FXML
  public Label descriptionLabel;

  @FXML
  public Label scoreLabel;

  @FXML
  public Label avarageLabel;

  @FXML
  public Label modeLabel;

  @FXML Label errorMsg;

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

  void populateView(){
    titleLabel.setText(course.getCode() + ": " + course.getName());
    descriptionLabel.setText(course.getDesc());
    avarageLabel.setText(String.valueOf(grade.getScore()));
    if(course.getScore() == 0){
      scoreLabel.setText("No data");
    }else{
      scoreLabel.setText(String.valueOf(course.getScore()));
    }
  }



  @Override
  public void initialize(URL location, ResourceBundle resources) {
    course = core.getCourse(grade.getCode());
    if(course == null){
      errorMsg.setText("Could not get all the information");
      errorMsg.setTextFill(Paint.valueOf("red"));
    }else{
      populateView();
    }
  }
}
