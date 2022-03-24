package ui;

import core.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import core.Encrypt;

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

    // DATA FOR THE USSR:
    String user;
    String password;

    private ArrayList<Grade> grades = new ArrayList<Grade>();

    /**
     * Adds application description to dashboard.
     */

    public void passData(String name, String pass, String data) {
        user = name;
        password = pass;

        if (data != null && data.length() > 0) {
            String decryptedData = Encrypt.decrypt(data, password);

            String[] coursesText = data.split("|");
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