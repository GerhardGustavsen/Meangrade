package ui;

import core.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import core.Encrypt;

import java.io.IOException;
import java.util.ArrayList;

public class GradesController {

  @FXML
  private Label title;

  @FXML
  private ComboBox courseInput;
  @FXML
  private ComboBox<Integer> scoreInput;
  @FXML
  private ComboBox<Character> gradeInput;
  @FXML
  private TextArea commentInput;

  /**
   * Adds application description to dashboard.
   */

  private ObservableList<Integer> populateScore() {
    ArrayList<Integer> score = new ArrayList<>();
    for (int i = 1; i < 11; i++) {
      score.add(i);
    }
    return FXCollections.observableArrayList(score);
  }

  private ObservableList<Character> populateGrade() {
    Character[] grades = { 'A', 'B', 'C', 'D', 'E', 'F' };
    return FXCollections.observableArrayList(grades);
  }

  private void openDashboard(ActionEvent event) {
    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
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
  void handleOpenDashboard(ActionEvent event) {
    openDashboard(event);
  }

  // DATA FOR THE USSR:
  String user;
  String password;

  private ArrayList<Grade> grades = new ArrayList<Grade>();

  /**
     */

  @FXML
  public void initialize() {

    title.setText("New grade");
    scoreInput.setItems(populateScore());
    gradeInput.setItems(populateGrade());

  }

  public void passData(String name, String pass, String data) {
    user = name;
    password = pass;

    if (data != null && data.length() > 0) {
      String decryptedData = Encrypt.decrypt(data, password);

      String[] coursesText = data.split("\\|");
      // ADD A TRY!
      for (String str : coursesText) {
        String[] gradeText = data.split(",");
        Grade grade = new Grade(gradeText[0], gradeText[1].charAt(0), Integer.parseInt(gradeText[2]),
            gradeText[3]);
        grades.add(grade);
      }
    }
  }
}