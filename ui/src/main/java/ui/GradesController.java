package ui;

import core.Grade;
import core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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

    User user;

    /**
     * Adds application description to dashboard.
     */

    private ObservableList<Integer> populateScore() {
        ArrayList<Integer> score = new ArrayList<Integer>();
        for (int i = 1; i < 11; i++) {
            score.add(i);
        }
        ObservableList<Integer> result = FXCollections.observableArrayList(score);
        return result;
    }

    private ObservableList<Character> populateGrade() {
        Character[] grades = { 'A', 'B', 'C', 'D', 'E', 'F' };
        ObservableList<Character> gradeList = FXCollections.observableArrayList(grades);
        return gradeList;
    }

    private void openGrades() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Grades");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.print("Did not find data fxml form!");
            e.printStackTrace();
        }
    }

    @FXML
    void handleOpenGrades() {
        openGrades();
    }

    /**
     */

    @FXML
    public void initialize() {

        title.setText("New grade");
        populateScore();
        scoreInput.setItems(populateScore());
        gradeInput.setItems(populateGrade());

    }


}