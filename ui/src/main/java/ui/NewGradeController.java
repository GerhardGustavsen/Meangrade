package ui;

import core.Grade;
import core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import core.Encrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewGradeController extends Controller implements Initializable {

    @FXML
    private Label title;

    @FXML
    ComboBox scoreInput;

    @FXML
    ComboBox gradeInput;

    @FXML
    void handleOpenDashboard(ActionEvent event) {
        DashboardController dash = new DashboardController();
        openFXML(dash, "Dashboard.fxml");
        dash.poppulateListView();
        dash.initClickActions();
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText("New grade");
        populateScore();
        scoreInput.setItems(populateScore());
        gradeInput.setItems(populateGrade());

    }

}