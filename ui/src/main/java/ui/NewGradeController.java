package ui;

import core.Grade;
import core.User;
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

public class NewGradeController extends Controller {

    @FXML
    private Label title;

    @FXML
    ComboBox scoreInput;

    @FXML
    ComboBox gradeInput;

    @FXML
    void handleOpenDashboard(ActionEvent event) {
        openDashboard(event);
    }

    User user;

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

    @FXML
    public void initialize() {

        title.setText("New grade");
        populateScore();
        scoreInput.setItems(populateScore());
        gradeInput.setItems(populateGrade());
    }

    public void passUser(User u) {
        user = u;
    }

}