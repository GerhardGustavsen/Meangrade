package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class GradesController {

  @FXML
  private Label title;

  @FXML
  private ComboBox courseInput;
  @FXML
  private ComboBox<String> gradeInput;
  @FXML
  private ComboBox<String> scoreInput;
  @FXML
  private TextArea commentInput;
  /**
   * Adds application description to dashboard.
   */

  private ArrayList<String> grades = new ArrayList<String>();

  private void populateGrades(){
    grades.add("A");
    grades.add("B");
    grades.add("C");
    grades.add("D");
    grades.add("E");
    grades.add("F");
  }


  @FXML
  public void initialize() {
    title.setText("Welcome to your grades");
    populateGrades();
    ObservableList<String> gradeList = FXCollections.observableArrayList(grades);

    System.out.println(grades);
    gradeInput.setItems(gradeList);
}

}