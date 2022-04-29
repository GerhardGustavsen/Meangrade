package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import core.Course;
import core.Grade;

public class ViewGradeController extends Controller {

  Grade grade;
  Course course;

  @FXML
  private Label courseLabel;

  @FXML
  private Label gradeLabel;

  @FXML
  private Label avrgGrade;

  @FXML
  private Label meanGrade;

  @FXML
  private Label modeGrade;

  @FXML
  private Label courseScore;

  @FXML
  private Label yourScore;

  @FXML
  private Label description;

  @FXML
  private Label notes;

  @FXML
  private void handleOpenDashboard(ActionEvent event) {
    DashboardController dash = new DashboardController();
    openFXML(dash, "Dashboard.fxml");
    // dash.poppulateListView();
    // dash.initClickActions();
  }

  @FXML
  void handleDelete(ActionEvent event) throws IOException {
    core.removeGrade(grade.getCode());
    handleOpenDashboard(event);
  }

  public void SendGrade(Grade g) {
    grade = g;
  }

  @Override
  void Poppulate() {
    courseLabel.setText(grade.getCode() + ":");
    gradeLabel.setText(String.valueOf(grade.getGrade()));

    course = grade.getCourse(core);

    avrgGrade.setText(String.valueOf(course.getAvrg()));
    meanGrade.setText(String.valueOf(course.getMean()));
    modeGrade.setText(String.valueOf(course.getMode()));

    courseScore.setText(Course.toStars((int) course.getScore()));
    yourScore.setText(Course.toStars(grade.getScore()));

    description.setText(course.getDesc());
    notes.setText(grade.getComment());
  }
}
