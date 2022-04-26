package ui;

import core.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NewGradeController extends Controller implements Initializable {

    @FXML
    private Label title;

    @FXML
    private ComboBox scoreInput;

    @FXML
    private ComboBox gradeInput;

    @FXML
    private ComboBox courseInput;

    @FXML
    private TextArea commentInput;

    @FXML
    private Label errorMsg;

    @FXML
    private DialogPane dialogPane;


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

    private ObservableList<String> populateCourse() {
        List<String> codeList = core.getCourses().stream().map(Course::getCode).collect(Collectors.toList());
        return FXCollections.observableList(codeList);
    }

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

    boolean codeInList(String code){
        return core.getActiveUser().getGrades().stream()
                .anyMatch(grade -> grade.getCode().equals(code));
    }

    @FXML
    void handleCreateGrade() throws IOException {
        if(checkInput()){
            char grade = (char) gradeInput.getValue();
            String courseCode = (String) courseInput.getValue();
            Integer score = (Integer) scoreInput.getValue();
            String comment = commentInput.getText();
            String trimmedCourse = courseCode.trim();
            System.out.println(trimmedCourse.equals("TDT1000"));
            if (codeInList(trimmedCourse)) {
                dialogPane.setVisible(true);
            }else{
                core.newGrade(grade, trimmedCourse, score, comment);
                errorMsg.setText("The grade was succesfully created!");
            }
        }
    }

    private boolean checkInput(){
        setErrorColor();
        if(courseInput.getValue()== null){
            errorMsg.setText("Please select a course code!");
        }else if(gradeInput.getValue()== null){
            errorMsg.setText("Please select a grade!");
        }else if(scoreInput.getValue() == null){
            errorMsg.setText("Please select a score!");
        }else if(commentInput.getText().equals("")){
            errorMsg.setText("The comment cannot be empty!");
        }else{
            setSuccessColor();
            return true;
        }
        return false;
    }

    private void setErrorColor(){
        errorMsg.setTextFill(Paint.valueOf("red"));
    }
    private void setSuccessColor(){
        errorMsg.setTextFill(Paint.valueOf("green"));
    }

    public void handleClose(){
        dialogPane.setVisible(false);
    }

    public void handleAccept() throws IOException {
        try{
        char grade = (char) gradeInput.getValue();
        String courseCode = (String) courseInput.getValue();
        Integer score = (Integer) scoreInput.getValue();
        String comment = commentInput.getText();
        String trimmedCourse = courseCode.trim();
        core.removeGrade(trimmedCourse);
        handleClose();
        core.newGrade(grade, trimmedCourse, score, comment);
        errorMsg.setText("The grade was succesfully created!");
        }catch (IOException e){
            System.out.println("Could not remove prior grades");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText("New grade");
        populateScore();
        scoreInput.setItems(populateScore());
        gradeInput.setItems(populateGrade());
        courseInput.setItems(populateCourse());
        dialogPane.setVisible(false);

    }



}